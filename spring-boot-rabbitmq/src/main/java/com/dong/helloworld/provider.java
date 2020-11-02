package com.dong.helloworld;

import com.dong.Utils.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author DongJian
 * @date Created in 2020/10/29 16:56
 * Utils: Intellij Idea
 * @description:
 * @version:1.0
 */
public class provider {

    @Test
    public void sendMessage() throws IOException, TimeoutException {
        // 连接对象
        Connection connection = RabbitmqUtil.getConnection();
        // 连接通道
        Channel channel = connection.createChannel();
        // 参数1：队列名称，如果队列不存在则自动创建
        // 参数2：原来定义队列数据是否要持久化，true持久化 false 不持久化
        // 参数3：exculsive是否要独占队列，true独占队列 false不独占队列
        // 参数4：autoDelete 是否在消费完成后自动删除队列，true自动删除 false不自动删除
        // 参数5：额外附加参数
        channel.queueDeclare("hello", false, false, false, null);

        // 发布消息
        // 参数1：Exchange 交换机的名称 参数2：队列名称 参数3：传递消息持久化参数设置 参数4：消息的具体内容
        channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello world".getBytes());
        // 关闭连接
        RabbitmqUtil.closeConnection(channel,connection);
    }


}
