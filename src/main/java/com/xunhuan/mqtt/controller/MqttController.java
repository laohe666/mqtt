package com.xunhuan.mqtt.controller;

import com.xunhuan.mqtt.config.MqttClientFactory;
import com.xunhuan.mqtt.config.MqttPushClient;
import com.xunhuan.mqtt.config.PushCallBack;
import com.xunhuan.mqtt.mqttcallback.DefaultCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mqtt
 * @description:
 * @author: gyf
 * @create: 2021-10-12 17:23
 **/
@RestController
public class MqttController {
    @Autowired
    private MqttClientFactory mqttClientFactory;



    @Autowired
    private MqttPushClient mqttPushClient;
    @PostMapping("send")
    public String sendMessage(String message){
        System.out.println(mqttPushClient.hashCode());
        System.out.println(message);
        PushCallBack callback = new PushCallBack();
        MqttPushClient thresholdSenderClient = mqttClientFactory.getThresholdSenderClient(callback);
        System.out.println(thresholdSenderClient.hashCode());
        thresholdSenderClient.publish(0,"mqttTest",message);
        return "发布成功";
    }


}
