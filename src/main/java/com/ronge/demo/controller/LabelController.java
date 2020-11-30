package com.ronge.demo.controller;


import com.ronge.demo.constant.ResponseContants;
import com.ronge.demo.service.LabelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liurong
 * @date 2020/10/01 22:16
 */
@RequestMapping("/rest/v1/label")
@RestController
public class LabelController {

    private static final Logger loggger = LoggerFactory.getLogger(LabelController.class);

    @Autowired
    private LabelService labelService;

    @GetMapping(value="/getAllLabel")
    public Object getLabel(){
        Map<String, Object> response = new HashMap<>();
        try {
            response.put(ResponseContants.DATA, labelService.getAllLabel());
            response.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            loggger.error("获取标签信息失败:{}", e);
            response.put(ResponseContants.STATUS, ResponseContants.FAIL);
            response.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return response;
    }

    @GetMapping(value="/getAllLabelAndSubLabel")
    public Object getAllLabelAndSubLabel(){
        Map<String, Object> response = new HashMap<>();
        try {
            response.put(ResponseContants.DATA, labelService.getAllLabelAndSubLabel());
            response.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            loggger.error("获取标签及其子标签信息失败:{}", e);
            response.put(ResponseContants.STATUS, ResponseContants.FAIL);
            response.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return response;
    }
}
