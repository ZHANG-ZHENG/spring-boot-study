package com.corundumstudio;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;

/**
 * 消息事件，作为后端与前台交互
 * @authoer liangxifeng 2018-07-07
 */
@Component
public class MessageEventHandler {
    public static SocketIOServer socketIoServer;
    static ArrayList<UUID> listClient = new ArrayList<UUID>();
    static final int limitSeconds = 60;
    //线程安全的map
    public static ConcurrentHashMap<String,SocketIOClient> webSocketMap = new ConcurrentHashMap<String, SocketIOClient>();

    @Autowired
    public MessageEventHandler(SocketIOServer server) {
        this.socketIoServer = server;
    }

    /**
     * 客户端连接的时候触发，前端js触发：socket = io.connect("http://192.168.9.209:9092");
     * @param client
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        String mac = client.getHandshakeData().getSingleUrlParam("mac");
        listClient.add(client.getSessionId());
        //以mac地址为key,SocketIOClient 为value存入map,后续可以指定mac地址向客户端发送消息
        webSocketMap.put(mac,client);
        //socketIoServer.getClient(client.getSessionId()).sendEvent("message", "back data");
        System.out.println("客户端:" + client.getSessionId() + "已连接,mac="+mac);
    }

    /**
     * 客户端关闭连接时触发：前端js触发：socket.disconnect();
     * @param client
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("客户端:" + client.getSessionId() + "断开连接");
    }

    /**
     * 自定义消息事件，客户端js触发：socket.emit('messageevent', {msgContent: msg}); 时触发
     * 前端js的 socket.emit("事件名","参数数据")方法，是触发后端自定义消息事件的时候使用的,
     * 前端js的 socket.on("事件名",匿名函数(服务器向客户端发送的数据))为监听服务器端的事件
     * @param client　客户端信息
     * @param request 请求信息
     * @param data　客户端发送数据{msgContent: msg}
     */
    @OnEvent(value = "messageevent")
    public void onEvent(SocketIOClient client, AckRequest request, MessageInfo data) {
    	String msgContent = data.getMsgContent();
        System.out.println("发来消息：" + msgContent);
        //服务器端向该客户端发送消息
        //socketIoServer.getClient(client.getSessionId()).sendEvent("messageevent", "你好 data");
        if(!msgContent.contains("img")) {
        	client.sendEvent("messageevent","我是服务器发送的信息.");
        }else {
        	Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
            //获取屏幕大小
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dm = toolkit.getScreenSize();
            //while(true){
                //一个矩形面板
                Rectangle rec = new Rectangle(0, 0, (int)dm.getWidth(), (int)dm.getHeight());
                //按照矩形截取图片到缓冲流
                BufferedImage img = robot.createScreenCapture(rec);
//                //缩放图片
//                BufferedImage newImg = RobotScreenTest.resize(img, jframe.getWidth(), jframe.getHeight());
//                label.setIcon(new ImageIcon(newImg));
                client.sendEvent("imgEvent", "111");
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
            //}
        }
        
    }

    public static void sendBuyLogEvent() {   //这里就是向客户端推消息了
        //String dateTime = new DateTime().toString("hh:mm:ss");

        for (UUID clientId : listClient) {
            if (socketIoServer.getClient(clientId) == null) continue;
            socketIoServer.getClient(clientId).sendEvent("enewbuy", "当前时间", 1);
        }

    }
}
