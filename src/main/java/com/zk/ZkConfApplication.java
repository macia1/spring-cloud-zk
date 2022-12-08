package com.zk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zenggs
 * @Date 2022/12/8
 */
@SpringBootApplication
@RestController
@RequestMapping("/zk")
@RefreshScope
public class ZkConfApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZkConfApplication.class, args);
    }

    @Autowired
    Environment env;

    @RequestMapping("/ping")
    public Map ping(@RequestParam("name")String name){
        String property = env.getProperty(name);
        System.out.println("prop:" + name + ", val:" + property);
        Map<String,String> res = new HashMap<>(16);
        res.put("zk-" + name,env.getProperty(name));
        return res;
    }
}
