package mytest;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Administrator on 2017/3/4.
 */
public class QueueSent {
    public static final int SEND_NUM = 100;

    public static final String BROKER_URL = "tcp://localhost:61616";

    public static final String DESTINATION = "queue.send.test";

    public static void run() throws JMSException {

        QueueConnection connection = null;
        QueueSession session = null;
        QueueConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
        connection = factory.createQueueConnection();
//      connection.start
        session = connection.createQueueSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(DESTINATION);
        QueueSender sender = session.createSender(queue);
        sender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        sendMessage(sender, session);
        session.commit();
        session.close();
        connection.close();
    }

    private static void sendMessage(QueueSender sender, QueueSession session) throws JMSException {
        for (int i=0;i<100;i++){
            String message = "xxxxxxxxxxx"+i;
            MapMessage map = session.createMapMessage();
            map.setString("message", message);
            map.setLong("age", 1231312);
            sender.send(map);
        }

    }


    public static void main(String[] args) throws JMSException {
        QueueSent.run();
    }
}
