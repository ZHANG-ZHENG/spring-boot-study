package top.zhost.curator.crud;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
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

	// EPHEMERAL
	public void createEphemeralNode(String path, byte[] data) {
		try {
			curatorFramework.create().creatingParentsIfNeeded()
					.withMode(CreateMode.EPHEMERAL).forPath(path, data);
		} catch (Exception e) {
			logger.info("failed to createPersistentNode:{}.", e);
		}

	}
	
	public void delNode(String path, byte[] data) {
		try {
			curatorFramework.delete().forPath(path);
		} catch (Exception e) {
			logger.info("failed .", e);
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

	// https://blog.csdn.net/sqh201030412/article/details/51446434

	/**
	 * 
	 * @描述：第一种监听器的添加方式: 对指定的节点进行添加操作 仅仅能监控指定的本节点的数据修改,删除 操作 并且只能监听一次 --->不好
	 */
	public void watch(String path) {
		try {
			byte[] data = curatorFramework.getData()
					.usingWatcher(new Watcher() {
						@Override
						public void process(WatchedEvent event) {
							System.out.println(" 节点 监听器 : " + event);
							System.out.println(" 节点 监听器 : " + event.getPath());
						}
					}).forPath(path);
			System.out.println("节点数据: " + new String(data));
		} catch (Exception e) {
			logger.info("failed to createPersistentNode:{}.", e);
		}

	}

	/**
	 * 
	 * @描述：第三种监听器的添加方式: Cache 的三种实现 实践 Path
	 *                  Cache：监视一个路径下1）孩子结点的创建、2）删除，3）以及结点数据的更新。
	 *                  产生的事件会传递给注册的PathChildrenCacheListener。 Node
	 *                  Cache：监视一个结点的创建、更新、删除，并将结点的数据缓存在本地。 Tree Cache：Path
	 *                  Cache和Node Cache的“合体”，监视路径下的创建、更新、删除事件，并缓存路径下所有孩子结点的数据。
	 */

	public void watchAll(String path) {
		try {
			ExecutorService pool = Executors.newCachedThreadPool();
			PathChildrenCache childrenCache = new PathChildrenCache(curatorFramework,path, true);
			PathChildrenCacheListener childrenCacheListener = new PathChildrenCacheListener() {
				@Override
				public void childEvent(CuratorFramework client,
						PathChildrenCacheEvent event) throws Exception {
					System.out.println("开始进行事件分析:-----");
					ChildData data = event.getData();
					switch (event.getType()) {
					case CHILD_ADDED:
						System.out.println("CHILD_ADDED : " + data.getPath()
								+ "  数据:" + data.getData());
						break;
					case CHILD_REMOVED:
						System.out.println("CHILD_REMOVED : " + data.getPath()
								+ "  数据:" + data.getData());
						break;
					case CHILD_UPDATED:
						System.out.println("CHILD_UPDATED : " + data.getPath()
								+ "  数据:" + data.getData() + new String(data.getData()));
						break;
					default:
						break;
					}
				}
			};
			childrenCache.getListenable().addListener(childrenCacheListener);
			System.out.println("Register zk watcher successfully!");
			childrenCache.start(StartMode.POST_INITIALIZED_EVENT);

		} catch (Exception e) {
			logger.info("failed to createPersistentNode:{}.", e);
		}

	}

}
