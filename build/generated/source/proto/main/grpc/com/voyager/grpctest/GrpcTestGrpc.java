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
    comments = "Source: test.proto")
public class GrpcTestGrpc {

  private GrpcTestGrpc() {}

  public static final String SERVICE_NAME = "grpctest.GrpcTest";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.voyager.grpctest.TestRequest,
      com.voyager.grpctest.TestResponse> METHOD_TEST_SINGLE_REQUEST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "grpctest.GrpcTest", "TestSingleRequest"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.voyager.grpctest.TestRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.voyager.grpctest.TestResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.voyager.grpctest.TestRequest,
      com.voyager.grpctest.TestResponse> METHOD_TEST_SERVER_TO_CLIENT_STREAM =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "grpctest.GrpcTest", "TestServerToClientStream"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.voyager.grpctest.TestRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.voyager.grpctest.TestResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.voyager.grpctest.TestRequest,
      com.voyager.grpctest.TestResponse> METHOD_TEST_CLIENT_TO_SERVER_STREAM =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING,
          generateFullMethodName(
              "grpctest.GrpcTest", "TestClientToServerStream"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.voyager.grpctest.TestRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.voyager.grpctest.TestResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.voyager.grpctest.TestRequest,
      com.voyager.grpctest.TestResponse> METHOD_TEST_BIDIRECTIONAL_STREAM =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING,
          generateFullMethodName(
              "grpctest.GrpcTest", "TestBidirectionalStream"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.voyager.grpctest.TestRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.voyager.grpctest.TestResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GrpcTestStub newStub(io.grpc.Channel channel) {
    return new GrpcTestStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GrpcTestBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GrpcTestBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static GrpcTestFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GrpcTestFutureStub(channel);
  }

  /**
   */
  public static abstract class GrpcTestImplBase implements io.grpc.BindableService {

    /**
     */
    public void testSingleRequest(com.voyager.grpctest.TestRequest request,
        io.grpc.stub.StreamObserver<com.voyager.grpctest.TestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_TEST_SINGLE_REQUEST, responseObserver);
    }

    /**
     */
    public void testServerToClientStream(com.voyager.grpctest.TestRequest request,
        io.grpc.stub.StreamObserver<com.voyager.grpctest.TestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_TEST_SERVER_TO_CLIENT_STREAM, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.voyager.grpctest.TestRequest> testClientToServerStream(
        io.grpc.stub.StreamObserver<com.voyager.grpctest.TestResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_TEST_CLIENT_TO_SERVER_STREAM, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.voyager.grpctest.TestRequest> testBidirectionalStream(
        io.grpc.stub.StreamObserver<com.voyager.grpctest.TestResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_TEST_BIDIRECTIONAL_STREAM, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_TEST_SINGLE_REQUEST,
            asyncUnaryCall(
              new MethodHandlers<
                com.voyager.grpctest.TestRequest,
                com.voyager.grpctest.TestResponse>(
                  this, METHODID_TEST_SINGLE_REQUEST)))
          .addMethod(
            METHOD_TEST_SERVER_TO_CLIENT_STREAM,
            asyncServerStreamingCall(
              new MethodHandlers<
                com.voyager.grpctest.TestRequest,
                com.voyager.grpctest.TestResponse>(
                  this, METHODID_TEST_SERVER_TO_CLIENT_STREAM)))
          .addMethod(
            METHOD_TEST_CLIENT_TO_SERVER_STREAM,
            asyncClientStreamingCall(
              new MethodHandlers<
                com.voyager.grpctest.TestRequest,
                com.voyager.grpctest.TestResponse>(
                  this, METHODID_TEST_CLIENT_TO_SERVER_STREAM)))
          .addMethod(
            METHOD_TEST_BIDIRECTIONAL_STREAM,
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.voyager.grpctest.TestRequest,
                com.voyager.grpctest.TestResponse>(
                  this, METHODID_TEST_BIDIRECTIONAL_STREAM)))
          .build();
    }
  }

  /**
   */
  public static final class GrpcTestStub extends io.grpc.stub.AbstractStub<GrpcTestStub> {
    private GrpcTestStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcTestStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcTestStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcTestStub(channel, callOptions);
    }

    /**
     */
    public void testSingleRequest(com.voyager.grpctest.TestRequest request,
        io.grpc.stub.StreamObserver<com.voyager.grpctest.TestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_TEST_SINGLE_REQUEST, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void testServerToClientStream(com.voyager.grpctest.TestRequest request,
        io.grpc.stub.StreamObserver<com.voyager.grpctest.TestResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_TEST_SERVER_TO_CLIENT_STREAM, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.voyager.grpctest.TestRequest> testClientToServerStream(
        io.grpc.stub.StreamObserver<com.voyager.grpctest.TestResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(METHOD_TEST_CLIENT_TO_SERVER_STREAM, getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.voyager.grpctest.TestRequest> testBidirectionalStream(
        io.grpc.stub.StreamObserver<com.voyager.grpctest.TestResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_TEST_BIDIRECTIONAL_STREAM, getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class GrpcTestBlockingStub extends io.grpc.stub.AbstractStub<GrpcTestBlockingStub> {
    private GrpcTestBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcTestBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcTestBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcTestBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.voyager.grpctest.TestResponse testSingleRequest(com.voyager.grpctest.TestRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_TEST_SINGLE_REQUEST, getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.voyager.grpctest.TestResponse> testServerToClientStream(
        com.voyager.grpctest.TestRequest request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_TEST_SERVER_TO_CLIENT_STREAM, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GrpcTestFutureStub extends io.grpc.stub.AbstractStub<GrpcTestFutureStub> {
    private GrpcTestFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcTestFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcTestFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcTestFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.voyager.grpctest.TestResponse> testSingleRequest(
        com.voyager.grpctest.TestRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_TEST_SINGLE_REQUEST, getCallOptions()), request);
    }
  }

  private static final int METHODID_TEST_SINGLE_REQUEST = 0;
  private static final int METHODID_TEST_SERVER_TO_CLIENT_STREAM = 1;
  private static final int METHODID_TEST_CLIENT_TO_SERVER_STREAM = 2;
  private static final int METHODID_TEST_BIDIRECTIONAL_STREAM = 3;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GrpcTestImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(GrpcTestImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TEST_SINGLE_REQUEST:
          serviceImpl.testSingleRequest((com.voyager.grpctest.TestRequest) request,
              (io.grpc.stub.StreamObserver<com.voyager.grpctest.TestResponse>) responseObserver);
          break;
        case METHODID_TEST_SERVER_TO_CLIENT_STREAM:
          serviceImpl.testServerToClientStream((com.voyager.grpctest.TestRequest) request,
              (io.grpc.stub.StreamObserver<com.voyager.grpctest.TestResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TEST_CLIENT_TO_SERVER_STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.testClientToServerStream(
              (io.grpc.stub.StreamObserver<com.voyager.grpctest.TestResponse>) responseObserver);
        case METHODID_TEST_BIDIRECTIONAL_STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.testBidirectionalStream(
              (io.grpc.stub.StreamObserver<com.voyager.grpctest.TestResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_TEST_SINGLE_REQUEST,
        METHOD_TEST_SERVER_TO_CLIENT_STREAM,
        METHOD_TEST_CLIENT_TO_SERVER_STREAM,
        METHOD_TEST_BIDIRECTIONAL_STREAM);
  }

}
