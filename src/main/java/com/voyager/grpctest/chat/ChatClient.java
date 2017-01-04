package com.voyager.grpctest.chat;

import com.voyager.grpctest.*;
import com.voyager.grpctest.sample.Client;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by amw on 1/3/2017.
 */
public class ChatClient {
    private static final Logger logger = Logger.getLogger(Client.class.getName());
    private Scanner scanner = new Scanner(System.in);

    private final ManagedChannel channel;
    private final GrpcChatGrpc.GrpcChatStub asyncStub;

    public ChatClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        asyncStub = GrpcChatGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void startChat() {
        StreamObserver<ChatResponse> responseObserver = new StreamObserver<ChatResponse>() {
            @Override
            public void onNext(ChatResponse value) {
                logger.info("From " + value.getUser() + ": " + value.getMessage());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        };

        StreamObserver<ChatMessage> requestObserver = asyncStub.chatStream(responseObserver);
        while(true) {
            System.out.print("Enter your message: ");
            String message = scanner.nextLine();

            if(message == null || message.isEmpty()) {
                break;
            }

            ChatMessage chatMessage = ChatMessage.newBuilder()
                    .setMessage(message)
                    .setUser("asdf")
                    .build();
            requestObserver.onNext(chatMessage);
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

    public static void main(String[] args) throws Exception {
        ChatClient client = new ChatClient("localhost", 50054);
        try {
            client.startChat();
        }
        finally {
            client.shutdown();
        }
    }
}
