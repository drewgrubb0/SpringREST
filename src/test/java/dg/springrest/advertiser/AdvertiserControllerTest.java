package dg.springrest.advertiser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test runs for AdvertiserController RESTful Requests
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AdvertiserController.class)
public class AdvertiserControllerTest
{
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdvertiserController controller;

    @Test
    public void getAdvertiser()
    {
        Advertiser advertiser = new Advertiser("Pepsi", "Indra Nooyl", 100.0);

       // mvc.perform();
    }
}
