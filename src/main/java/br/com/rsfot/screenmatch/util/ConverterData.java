package br.com.rsfot.screenmatch.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverterData implements IConvert {
    private  ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> clazz) {
        try {
            T t = mapper.readValue(json, clazz);
            return t;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
