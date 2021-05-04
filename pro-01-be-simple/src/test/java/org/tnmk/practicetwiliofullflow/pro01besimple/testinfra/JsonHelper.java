package org.tnmk.practicetwiliofullflow.pro01besimple.testinfra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonHelper {
  private final ObjectMapper objectMapper;

  public JsonHelper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public String toJson(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException(e);
    }
  }

  public <T> T toObject(String jsonString, Class<T> resultClass) {
    try {
      return objectMapper.readValue(jsonString, resultClass);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException("Cannot parse json. Rootcause:" + e.getMessage() + ". Json: \n" + jsonString, e);
    }
  }

  public <T> T toObject(String jsonString, JavaType resultType) {
    try {
      return objectMapper.readValue(jsonString, resultType);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException("Cannot parse json. Rootcause:" + e.getMessage() + ". Json: \n" + jsonString, e);
    }
  }

  public ObjectMapper getObjectMapper() {
    return objectMapper;
  }
}
