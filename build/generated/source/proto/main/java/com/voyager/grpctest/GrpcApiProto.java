// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: api.proto

package com.voyager.grpctest;

public final class GrpcApiProto {
  private GrpcApiProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_grpctest_Request_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_grpctest_Request_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_grpctest_Response_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_grpctest_Response_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\tapi.proto\022\010grpctest\"&\n\007Request\022\r\n\005quer" +
      "y\030\001 \001(\t\022\014\n\004page\030\002 \001(\005\";\n\010Response\022\017\n\007con" +
      "tent\030\001 \001(\t\022\014\n\004page\030\002 \001(\005\022\020\n\010has_next\030\003 \001" +
      "(\0102D\n\007GrpcApi\0229\n\016RequestContent\022\021.grpcte" +
      "st.Request\032\022.grpctest.Response\"\000B0\n\024com." +
      "voyager.grpctestB\014GrpcApiProtoP\001\242\002\007GRPCA" +
      "PIb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_grpctest_Request_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_grpctest_Request_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_grpctest_Request_descriptor,
        new java.lang.String[] { "Query", "Page", });
    internal_static_grpctest_Response_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_grpctest_Response_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_grpctest_Response_descriptor,
        new java.lang.String[] { "Content", "Page", "HasNext", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}