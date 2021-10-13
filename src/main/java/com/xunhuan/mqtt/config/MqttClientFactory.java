package com.xunhuan.mqtt.config;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: mqtt
 * @description:
 * @author: gyf
 * @create: 2021-10-12 16:34
 **/

@Component
@Slf4j
public class MqttClientFactory {

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.host-url}")
    private String url;

    @Value("${mqtt.clientId}")
    private String clientId;

    @Value("${mqtt.default-topic]")
    private String defaultTopic;

    @Value("${mqtt.host-url}")
    private String hostUrl;

    @Value("${mqtt.timeout}")
    private Integer timeout;

    @Value("${mqtt.keepalive}")
    private Integer keepalive;

    @Autowired
    private MqttPushClient mqttPushClient;

    public MqttPushClient getThresholdSenderClient(MqttCallback callback) {
        String clientId = "thresholdSenderClient";
        mqttPushClient.connectWithCallBack(hostUrl, clientId, username, password, timeout, keepalive,callback);
        log.info("创建客户端{}成功", clientId);
        return mqttPushClient;
    }
}
