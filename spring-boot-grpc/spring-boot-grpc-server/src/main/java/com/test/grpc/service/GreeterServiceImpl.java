package com.test.grpc.service;


import com.test.grpc.GreeterGrpc;
import com.test.grpc.HelloProto;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GreeterServiceImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloProto.HelloRequest request,
                         StreamObserver<HelloProto.HelloReply> responseObserver) {
        System.out.println("Processed request for: " + request.getName());
        // 构建响应
        String message = "Hello zzz, " + request.getName() + "! (from Spring Boot)";
        HelloProto.HelloReply reply = HelloProto.HelloReply.newBuilder()
                .setMessage(message)
                .build();

        // 发送响应
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}