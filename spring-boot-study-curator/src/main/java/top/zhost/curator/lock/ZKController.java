package top.zhost.curator.lock;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zk")
public class ZKController {

	@Autowired
	private DistributedLockByCurator distributedLockByCurator;

	@GetMapping("/lock")
	public Boolean getLock() throws Exception {
//		distributedLockByCurator.acquireDistributedLock("mylock");
//		distributedLockByCurator.releaseDistributedLock("mylock");
//		distributedLockByCurator.acquireDistributedLock("mylock");
//		distributedLockByCurator.releaseDistributedLock("mylock");
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					distributedLockByCurator.acquireDistributedLock("mylock");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					distributedLockByCurator.releaseDistributedLock("mylock");
				}
			}).start();
		}
		return true;
	}
}