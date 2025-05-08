package com.test.grpc.service;

import com.test.grpc.GreeterGrpc.GreeterImplBase;
import com.test.grpc.HelloRequest;
import com.test.grpc.HelloReply;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GreeterServiceImpl extends GreeterImplBase {

    @Override
    public void sayHello(HelloRequest request,
                         StreamObserver<HelloReply> responseObserver) {
        System.out.println("Processed request for: " + request.getName());
        // 构建响应
        String message = "Hello zzz, " + request.getName() + "! (from Spring Boot)";
        HelloReply reply = HelloReply.newBuilder()
                .setMessage(message)
                .build();

        // 发送响应
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}