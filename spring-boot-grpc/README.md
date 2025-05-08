# spring-boot-grpc

## 下载 Protocol Buffer 编译器

访问 https://github.com/protocolbuffers/protobuf/releases  
下载最新版的 protoc-xxx-win64.zip  
验证：protoc --version

## 下载 gRPC Java 插件

https://repo1.maven.org/maven2/io/grpc/protoc-gen-grpc-java/1.58.0/protoc-gen-grpc-java-1.58.0-windows-x86_64.exe  

## 准备 .proto 文件

C:\grpc-project\
├── src\
│   ├── main\
│   │   ├── java\
│   │   └── proto\
│   │       └── hello.proto

hello.proto 内容：

syntax = "proto3";

option java_multiple_files = true;
option java_package = "com.test.grpc";
option java_outer_classname = "HelloProto";

package hello;

service Greeter {
  rpc SayHello (HelloRequest) returns (HelloReply) {}
}

message HelloRequest {
  string name = 1;
}

message HelloReply {
  string message = 1;
}


## 手动生成 Java 代码

protoc --proto_path=./src/main/proto --java_out=./src/main/java --grpc-java_out=./src/main/java --plugin=protoc-gen-grpc-java="G:\tools\protoc-30.1-win64\protoc-gen-grpc-java-1.58.0-windows-x86_64.exe" src\main\proto\hello.proto

生成完成后，目录结构应该如下：
C:\grpc-project\src\main\java\com\example\grpc\
├── HelloGrpc.java       # gRPC服务接口
├── HelloProto.java      # Protobuf消息类(如果java_multiple_files=false)
└── (如果java_multiple_files=true)
    ├── HelloReply.java
    ├── HelloRequest.java
    └── ...
