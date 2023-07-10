package com.example.halagodainv.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(HttpStatus status, String message, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("timestamp", new Date());
            map.put("status", status.value());
            map.put("success", true);
            map.put("error", message);
            if (responseObj instanceof Collection) {
                Map<String, Object> mapItems = new HashMap<>();
                mapItems.put("items", responseObj);
                map.put("result", mapItems);
            } else {
                map.put("result", responseObj);
            }
            return new ResponseEntity<>(map, status);
        } catch (Exception e) {
            map.clear();
            map.put("timestamp", new Date());
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("success", false);
            map.put("error", e.getMessage());
            map.put("result", null);
            return new ResponseEntity<>(map, status);
        }
    }
}
