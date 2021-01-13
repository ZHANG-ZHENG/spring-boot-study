package top.zhost.test;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MqttDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqttDemoApplication.class, args);

        test();
    }


    private static void test(){
        MqttPushClient.MQTT_HOST = "tcp://zhost.top:1883";
        MqttPushClient.MQTT_CLIENTID = "client";
        //MqttPushClient.MQTT_USERNAME = "username";
        //MqttPushClient.MQTT_PASSWORD = "password";
        MqttPushClient client = MqttPushClient.getInstance();
        //client.subscribe("/#");
        client.subscribe("topic2");
        client.subscribe("topic1");
        client.publish("topic3", "datazz");
        client.unsubscribe("topic1");
    }
}
