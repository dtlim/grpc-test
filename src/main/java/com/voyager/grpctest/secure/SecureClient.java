package com.voyager.grpctest.secure;

import com.voyager.grpctest.sample.Client;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NettyChannelBuilder;

import javax.net.ssl.SSLException;
import java.io.File;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dale on 1/4/17.
 */
public class SecureClient {
    private static final Logger logger = Logger.getLogger(Client.class.getName());

    private final ManagedChannel channel;
    private final GrpcSecureGrpc.GrpcSecureBlockingStub blockingStub;
    private final GrpcSecureGrpc.GrpcSecureStub asyncStub;

    /** Construct client connecting to HelloWorld server at {@code host:port}. */
    public SecureClient(String host, int port) throws URISyntaxException, SSLException{
//        channel = ManagedChannelBuilder.forAddress(host, port)
//                .build();
        File certFile = new File(SecureServer.class.getResource("server.crt.pem").toURI());
        channel = NettyChannelBuilder.forAddress(host, 8443)
                .sslContext(GrpcSslContexts.forClient().trustManager(certFile).build())
                .build();
        blockingStub = GrpcSecureGrpc.newBlockingStub(channel);
        asyncStub = GrpcSecureGrpc.newStub(channel);
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
            e.printStackTrace();
            return;
        }
        logger.info("From Server: " + response.getContent());
    }

    public static void main(String[] args) throws Exception {
        SecureClient client = new SecureClient("localhost", 8443);
        try {
            client.requestContent("Sample query.", 1);
        }
        finally {
            client.shutdown();
        }
    }
}
