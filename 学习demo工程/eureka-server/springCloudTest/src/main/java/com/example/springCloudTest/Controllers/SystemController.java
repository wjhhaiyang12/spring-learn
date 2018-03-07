//package com.example.springCloudTest.Controllers;
//
//import com.netflix.discovery.DiscoveryClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class SystemController {
//
//    @Autowired
//    DiscoveryClient discoveryClient;
//
//    @RequestMapping(value = "/stopheartbeat")
//    String index(){
//        discoveryClient.shutdown();
//        return "666";
//    }
//
//}
