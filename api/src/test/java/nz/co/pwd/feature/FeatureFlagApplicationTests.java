package nz.co.pwd.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import nz.co.pwd.feature.api.model.FeatureApi;
import nz.co.pwd.feature.api.FeatureController;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@Disabled("there is a conflict between db-schema.sql auto population and H2 creation")
class FeatureFlagApplicationTests {
  private final static String featureBaseUrl = "/api/v1/feature";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private FeatureController candidate;

  @Test
  void When_CreateFeatureFlag_Then_Success() throws Exception {
    // setup
    final FeatureApi request = FeatureApi.builder()
                                   .displayName("feature a")
                                   .description("feature-a")
                                   .technicalName("feature.a")
                                   .build();

    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
    String json = writer.writeValueAsString(request);

    // act
    MvcResult mvcResult =
        this.mockMvc.perform(
                post(featureBaseUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

    // verify
    assertEquals("application/json", mvcResult.getResponse().getContentType());
    FeatureApi responseApi =
        mapper.readValue(mvcResult.getResponse().getContentAsString(),
            FeatureApi.class);
    assertEquals("feature-a", responseApi.description());
    assertEquals("feature.a", responseApi.technicalName());
  }

}
