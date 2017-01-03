package com.voyager.grpctest;

import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by dale on 12/14/16.
 */
public class Server {
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    /* The port on which the server should run */
    private int port = 50051;
    private io.grpc.Server server;

    private void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new TestServicerImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                Server.this.stop();
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
        final Server server = new Server();
        server.start();
        server.blockUntilShutdown();
    }

    private class TestServicerImpl extends GrpcTestGrpc.GrpcTestImplBase {
        @Override
        public void testSingleRequest(TestRequest request, StreamObserver<TestResponse> responseObserver) {
            TestResponse response = TestResponse.newBuilder().setMessage("hi " + request.getMessage()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void testServerToClientStream(TestRequest request, StreamObserver<TestResponse> responseObserver) {
            for(int i=0; i<5; i++) {
                TestResponse response = TestResponse.newBuilder().setMessage("hi " + request.getMessage() + " " + i).build();
                responseObserver.onNext(response);
            }
            responseObserver.onCompleted();
        }

        @Override
        public StreamObserver<TestRequest> testClientToServerStream(final StreamObserver<TestResponse> responseObserver) {
            StreamObserver<TestRequest> observer = new StreamObserver<TestRequest>() {

                String finalMessage = "";

                @Override
                public void onNext(TestRequest value) {
                    logger.info("From Client: " + value.getMessage());
                    finalMessage += value.getMessage();
                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onCompleted() {
                    TestResponse response = TestResponse.newBuilder().setMessage(finalMessage).build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                }
            };

            return observer;
        }

        @Override
        public StreamObserver<TestRequest> testBidirectionalStream(final StreamObserver<TestResponse> responseObserver) {
            StreamObserver<TestRequest> observer = new StreamObserver<TestRequest>() {

                String finalMessage = "";

                @Override
                public void onNext(TestRequest value) {
                    logger.info("From Client: " + value.getMessage());
                    finalMessage += value.getMessage();

                    TestResponse response = TestResponse.newBuilder().setMessage(finalMessage).build();
                    responseObserver.onNext(response);
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
