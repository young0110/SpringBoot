package com.youngc.filesystem.controller;

import com.alibaba.fastjson.JSONObject;

public interface IBaseController {

    default JSONObject successJson() {
        JSONObject resultJson = new JSONObject(true);
        resultJson.put("status", 10000);
        resultJson.put("message", "Success！");
        return resultJson;
    }

    default JSONObject successJson(Object data) {
        JSONObject resultJson = new JSONObject(true);
        resultJson.put("status", 10000);
        resultJson.put("message", "Success！");
        resultJson.put("data", data);
        return resultJson;
    }

    default JSONObject errorJson() {
        JSONObject resultJson = new JSONObject(true);
        resultJson.put("status", -10000);
        resultJson.put("message", "Error！");
        return resultJson;
    }

    default JSONObject errorJson(String message) {
        JSONObject resultJson = new JSONObject(true);
        resultJson.put("status", -10000);
        resultJson.put("message", message);
        return resultJson;
    }

}
