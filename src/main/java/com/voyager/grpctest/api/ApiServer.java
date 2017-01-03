package com.voyager.grpctest.api;

import com.voyager.grpctest.GrpcApiGrpc;
import com.voyager.grpctest.Request;
import com.voyager.grpctest.Response;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by dale on 1/3/17.
 */
public class ApiServer {

    private static final Logger logger = Logger.getLogger(ApiServer.class.getName());

    private int port = 50052;
    private io.grpc.Server server;

    private void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new GrpcApi())
                .build()
                .start();
        logger.info("Api Server started at port " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                ApiServer.this.stop();
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

    public static void main(String[] args) throws IOException, InterruptedException {
        final ApiServer server = new ApiServer();
        server.start();
        server.blockUntilShutdown();
    }

    private class GrpcApi extends GrpcApiGrpc.GrpcApiImplBase {
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
