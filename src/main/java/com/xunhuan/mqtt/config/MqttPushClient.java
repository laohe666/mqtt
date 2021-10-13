package com.xunhuan.mqtt.config;

import com.xunhuan.mqtt.mqttcallback.DefaultCallback;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @program: mqtt
 * @description:
 * @author: gyf
 * @create: 2021-10-12 16:26
 **/
@Component
@Slf4j
public class MqttPushClient {
    @Autowired
    private DefaultCallback defaultCallback;

    private MqttClient client ;

    /**
     * 客户端连接
     *
     * */

    public void connect(String host, String clientId, String username, String password, int timeout, int keepalive) {
        try {
            client = new MqttClient(host, clientId, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(timeout);
            options.setKeepAliveInterval(keepalive);
            options.setAutomaticReconnect(true);
            client.setCallback(new DefaultCallback());
            client.connect(options);
        } catch (Exception e) {
            System.out.println("mqtt客户端连接失败！！！！！！！");
        }
    }

    /**
     * 发布
     * */
    public void publish(int qos, String topic, String message) {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(false);
        mqttMessage.setPayload(message.getBytes());
        MqttTopic mqttTopic = client.getTopic(topic);
        //如果主题不存在
        if (null == mqttTopic) {
            System.out.println(topic + "主题不存在！");
            return;
        }
        MqttDeliveryToken token;
        try {
            token = mqttTopic.publish(mqttMessage);
            token.waitForCompletion();
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }



    /**
     *
     * 订阅一个主题
     * */
    public void subscribe(String topic, int qos) {
            try {
                client.subscribe(topic, qos);
                System.out.println(client.getClientId() + "---订阅了主题---" + topic);
            } catch (MqttException e) {
                e.printStackTrace();
            }

    }

    public void connectWithCallBack(String host, String clientId, String username, String password, int timeout, int keepalive, MqttCallback callback) {

       try {
            client = new MqttClient(host, clientId, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(timeout);
            options.setKeepAliveInterval(keepalive);
            options.setAutomaticReconnect(true);
            try {
                client.setCallback(callback);
                client.connect(options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
