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
 
    @RequestMapping(value = "/addt")
    public String addt() {
    	String data = "zz nihao "+System.currentTimeMillis();
    	baseCuratorDao.createEphemeralNode("/zktesttmp/p"+System.currentTimeMillis(), data.getBytes());
        return "addt";
    }
    
    @RequestMapping(value = "/del")
    public String del() {
    	baseCuratorDao.delNode("/zktest/p1", "del".getBytes());
        return "del";
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
    @RequestMapping(value = "/watchall")
    public String watchall() {
    	baseCuratorDao.watchAll("/zktest");
    	return "watchall";
    }   
}
