import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

public class TestJMS {
    public static void main(String[] args) {
        ConnectionFactory connectionfactory = new ActiveMQConnectionFactory("hnyfadmin", "hnyfadmin", "tcp://localhost:61616");

        Connection connection  = null;
        try {
            connection = connectionfactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Topic topic = new ActiveMQTopic("user.onlines");

            MessageProducer msgProducer = session.createProducer(topic);

            Message message =session.createTextMessage("test");
            msgProducer.send(message);

//            session.commit(); //在事务性会话中，只有commit之后，消息才会真正到达目的地
//            session.close();
//            connection.close();

        } catch (JMSException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}
