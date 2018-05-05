package dg.springrest.advertiser;

import dg.springrest.SpringAdvertiseApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(AdvertiserController.class)
@AutoConfigureWebMvc
@ContextConfiguration(classes = {SpringAdvertiseApplication.class})
public class AdvertiserControllerTest
{
    //Error creating bean with name 'advertiserRepository' defined in file

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdvertiserController controller;

    @Test
    public void postAdvertiserPositive()
    {
        Advertiser advertiser = new Advertiser();
        advertiser.setName("Apple");
        advertiser.setContactName("Steve Jobs");
        advertiser.setCreditLimit(4500f);
    }

    @Test
    public void getAdvertiserNegative() throws Exception
    {

    }

    @Test
    public void getAdvertiserPositive() throws Exception
    {

    }
}
