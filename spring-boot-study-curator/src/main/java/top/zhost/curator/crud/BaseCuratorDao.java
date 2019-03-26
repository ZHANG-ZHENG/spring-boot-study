package top.zhost.curator.crud;

import java.util.concurrent.CountDownLatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseCuratorDao {

	private static final Logger logger = LoggerFactory
			.getLogger(BaseCuratorDao.class);

	@Autowired
	private CuratorFramework curatorFramework;

	// https://www.cnblogs.com/haoxinyue/p/6561896.html
	public void createPersistentNode(String path, byte[] data) {
		try {
			curatorFramework.create().creatingParentsIfNeeded()
					.withMode(CreateMode.PERSISTENT).forPath(path, data);
		} catch (Exception e) {
			logger.info("failed to createPersistentNode:{}.", e);
		}

	}
	
	public void setNode(String path, byte[] data) {
		try {
			curatorFramework.setData().forPath(path, data);
		} catch (Exception e) {
			logger.info("failed to createPersistentNode:{}.", e);
		}
	}

	public String getNode(String path) {
		byte[] data = null;
		try {
			data = curatorFramework.getData().forPath(path);
		} catch (Exception e) {
			logger.info("failed to createPersistentNode:{}.", e);
		}
		return new String(data);
	}

	public void watch(String path) {
		try {
	        PathChildrenCache cache = new PathChildrenCache(curatorFramework, path, false);
	        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
	        cache.getListenable().addListener((client, event) -> {
	        	logger.info("event {}", event);
	            if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)) {
	                //String path = event.getData().getPath();
	                logger.info("event:{}", event.getData());
	
	            }
	        });
		} catch (Exception e) {
			logger.info("failed to createPersistentNode:{}.", e);
		}
	
	}

}
