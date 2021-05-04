package org.tnmk.practicetwiliofullflow.pro01besimple.common.rest;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.tnmk.practicetwiliofullflow.pro01besimple.datetime.DateTimeResponse;
import org.tnmk.practicetwiliofullflow.pro01besimple.testinfra.BaseIntegrationTest;
import org.tnmk.practicetwiliofullflow.pro01besimple.testinfra.MvcResultHelper;

import java.lang.invoke.MethodHandles;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class DateTimeControllerTest extends BaseIntegrationTest {

  private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  @Autowired
  private MockMvc mvc;

  @Autowired
  private MvcResultHelper mvcResultHelper;

  @Test
  void getSentryConfig_shouldReturn_NotEmptyValues() throws Exception {
    // Given

    //Africa/Cairo
    //Pacific/Honolulu
    //America/New_York
    //America/Sao_Paulo
    MvcResult mvcResult = mvc
        //when
        .perform(get("/date-time")
                .contentType(MediaType.APPLICATION_JSON)
            //            .content(JsonHelper.toJson(fakeRequest))
        )

        //then        .andExpect(status().isOk())
        .andReturn();

    // When creating a message the first time, it already has dateUpdated value. So we need to compare dateUpdated equals dateCreated.
    // I have to use this approach because could find any solution to compare those values by using jsonPath.

    DateTimeResponse response = mvcResultHelper.convertJsonResult(mvcResult, DateTimeResponse.class);
    ZonedDateTime beginDateTimeAtLocalZoneId = response.getDateA().atZoneSameInstant(ZoneId.systemDefault());
    ZonedDateTime endDateTimeAtLocalZoneId = response.getDateB().atZoneSameInstant(ZoneId.systemDefault());
    logger.info("DateTimeResponse:\n begin: {}, end: {}", beginDateTimeAtLocalZoneId, endDateTimeAtLocalZoneId);
  }
}
