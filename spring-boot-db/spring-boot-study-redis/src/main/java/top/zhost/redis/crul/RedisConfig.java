package top.zhost.redis.crul;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
//@EnableRedisRepositories
public class RedisConfig {
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(
			RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		RedisSerializer<String> keySerializer = new StringRedisSerializer();
		RedisSerializer<Object> valueSerializer = new GenericJackson2JsonRedisSerializer();
		template.setConnectionFactory(factory);
		template.setHashKeySerializer(keySerializer);
		template.setKeySerializer(keySerializer);
		template.setValueSerializer(valueSerializer);
		template.setHashValueSerializer(valueSerializer);
		return template;
	}

}
