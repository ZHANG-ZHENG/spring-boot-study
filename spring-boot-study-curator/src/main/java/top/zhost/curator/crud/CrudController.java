package top.zhost.curator.crud;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zk")
public class CrudController {

    @Autowired
    private BaseCuratorDao baseCuratorDao;
	
    @RequestMapping(value = "/add")
    public String add() {
    	baseCuratorDao.createPersistentNode("/zktest/p1", "zz nihao a".getBytes());
        return "add";
    }
    
    @RequestMapping(value = "/set")
    public String set() {
    	String data = "zz nihao "+System.currentTimeMillis();
    	baseCuratorDao.setNode("/zktest/p1",data.getBytes());
    	return "set";
    } 
    
    @RequestMapping(value = "/get")
    public String get() {
    	return baseCuratorDao.getNode("/zktest/p1");
    }  
    
    @RequestMapping(value = "/watch")
    public String watch() {
    	baseCuratorDao.watch("/zktest/p1");
    	return "watch";
    }    
}
