package com.youcloud;

import com.youcloud.pojo.Sources;
import com.youcloud.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    CircleService circleService;
    @Autowired
    UserService userService;
    @Autowired
    FileHolderService fileHolderService;
    @Autowired
    SourcesService sourcesService;
    @Autowired
    UsersCirclesService usersCirclesService;


    @Test
    void contextLoads() {
        List<Sources> sources = sourcesService.querySourcesByID(123456);
        Iterator<Sources> iterator = sources.iterator();
        System.out.println("11111");
        if (sources.size() == 0){
            System.out.println("okkkk");
        }
        if (iterator.hasNext()){
            System.out.println("22222");
            System.out.println("3333"+iterator.next());
        }
        System.out.println("444444");
    }

}
