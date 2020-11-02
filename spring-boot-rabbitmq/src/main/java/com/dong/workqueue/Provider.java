package com.dong.workqueue;

import com.dong.Utils.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author DongJian
 * @date Created in 2020/11/2 16:37
 * Utils: Intellij Idea
 * @description:
 * @version:1.0
 */
public class Provider {


    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtil.getConnection();
        Channel channel = connection.createChannel();
        // 通过通道声明队列
        channel.queueDeclare("work", true, false, false, null);
        // 生产消息
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", "work", true, null, (i + "hello work queue").getBytes());
        }
        // 关闭资源
        RabbitmqUtil.closeConnection(channel, connection);
    }
}
