package com.mo.guard;

import com.mo.guard.model.entity.AppEntity;
import com.mo.guard.service.AppServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        properties = {
                "property.valueA=testA",
                "property.valueB=testB"
        },
        classes = {
                GuardApplication.class,
                AppServiceImpl.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class GuardApplicationTests {

    @Autowired
    AppServiceImpl appService;

    @Test
    public void contextLoads() {
        System.out.println("hello test?");
        AppEntity app = appService.findBySequence(1);
        System.out.println(app.getDisplayName());
    }

}
