package com.corundumstudio;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 在项目服务启动的时候启动socket.io服务
 * @author liangxifeng 2018-07-07
 */
@Component
@Order(value=1)
public class ServerRunner implements CommandLineRunner {

    private final SocketIOServer server;


    @Autowired
    public ServerRunner(SocketIOServer server) {
        this.server = server;
    }

    @Override
    public void run(String... args) throws Exception {
        server.start();
        System.out.println("socket.io启动成功！");
    }

}