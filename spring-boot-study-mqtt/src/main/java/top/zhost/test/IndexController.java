package top.zhost.test;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.zhost.test.bean.Cmd;

@RestController
public class IndexController {

    @RequestMapping(value = "/startClass")
    public String startClass(@RequestBody Cmd cmd) {
    	MqttPushClient.getInstance().publish("vb/task/"+cmd.getClientId(), "{\"cmd\":\"startClass\"}");
        return cmd.getClientId()+" startClass success";
    }
    @RequestMapping(value = "/startInspect")
    public String startInspect(@RequestBody Cmd cmd) {
    	MqttPushClient.getInstance().publish("vb/task/"+cmd.getClientId(), "{\"cmd\":\"startInspect\"}");
    	String clientId = cmd.getClientId();
        return clientId+" startInspect success";
    }
    @RequestMapping(value = "startAi")
    public String startAi(@RequestBody Cmd cmd) {
    	MqttPushClient.getInstance().publish("vb/task/"+cmd.getClientId(), "{\"cmd\":\"startAi\"}");
        return cmd.getClientId()+" startAi success";
    }
    @RequestMapping(value = "userTopic")
    public String userTopic(@RequestBody Cmd cmd) {
    	MqttPushClient.getInstance().publish(cmd.getTopic(), cmd.getContent());
        return "userTopic success";
    }
}
