package com.dong.workqueue;

import com.dong.Utils.RabbitmqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author DongJian
 * @date Created in 2020/11/2 16:58
 * Utils: Intellij Idea
 * @description:
 * @version:1.0
 */
public class Customer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);//每次只消费一个
        channel.queueDeclare("work", true, false, false, null);
        // 消费消息 关闭自动确认
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者1：" + new String(body));
                // 参数1：具体是那个消息 参数2：是否同时确认多个消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
