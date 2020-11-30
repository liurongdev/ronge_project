package com.ronge.demo.Component;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ronge.demo.model.KafkaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaComponent {

    private static final Logger logger= LoggerFactory.getLogger(KafkaComponent.class);

//    @Autowired
////    private KafkaTemplate<String,String> kafkaTemplate;


    public void sendMessage(KafkaMessage kafkaMessage) throws JsonProcessingException {
        //kafkaTemplate.send("test-topic",JackSonUtils.convertObjectToString(kafkaMessage));
        logger.info("成功发送kafka消息:{}",kafkaMessage);
    }


//    @KafkaListener(topics = {"test-topic"},groupId = "test-consumer")
//    public void onMessage(ConsumerRecord<String,String> record){
//        logger.info("成功接收kafka消息:{}",record);
//    }

}