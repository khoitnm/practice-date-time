package org.tnmk.practicetwiliofullflow.pro01besimple.testinfra;

import com.fasterxml.jackson.databind.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandles;
import java.util.List;

@Component
public class MvcResultHelper {
  private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  private final JsonHelper jsonHelper;

  public MvcResultHelper(JsonHelper jsonHelper) {
    this.jsonHelper = jsonHelper;
  }

  public <T> T convertJsonResult(MvcResult mvcResult, Class<T> clazz) throws UnsupportedEncodingException {
    String resultJson = mvcResult.getResponse().getContentAsString();
    T result = jsonHelper.toObject(resultJson, clazz);
    return result;
  }

  public <T> List<T> toList(MvcResult mvcResult, Class<T> elementClass) throws UnsupportedEncodingException {
    JavaType type = jsonHelper.getObjectMapper().getTypeFactory().constructCollectionType(List.class, elementClass);
    String resultJson = mvcResult.getResponse().getContentAsString();
    logger.info("JSON: \n{}", resultJson);

    List<T> result = jsonHelper.toObject(resultJson, type);
    return result;
  }
}
