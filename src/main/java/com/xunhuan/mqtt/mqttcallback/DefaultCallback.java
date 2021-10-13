package com.xunhuan.mqtt.mqttcallback;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

/**
 * @program: mqtt
 * @description:
 * @author: gyf
 * @create: 2021-10-12 16:33
 **/
@Component
@Slf4j
public class DefaultCallback implements MqttCallback {


    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("订阅者接收到消息 ----- " + mqttMessage );
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
