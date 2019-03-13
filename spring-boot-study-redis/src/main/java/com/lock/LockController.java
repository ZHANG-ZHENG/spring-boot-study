package com.lock;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LockController {
	@Autowired
	DistributedLockHandler distributedLockHandler;

    @RequestMapping("/locktest")
    private String hello() {
    	System.out.println("start service 1");
    	Lock lock=new Lock("redis-lock","my-redis-lock");
		if(distributedLockHandler.tryLock(lock)){
			System.out.println("start service 2");
			try{
				Thread.sleep(10000);
			}catch(Exception e){
				System.out.println(e);
			}
			
		    distributedLockHandler.releaseLock(lock);
		    System.out.println("end service");
		}
        return "Hello World!";
    }


}
