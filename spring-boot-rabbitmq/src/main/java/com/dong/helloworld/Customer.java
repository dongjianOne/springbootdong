package com.dong.helloworld;

import com.dong.Utils.RabbitmqUtil;
import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

/**
 * @author DongJian
 * @date Created in 2020/11/2 13:57
 * Utils: Intellij Idea
 * @description: rabbitMq hello world 模型 可以应用于注册或者登陆时的发送短信功能
 * 或者加积分的操作
 * @version:1.0
 */
public class Customer {

    public static void main(String[] args) throws Exception{
        // 创建连接
        /*ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("172.16.3.43");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems"); //虚拟主机
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
        Connection connection = connectionFactory.newConnection();*/
        Connection connection = RabbitmqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello", false, false, false, null);

        // 参数1：队列名称 参数2：开启消息的自动确认机制 参数三：消费时的回调接口
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            @Override
            // body:消息队列中取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("===========================" + new String(body)+ LocalDateTime.now());
            }
        });
        // 一般开启，持续对消息队列进行监听
//        channel.close();
//        connection.close();
    }
}
