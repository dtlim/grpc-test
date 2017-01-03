package com.voyager.grpctest;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.0)",
    comments = "Source: chat.proto")
public class GrpcChatGrpc {

  private GrpcChatGrpc() {}

  public static final String SERVICE_NAME = "grpctest.GrpcChat";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.voyager.grpctest.ChatMessage,
      com.voyager.grpctest.ChatResponse> METHOD_CHAT_STREAM =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING,
          generateFullMethodName(
              "grpctest.GrpcChat", "ChatStream"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.voyager.grpctest.ChatMessage.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.voyager.grpctest.ChatResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GrpcChatStub newStub(io.grpc.Channel channel) {
    return new GrpcChatStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GrpcChatBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GrpcChatBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static GrpcChatFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GrpcChatFutureStub(channel);
  }

  /**
   */
  public static abstract class GrpcChatImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<com.voyager.grpctest.ChatMessage> chatStream(
        io.grpc.stub.StreamObserver<com.voyager.grpctest.ChatResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_CHAT_STREAM, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_CHAT_STREAM,
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.voyager.grpctest.ChatMessage,
                com.voyager.grpctest.ChatResponse>(
                  this, METHODID_CHAT_STREAM)))
          .build();
    }
  }

  /**
   */
  public static final class GrpcChatStub extends io.grpc.stub.AbstractStub<GrpcChatStub> {
    private GrpcChatStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcChatStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcChatStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcChatStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.voyager.grpctest.ChatMessage> chatStream(
        io.grpc.stub.StreamObserver<com.voyager.grpctest.ChatResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_CHAT_STREAM, getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class GrpcChatBlockingStub extends io.grpc.stub.AbstractStub<GrpcChatBlockingStub> {
    private GrpcChatBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcChatBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcChatBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcChatBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class GrpcChatFutureStub extends io.grpc.stub.AbstractStub<GrpcChatFutureStub> {
    private GrpcChatFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcChatFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcChatFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcChatFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_CHAT_STREAM = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GrpcChatImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(GrpcChatImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHAT_STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.chatStream(
              (io.grpc.stub.StreamObserver<com.voyager.grpctest.ChatResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_CHAT_STREAM);
  }

}
