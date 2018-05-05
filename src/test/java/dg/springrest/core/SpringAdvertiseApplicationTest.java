package dg.springrest.core;

import dg.springrest.advertiser.AdvertiserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAdvertiseApplicationTest
{
    //Error with test class: org.springframework.beans.factory.BeanDefinitionStoreException: Failed to read candidate component class:
    //file [C:\Users\Drew\Desktop\Software Engineering\Java\SpringAdvertise\out\production\classes\dg\springrest\core\SpringAdvertiseApplication.class]

    @Autowired
    private AdvertiserController controller;

    @Test
    public void getInfo()
    {
        assertThat(controller).isNotNull();
    }
}
