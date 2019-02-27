package com;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.MyConfig;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MyConfigTest {

    @Autowired
    private MyConfig myConfig;

    @Test
    public void testConfig() {
        System.out.println("webName: "+myConfig.getName()+
                ", webVersion: "+ myConfig.getVersion()+", webAuthor: "+myConfig.getAuthor());
    }
}
