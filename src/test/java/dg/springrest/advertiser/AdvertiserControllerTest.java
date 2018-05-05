package dg.springrest.advertiser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class AdvertiserControllerTest
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private MockMvc mvc;

    @Mock
    private AdvertiserRepository repository;

    @InjectMocks
    private AdvertiserController controller;

    private Advertiser advertiser1;
    private Advertiser advertiser2;

    @Before
    public void initAdvertisers()
    {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();

        advertiser1 = new Advertiser();
        advertiser1.setId(10001L);
        advertiser1.setName("Pepsi");
        advertiser1.setContactName("Indra Nooyi");
        advertiser1.setCreditLimit(2500f);

        advertiser2 = new Advertiser(10002L, "Coke", "Ahmet Muhtar Kent", 4000f);

        logger.info(advertiser1.toString());
        logger.info(advertiser2.toString());
    }

    @Test
    public void getAdvertiserNegative() throws Exception
    {
        mvc.perform(get("/api/advertiser")
                .header("id", "10gf001"))
                .andExpect(status().isBadRequest());

        mvc.perform(get("/api/advertiser")
                .header("id", "2424"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAdvertiserPositive() throws Exception
    {
        given(repository.findById(10001)).willReturn(advertiser1);

        mvc.perform(get("/api/advertiser")
                .header("id", "10001")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(10001)))
                .andExpect(jsonPath("$.name", is("Pepsi")))
                .andExpect(jsonPath("$.contactName", is("Indra Nooyi")))
                .andExpect(jsonPath("$.creditLimit", is(2500.0)));
    }

    @Test
    public void addAdvertiserPositive() throws Exception
    {

    }

    @Test
    public void addAdvertiserNegative() throws Exception
    {

    }

    @Test
    public void updateAdvertiserNegative() throws Exception
    {

    }

    @Test
    public void updateAdvertiserPositive() throws Exception
    {

    }

    @Test
    public void deleteAdvertiserNegative() throws Exception
    {

    }

    @Test
    public void deleteAdvertiserPositive() throws Exception
    {

    }

    @Test
    public void getCreditNegative() throws Exception
    {

    }

    @Test
    public void getCreditPositive() throws Exception
    {

    }

    @Test
    public void getAllAdvertisers() throws Exception
    {
        List<Advertiser> advertisers = Arrays.asList(advertiser1, advertiser2);

        given(repository.getAll()).willReturn(advertisers);

        mvc.perform(get("/api/advertiser/all"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].name", is("Pepsi")));
    }

    @Test
    public void deductCreditsNegative() throws Exception
    {

    }

    @Test
    public void deductCreditsPositive() throws Exception
    {

    }
}
