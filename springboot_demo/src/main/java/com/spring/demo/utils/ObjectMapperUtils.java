package com.spring.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.demo.model.common.ResultData;

import java.io.IOException;
import java.util.Collections;

public class ObjectMapperUtils {

    public static ObjectMapper objMapper = new ObjectMapper();

    static {

    }

    public static String obj2JsonStr(Object obj) throws JsonProcessingException {
        if (null != obj) return objMapper.writeValueAsString(obj);
        else return "{}";
    }

    public static Object jsonStr2Obj(String jsonStr, Class clazz) throws JsonMappingException, IOException {
        return objMapper.readValue(jsonStr, clazz);
    }

    public static void main (String[] args) throws JsonProcessingException{
        ResultData data = new ResultData(200, "success", Collections.EMPTY_LIST);
        System.out.println(obj2JsonStr(data));
    }

}