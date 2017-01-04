package com.voyager.grpctest.chat;

import com.voyager.grpctest.*;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by amw on 1/3/2017.
 */
public class ChatServer {
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    /* The port on which the server should run */
    private int port = 50054;
    private io.grpc.Server server;

    private void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new ChatServer.ChatService())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                ChatServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final ChatServer server = new ChatServer();
        server.start();
        server.blockUntilShutdown();
    }

    private class ChatService extends GrpcChatGrpc.GrpcChatImplBase {

        private List<StreamObserver<ChatResponse>> observers = new ArrayList<>();

        @Override
        public StreamObserver<ChatMessage> chatStream(StreamObserver<ChatResponse> responseObserver) {
            observers.add(responseObserver);
            StreamObserver<ChatMessage> observer = new StreamObserver<ChatMessage>() {
                @Override
                public void onNext(ChatMessage value) {
                    ChatResponse response = ChatResponse.newBuilder()
                            .setStatusCode(1)
                            .setMessage(value.getMessage())
                            .setUser(value.getUser())
                            .build();
                    for(StreamObserver<ChatResponse> currentObserver : observers) {
                        currentObserver.onNext(response);
                    }
                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onCompleted() {
                    responseObserver.onCompleted();
                }
            };
            return observer;
        }
    }
}
