package top.zhost.redis.crul;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisBaseDao {


	@Autowired
	private RedisTemplate<String, Object> redisTemplate; // 用于对象存储为json
	@Autowired
	private StringRedisTemplate stringRedisTemplate;// 用于string存储
	
	public void set(String key, Object obj) {
		redisTemplate.opsForValue().set(key, obj);
	}
	public void delete(String key) {
		redisTemplate.delete(key);
	}
	
	public Set<String> getKeys(String keyPrefix){
		Set<String> keys = redisTemplate.keys(keyPrefix + "*");
		return keys;
	}
	
	public <T> List<T> getList(String keyPrefix, Class<T> clazz) {
		Set<String> keys = redisTemplate.keys(keyPrefix + "*");
		if (keys == null || keys.size() < 1) {
			return new ArrayList<T>();
		}
		List<T> listObj = new ArrayList<T>();
	
		listObj = (List<T>) redisTemplate.opsForValue().multiGet(keys);
		return listObj;
	}


}