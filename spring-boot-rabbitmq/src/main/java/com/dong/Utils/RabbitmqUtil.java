package com.dong.Utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author DongJian
 * @date Created in 2020/11/2 16:38
 * Utils: Intellij Idea
 * @description:
 * @version:1.0
 */
public class RabbitmqUtil {


    public static final ConnectionFactory connectionFactory;

    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("172.16.3.43");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
        // 虚拟主机
        connectionFactory.setVirtualHost("/ems");
    }

    /**
     * @return com.rabbitmq.client.Connection
     * @Description rabbitmq连接
     * @Author dongjian 2020/11/2 16:44
     * @Param []
     **/
    public static Connection getConnection() {
        //1.创建连接mq的工厂对象
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Channel channel, Connection connection) {
        // 关闭队列和连接
        try {
            if (channel != null) channel.close();
            if (connection != null) connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
