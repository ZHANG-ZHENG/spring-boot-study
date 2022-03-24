package top.zhost.redis.crul;


import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping("/redis/test")
public class TestController {
	@Autowired
	private RedisDao redisDao;
	@Autowired
	private RedisBaseDao redisBaseDao;
	
    @RequestMapping("/add")
    private String set() {
    	Set<String> keys = redisBaseDao.getKeys("userBean" + "*");
    	if(keys.size() > 1000){
    		return "请先删除数据";
    	}
    	Long startTime = System.currentTimeMillis();
    	Long id = System.currentTimeMillis();
    	int i=0;
    	for(;i<100;i++){
    		Long uid = id + i;
        	User user = new User(uid,"name"+ i,16);
        	redisBaseDao.set("userBean:"+uid, user);
    	}    	

    	Long endTime = System.currentTimeMillis();

    	return "新增数据记录:"+i+" 用时:"+(endTime-startTime);
    }
    @RequestMapping("/get")
    private String get() {
    	Long startTime = System.currentTimeMillis();
    	List<User> list =  redisBaseDao.getList("userBean", User.class);
    	
		Long endTime = System.currentTimeMillis();
    	return "查询数据记录:"+list.size()+" 用时:"+(endTime-startTime);
    }
    @RequestMapping("/del")
    private String del() {
    	Set<String> keys = redisBaseDao.getKeys("userBean" + "*");
		Long startTime = System.currentTimeMillis();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			String key = it.next();
			redisBaseDao.delete(key);
		}
		Long endTime = System.currentTimeMillis();
    	return "删除数据记录:"+keys.size()+" 用时:"+(endTime-startTime);
    }    
}
