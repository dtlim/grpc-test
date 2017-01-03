package com.voyager.grpctest.api;

import com.voyager.grpctest.*;
import com.voyager.grpctest.sample.Client;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dale on 1/3/17.
 */
public class ApiClient {
    private static final Logger logger = Logger.getLogger(Client.class.getName());
    private Scanner scanner = new Scanner(System.in);

    private final ManagedChannel channel;
    private final GrpcApiGrpc.GrpcApiBlockingStub blockingStub;
    private final GrpcApiGrpc.GrpcApiStub asyncStub;

    /** Construct client connecting to HelloWorld server at {@code host:port}. */
    public ApiClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        blockingStub = GrpcApiGrpc.newBlockingStub(channel);
        asyncStub = GrpcApiGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void requestContent(String query, int page) {
        Request request = Request.newBuilder()
                .setQuery(query)
                .setPage(page)
                .build();
        Response response;
        try {
            response = blockingStub.requestContent(request);
        }
        catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.info("From Server: " + response.getContent());
    }

    public static void main(String[] args) throws Exception {
        ApiClient client = new ApiClient("localhost", 50052);
        try {
            client.requestContent("Sample query.", 1);
        }
        finally {
            client.shutdown();
        }
    }
}
