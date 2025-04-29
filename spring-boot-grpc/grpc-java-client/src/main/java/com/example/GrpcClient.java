package com.example;

import com.example.grpc.GreeterGrpc;
import com.example.grpc.HelloRequest;
import com.example.grpc.HelloReply;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrpcClient {
    private static final Logger logger = LoggerFactory.getLogger(GrpcClient.class);

    public static void main(String[] args) {
        // 1. 创建Channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 3000)
                .usePlaintext() // 开发环境使用，生产环境应使用TLS
                .build();

        try {
            // 2. 创建Stub
            GreeterGrpc.GreeterBlockingStub blockingStub = GreeterGrpc.newBlockingStub(channel);

            // 3. 准备请求
            HelloRequest request = HelloRequest.newBuilder()
                    .setName("Java gRPC Client")
                    .build();

            // 4. 发送RPC请求
            logger.info("Sending request to server...");
            HelloReply response = blockingStub.sayHello(request);

            // 5. 处理响应
            logger.info("Received response: " + response.getMessage());
        } finally {
            // 6. 关闭Channel
            channel.shutdown();
        }
    }
}