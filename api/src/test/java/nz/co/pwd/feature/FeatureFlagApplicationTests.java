package nz.co.pwd.feature;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import nz.co.pwd.feature.api.FeatureFlagApi;
import nz.co.pwd.feature.api.FeatureFlagController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class FeatureFlagApplicationTests {
	private final static String featureBaseUrl = "/api/vi/feature";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private FeatureFlagController candidate;

	@Test
	void When_CreateFeatureFlag_Then_Success() throws Exception {
		// setup
		final ZoneId zoneId = ZoneId.systemDefault();
		final LocalDateTime current = Instant.now().atZone(zoneId).toLocalDateTime();
		final FeatureFlagApi request = FeatureFlagApi.builder()
																 .description("feature-a")
																 .expiresOn(current.plusMonths(3))
																 .build();

		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(request);
		System.out.println(json);

		// act
		MvcResult mvcResult =
				this.mockMvc.perform(
								post("/api/v1/layout")
										.contentType(MediaType.APPLICATION_JSON)
										.content(json))
						.andDo(print())
						.andExpect(status().isOk())
						.andReturn();

		// verify
		assertEquals("application/json", mvcResult.getResponse().getContentType());
		FeatureFlagApi responseApi =
				mapper.readValue(mvcResult.getResponse().getContentAsString(),
						FeatureFlagApi.class);

		System.out.println(mvcResult.getResponse().getContentAsString());
	}

}
