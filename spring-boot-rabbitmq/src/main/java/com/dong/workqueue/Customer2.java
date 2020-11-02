package com.dong.workqueue;

import com.dong.Utils.RabbitmqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author DongJian
 * @date Created in 2020/11/2 17:02
 * Utils: Intellij Idea
 * @description:
 * @version:1.0
 */
public class Customer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("work", true, false, false, null);
        // 消费消息
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
