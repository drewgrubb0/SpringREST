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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class that tests the functionality of the entire project by positive/negative testing
 * the REST Requests.
 */
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

        advertiser2 = new Advertiser(10001L, "jason", "Indra Nooyi", 2500f);

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
        mvc.perform(post("/api/advertiser")
                .param("contactName", "jason")
                .param("name", "Gushers")
                .param("creditLimit", "5000"))
                .andExpect(status().isCreated());
    }

    @Test
    public void addAdvertiserNegative() throws Exception
    {
        mvc.perform(post("/api/advertiser")
                .param("id", "10gf01")
                .param("contactName", "jason"))
                .andExpect(status().isBadRequest());

        mvc.perform(post("/api/advertiser")
                .param("contactName", "jason"))
                .andExpect(status().isBadRequest());

        mvc.perform(post("/api/advertiser")
                .param("contactName", "jason")
                .param("name", "Gushers")
                .param("creditLimit", "-5000"))
                .andExpect(status().isBadRequest());

        mvc.perform(post("/api/advertiser")
                .param("contactName", "jason")
                .param("creditLimit", "5000"))
                .andExpect(status().isBadRequest());

        mvc.perform(post("/api/advertiser")
                .param("name", "Gushers")
                .param("creditLimit", "5000"))
                .andExpect(status().isBadRequest());

        mvc.perform(post("/api/advertiser")
                .param("contactName", "jason")
                .param("name", "Gushers")
                .param("creditLimit", "5fg00"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateAdvertiserNegative() throws Exception
    {
        given(repository.findById(10001)).willReturn(advertiser1);

        mvc.perform(put("/api/advertiser")
                .param("id", "10gf01")
                .param("contactName", "jason"))
                .andExpect(status().isBadRequest());

        mvc.perform(put("/api/advertiser")
                .param("id", "10001"))
                .andExpect(status().isBadRequest());

        mvc.perform(put("/api/advertiser")
                .param("contactName", "jason"))
                .andExpect(status().isBadRequest());

        mvc.perform(put("/api/advertiser")
                .param("id", "10001")
                .param("creditLimit", "354gf"))
                .andExpect(status().isBadRequest());

        given(repository.findById(10001)).willReturn(null);

        mvc.perform(put("/api/advertiser")
                .param("id", "10001")
                .param("contactName", "jason"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateAdvertiserPositive() throws Exception
    {
        given(repository.findById(10001)).willReturn(advertiser1);

        mvc.perform(put("/api/advertiser")
                .param("id", "10001")
                .param("contactName", "jason")
                .param("name", "Gushers")
                .param("creditLimit", "5000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(10001)))
                .andExpect(jsonPath("$.name", is("Gushers")))
                .andExpect(jsonPath("$.contactName", is("jason")))
                .andExpect(jsonPath("$.creditLimit", is(5000.0)));
    }

    @Test
    public void deleteAdvertiserNegative() throws Exception
    {
        mvc.perform(delete("/api/advertiser")
                .header("id", "10gf001"))
                .andExpect(status().isBadRequest());

        mvc.perform(delete("/api/advertiser")
                .param("id", "2424"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteAdvertiserPositive() throws Exception
    {
        given(repository.deleteById(10001)).willReturn(1);

        mvc.perform(delete("/api/advertiser")
                .param("id", "10001"))
                .andExpect(status().isOk());
    }

    @Test
    public void getCreditNegative() throws Exception
    {
        given(repository.findById(10001)).willReturn(advertiser1);

        mvc.perform(get("/api/advertiser/credit")
                .header("id", "100fsd01")
                .header("transactionCost", "5000")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mvc.perform(get("/api/advertiser/credit")
                .header("id", "10001")
                .header("transactionCost", "5dfg000")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mvc.perform(get("/api/advertiser/credit")
                .header("id", "10001")
                .header("transactionCost", "5000")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        given(repository.findById(2424)).willReturn(null);

        mvc.perform(get("/api/advertiser/credit")
                .header("id", "2424")
                .header("transactionCost", "5000")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getCreditPositive() throws Exception
    {
        given(repository.findById(10001)).willReturn(advertiser1);

        mvc.perform(get("/api/advertiser/credit")
                .header("id", "10001")
                .header("transactionCost", "500")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
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
        given(repository.findById(10001)).willReturn(advertiser1);

        mvc.perform(post("/api/advertiser/deduct")
                .param("creditsToDeduct", "500"))
                .andExpect(status().isBadRequest());

        mvc.perform(post("/api/advertiser/deduct")
                .param("id", "10001"))
                .andExpect(status().isBadRequest());

        mvc.perform(post("/api/advertiser/deduct")
                .param("id", "10001")
                .param("creditsToDeduct", "50f0"))
                .andExpect(status().isBadRequest());

        mvc.perform(post("/api/advertiser/deduct")
                .param("id", "10f01")
                .param("creditsToDeduct", "50f0"))
                .andExpect(status().isBadRequest());

        given(repository.findById(10001)).willReturn(null);

        mvc.perform(post("/api/advertiser/deduct")
                .param("id", "10001")
                .param("creditsToDeduct", "500"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deductCreditsPositive() throws Exception
    {
        given(repository.findById(10001)).willReturn(advertiser1);

        mvc.perform(post("/api/advertiser/deduct")
                .param("id", "10001")
                .param("creditsToDeduct", "500"))
                .andExpect(status().isOk());

        mvc.perform(post("/api/advertiser/deduct")
                .param("id", "10001")
                .param("creditsToDeduct", "5000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.creditLimit", is(0.0)));
    }
}
