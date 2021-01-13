package top.zhost.redis.crul;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping("/sale/goods")
public class TestController {
	@Autowired
	private RedisDao redisDao;
	@Autowired
	private RedisBaseDao redisBaseDao;

    @RequestMapping("/addGoods")
    private String addGoods() {
    	Goods goods = new Goods();
    	goods.setAddress("福州");
    	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    	redisBaseDao.set("goods:"+uuid, goods);
 

    	return "ok";
    }
    
    @RequestMapping("/getGoods")
    private List<Goods> getGoods() {

    	List<Goods> list =  redisBaseDao.getList("goods", Goods.class);
    	
    	return list;
    }    
	

    @RequestMapping("/del")
    private String del() {
    	String key = "123";
    	redisBaseDao.delete(key);
    	return "ok";
    }    
}
