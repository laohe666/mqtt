package com.xunhuan.mqtt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @program: mqtt
 * @description:
 * @author: gyf
 * @create: 2021-10-12 15:25
 **/
@Configuration
public class MqttConfig {
    /**
     * 发布的Bean名称
     * */
    public static final String MQTT_NAME = "mqttsend";

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

    /**
     * MQTT连接选项
     * */

    @Bean
    public MqttPushClient getMqttPushClient(){
        Random random = new Random();
        int i = random.nextInt(10000);
        mqttPushClient.connect(hostUrl, clientId + i, username, password, timeout, keepalive);
        mqttPushClient.subscribe("mqttTest", 0);
        return mqttPushClient;

    }

}
