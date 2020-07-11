package helloworld;

import com.rabbitmq.client.*;
//import com.sun.org.apache.xpath.internal.operations.String;
import helloworld.utils.RabbitMQUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        //ConnectionFactory connectionFactory = new ConnectionFactory();
        //connectionFactory.setHost("192.168.138.139");
        //connectionFactory.setPort(5672);
        //connectionFactory.setVirtualHost("/ems");
        //connectionFactory.setUsername("ems");
        //connectionFactory.setPassword("123");

        //创建连接对象
        //Connection connection = connectionFactory.newConnection();

        //通过工具类获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //通道绑定对象
        channel.queueDeclare("aa",true,false,false,null);

        //消费消息
        //参数1。消费哪个队列的消息
        //参数2. 开启消息的自动确认机制
        //参数3. 消费消息时的回调接口
        channel.basicConsume("aa",true,new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("===================================body:"+new String(body));
            }
        });
        //channel.close();
        //connection.close();
    }
}
