package top.zhost.curator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping(value = "/test")
    public String index() {

        return "Hello World spring-boot-study-curator!!!";
    }
}
