package com.voyager.grpctest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.rmi.StubNotFoundException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Scanner;

/**
 * Created by dale on 12/14/16.
 */
public class Client {
    private static final Logger logger = Logger.getLogger(Client.class.getName());
    private Scanner scanner = new Scanner(System.in);

    private final ManagedChannel channel;
    private final GrpcTestGrpc.GrpcTestBlockingStub blockingStub;
    private final GrpcTestGrpc.GrpcTestStub asyncStub;

    /** Construct client connecting to HelloWorld server at {@code host:port}. */
    public Client(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        blockingStub = GrpcTestGrpc.newBlockingStub(channel);
        asyncStub = GrpcTestGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void testSingleRequest(String name) {
        TestRequest request = TestRequest.newBuilder().setMessage(name).build();
        TestResponse response;
        try {
            response = blockingStub.testSingleRequest(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.info("From Server: " + response.getMessage());
    }

    public void testServerToClientStream(String name) {
        TestRequest request = TestRequest.newBuilder().setMessage(name).build();
        Iterator<TestResponse> responses;

        try {
            responses = blockingStub.testServerToClientStream(request);
        } catch (StatusRuntimeException e) {
            return;
        }

        while(responses.hasNext()) {
            TestResponse response = responses.next();
            logger.info("From Server: " + response.getMessage());
        }
    }

    public void testClientToServerStream() {
        // even though you will only get one response, you still implement retrieval of responses as stream (?)
        StreamObserver<TestResponse> responseObserver = new StreamObserver<TestResponse>() {
            @Override
            public void onNext(TestResponse value) {
                logger.info("From Server: " + value.getMessage());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        };

        StreamObserver<TestRequest> requestObserver = asyncStub.testClientToServerStream(responseObserver);
        for(int i=0; i<5; i++) {
            TestRequest request = TestRequest.newBuilder().setMessage("Message " + i).build();
            requestObserver.onNext(request);
        }
        requestObserver.onCompleted();
    }

    public void testBidirectionalStream() {
        StreamObserver<TestResponse> responseObserver = new StreamObserver<TestResponse>() {
            @Override
            public void onNext(TestResponse value) {
                logger.info("From Server: " + value.getMessage() + " " + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        };

        StreamObserver<TestRequest> requestObserver = asyncStub.testBidirectionalStream(responseObserver);
        while(true) {
            System.out.println("Enter your message: " + Thread.currentThread().getName());
            String message = scanner.nextLine();

            if(message == null || message.isEmpty()) {
                break;
            }

            TestRequest request = TestRequest.newBuilder().setMessage(message).build();
            requestObserver.onNext(request);
            try {
                Thread.sleep(1000);
            }
            catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        requestObserver.onCompleted();
    }

    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting.
     */
    public static void main(String[] args) throws Exception {
        Client client = new Client("localhost", 50051);
        try {
            String user = "world";
            if (args.length > 0) {
                user = args[0];
            }
//            client.testSingleRequest(user);
//            client.testServerToClientStream(user);
//            client.testClientToServerStream();
            client.testBidirectionalStream();
        } finally {
            client.shutdown();
        }
    }
}
