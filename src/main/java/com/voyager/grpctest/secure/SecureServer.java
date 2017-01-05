package com.voyager.grpctest.secure;

import com.voyager.grpctest.chat.ChatServer;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;

/**
 * Created by dale on 1/4/17.
 */
public class SecureServer {
    private static final Logger logger = Logger.getLogger(ChatServer.class.getName());

    /* The port on which the server should run */
    private int port = 8443;
    private io.grpc.Server server;

    private void start() throws IOException, URISyntaxException {
        File certFile = new File(SecureServer.class.getResource("server.crt").toURI());
        File privateKeyFile = new File(SecureServer.class.getResource("server.pem").toURI());
        server = ServerBuilder.forPort(port)
                .useTransportSecurity(certFile, privateKeyFile)
                .addService(new SecureService())
                .build()
                .start();
//        server = NettyServerBuilder.forPort(port)
//                .sslContext(GrpcSslContexts.forServer(certFile, privateKeyFile).build())
//                .addService(new SecureService())
//                .build()
//                .start();

        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                SecureServer.this.stop();
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
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        final SecureServer server = new SecureServer();
        server.start();
        server.blockUntilShutdown();
    }

    private class SecureService extends GrpcSecureGrpc.GrpcSecureImplBase {
        @Override
        public void requestContent(Request request, StreamObserver<Response> responseObserver) {
            String query = request.getQuery();
            int page = request.getPage();

            String responseMessage = query + " " + page + " response.";
            int responsePage = page;
            boolean hasNext = true;

            Response response = Response.newBuilder()
                    .setContent(responseMessage)
                    .setPage(responsePage)
                    .setHasNext(hasNext)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
