package org.project.onboarding;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TestObjectMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public <T> T toObject(String contentAsString, Class<T> tClass) throws IOException {
        return objectMapper.readValue(contentAsString, tClass);
    }

    public <T> String toJson(T dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

}
