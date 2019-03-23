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
public class RedisDao {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;// 用于string存储

	@Autowired
	private RedisTemplate<String, Object> redisTemplate; // 用于对象存储为json
	/**
	 * 一周有多少秒
	 */
	private static final long WEEK_SECONDS = 7 * 24 * 60 * 60;

	/**
	 * 将 key，value 存放到redis数据库中，默认设置过期时间为一周
	 *
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value) {
		stringRedisTemplate.opsForValue().set(key,
				JsonUtil.convertObj2String(value), WEEK_SECONDS,
				TimeUnit.SECONDS);
	}

	/**
	 * 将 key，value 存放到redis数据库中，设置过期时间单位是秒
	 *
	 * @param key
	 * @param value
	 * @param expireTime
	 */
	public void set(String key, Object value, long expireTime) {
		stringRedisTemplate.opsForValue()
				.set(key, JsonUtil.convertObj2String(value), expireTime,
						TimeUnit.SECONDS);
	}

	/**
	 * 判断 key 是否在 redis 数据库中
	 *
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return stringRedisTemplate.hasKey(key);
	}

	/**
	 * 获取与 key 对应的对象
	 * 
	 * @param key
	 * @param clazz
	 *            目标对象类型
	 * @param <T>
	 * @return
	 */
	public <T> T get(String key, Class<T> clazz) {
		String s = get(key);
		if (s == null) {
			return null;
		}
		return JsonUtil.convertString2Obj(s, clazz);
	}

	public <T> List<T> getList(String key, Class<T> clazz) {
		Set<String> keys = stringRedisTemplate.keys(key + "*");
		if (keys == null || keys.size() < 1) {
			return null;
		}
		List<T> listObj = new ArrayList<T>();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String keyOne = it.next();
			String valOne = get(keyOne);
			T t = JsonUtil.convertString2Obj(valOne, clazz);
			listObj.add(t);
		}
		return listObj;
	}

	/**
	 * 获取 key 对应的字符串
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	/**
	 * 删除 key 对应的 value
	 * 
	 * @param key
	 */
	public void delete(String key) {
		stringRedisTemplate.delete(key);
	}
}