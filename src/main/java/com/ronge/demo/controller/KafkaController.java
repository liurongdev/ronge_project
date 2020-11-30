package com.ronge.demo.controller;

import com.ronge.demo.Component.KafkaComponent;
import com.ronge.demo.constant.ResponseContants;
import com.ronge.demo.model.KafkaMessage;
import com.ronge.demo.utils.JackSonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping(value="/rest/v1/kafka")
@RestController
public class KafkaController {

    private static final Logger logger= LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private KafkaComponent kafkaComponent;

    @PostMapping(value="/send_message")
    public Object sendMessage(@RequestBody String message){
         Map<String,Object> response=new HashMap<>();
         try{
             KafkaMessage kafkaMessage= (KafkaMessage) JackSonUtils.convertStringToObject(message,KafkaMessage.class);
             kafkaComponent.sendMessage(kafkaMessage);
             response.put(ResponseContants.STATUS,ResponseContants.SUCCESS);
         }catch (Exception e){
             logger.error("kafka消息发送异常:{}",e);
             response.put(ResponseContants.MESSAGE,e.getMessage());
             response.put(ResponseContants.STATUS,ResponseContants.FAIL);
         }
         return response;
    }
}
