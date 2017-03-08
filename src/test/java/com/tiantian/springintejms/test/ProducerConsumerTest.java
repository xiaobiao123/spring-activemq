package com.tiantian.springintejms.test;

import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tiantian.springintejms.service.ProducerService;

/**
 * http://elim.iteye.com/blog/1893676
 *
 * <h1>消息类型  queue/topic
 * <p1>spring提供的ConnectionFactory类型有SingleConnectionFactory/CachingConnectionFactory
 * <p2>ActiveMQ提供的PooledConnectionFactory

 * <h2>消息监听及消息监听容器
 * <p1>配置消息监听容器MessageListenerContainer必须有三个参数ConnectionFactory、MessageListener,Destination
 * spring提供的有两种监听容器SimpleMessageListenerContainer、DefaultMessageListenerContainer
 * <p2>消息监听MessageListener、SessionAwareMessageListener和MessageListenerAdapter（主要作用是将接收到的消息进行类型转换）

 * <h3>消息转换器MessageConverter
 * <p>spring默认实现的消息转换器SimpleMessageConverter

 * <h4>事物管理
 * <p1>只需要在定义对应的消息监听容器时指定其sessionTransacted属性为true(仅支持消息，不支持消息与数据库混合使用)
 * <p2>使用jtaTransactionManager配置事物
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ProducerConsumerTest {

    @Autowired
    private ProducerService producerService;
    @Autowired
    @Qualifier("queueDestination")
    private Destination destination;

    @Test
    public void testSend() {
        for (int i = 0; i < 2; i++) {
            producerService.sendMessage(destination, "你好，生产者！这是消息：" + (i + 1));
        }
    }

}
