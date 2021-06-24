package top.zhost.test;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    private UserRepository userRepository;
    
    //创建10个node localhost:8080/addUserNode
	@RequestMapping(path = "/addUserNode", method = RequestMethod.GET)
	public String addUserNode() {
	    int i = 0;
	    do {
	        UserNode user = new UserNode();
	        user.setName("zz" + RandomUtils.nextInt(1, 1000));
	        userRepository.save(user);
	        i += 1;
	    } while (i < 10);
	
	    return "ok";
	}
	//localhost:8080/getUserNodeList
	@RequestMapping(path = "/getUserNodeList", method = RequestMethod.GET)
	public List<UserNode> getUserNodeList() {
	    return (List<UserNode>) userRepository.findAll();
	}
}
