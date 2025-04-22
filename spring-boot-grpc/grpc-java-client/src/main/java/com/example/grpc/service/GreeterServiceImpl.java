package com.example.grpc.service;

import com.example.grpc.GreeterGrpc.GreeterImplBase;
import com.example.grpc.HelloRequest;
import com.example.grpc.HelloReply;
import io.grpc.stub.StreamObserver;
//import net.devh.boot.grpc.server.service.GrpcService;
//
//@GrpcService
//public class GreeterServiceImpl extends GreeterImplBase {
//
//    @Override
//    public void sayHello(HelloRequest request,
//                         StreamObserver<HelloReply> responseObserver) {
//        // 构建响应
//        String message = "Hello, " + request.getName() + "! (from Spring Boot)";
//        HelloReply reply = HelloReply.newBuilder()
//                .setMessage(message)
//                .build();
//
//        // 发送响应
//        responseObserver.onNext(reply);
//        responseObserver.onCompleted();
//
//        System.out.println("Processed request for: " + request.getName());
//    }
//}