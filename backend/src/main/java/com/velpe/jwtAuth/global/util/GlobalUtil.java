package com.velpe.jwtAuth.global.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GlobalUtil {

    public Map getListContent(Object list){
        Map<String, Object> content = new HashMap<>();
        content.put("content", list);

        return content;
    }
}
