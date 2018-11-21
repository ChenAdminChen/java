import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

public class TestJMS {
    public static void main(String[] args) {
        ConnectionFactory connectionfactory = new ActiveMQConnectionFactory("hnyfadmin", "hnyfadmin", "tcp://192.168.1.168:61616");

        Connection connection  = null;
        try {
            connection = connectionfactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Topic topic = new ActiveMQTopic("ds.test.data");

            MessageProducer msgProducer = session.createProducer(topic);

            //生产
//            Message message =session.createTextMessage("[{\"index\":21,\"value\":5,\"dataGroupId\":11}," +
//                    "{\"index\":22,\"value\":1,\"dataGroupId\":11}," +
//                    "{\"index\":23,\"value\":155,\"dataGroupId\":11}," +
//                    "{\"index\":24,\"value\":455,\"dataGroupId\":11}," +
//                    "{\"index\":26,\"value\":1,\"dataGroupId\":11}," +
//                    "{\"index\":28,\"value\":1,\"dataGroupId\":11}," +
//                    "{\"index\":30,\"value\":51,\"dataGroupId\":11}," +
//                    "{\"index\":31,\"value\":111,\"dataGroupId\":11}," +
//                    "{\"index\":32,\"value\":300,\"dataGroupId\":11}," +
//                    "{\"index\":35,\"value\":1,\"dataGroupId\":11}]");

            //有错
//            Message message =session.createTextMessage("[{\"index\":21,\"value\":5,\"dataGroupId\":11}," +
//                    "{\"index\":22,\"value\":1,\"dataGroupId\":11}," +
//                    "{\"index\":23,\"value\":155,\"dataGroupId\":11}," +
//                    "{\"index\":24,\"value\":455,\"dataGroupId\":11}," +
//                    "{\"index\":26,\"value\":1,\"dataGroupId\":11}," +
//                    "{\"index\":28,\"value\":0,\"dataGroupId\":11}," +
//                    "{\"index\":30,\"value\":51,\"dataGroupId\":11}," +
//                    "{\"index\":31,\"value\":111,\"dataGroupId\":11}," +
//                    "{\"index\":32,\"value\":300,\"dataGroupId\":11}," +
//                    "{\"index\":35,\"value\":1,\"dataGroupId\":11}]");


            //停车
//            Message message =session.createTextMessage("[{\"index\":21,\"value\":5,\"dataGroupId\":11}," +
//                    "{\"index\":22,\"value\":1,\"dataGroupId\":11}," +
//                    "{\"index\":23,\"value\":155,\"dataGroupId\":11}," +
//                    "{\"index\":24,\"value\":455,\"dataGroupId\":11}," +
//                    "{\"index\":26,\"value\":1,\"dataGroupId\":11}," +
//                    "{\"index\":28,\"value\":1,\"dataGroupId\":11}," +
//                    "{\"index\":30,\"value\":51,\"dataGroupId\":11}," +
//                    "{\"index\":31,\"value\":111,\"dataGroupId\":11}," +
//                    "{\"index\":32,\"value\":100,\"dataGroupId\":11}," +
//                    "{\"index\":35,\"value\":0,\"dataGroupId\":11}]");

            Message message = session.createTextMessage("{\"id\":118,\"indexData\":{\"22\":1,\"21\":6,\"23\":155},\"time\":\"2018-07-04 10:40:47\"}");

            //停产
//            Message message =session.createTextMessage("[{\"index\":21,\"value\":1,\"dataGroupId\":11}," +
//                    "{\"index\":22,\"value\":0,\"dataGroupId\":11}," +
//                    "{\"index\":23,\"value\":128,\"dataGroupId\":11}," +
//                    "{\"index\":24,\"value\":288,\"dataGroupId\":11}," +
//                    "{\"index\":26,\"value\":0,\"dataGroupId\":11}," +
//                    "{\"index\":28,\"value\":0,\"dataGroupId\":11}," +
//                    "{\"index\":30,\"value\":40,\"dataGroupId\":11}," +
//                    "{\"index\":31,\"value\":88,\"dataGroupId\":11}," +
//                    "{\"index\":32,\"value\":100,\"dataGroupId\":11}," +
//                    "{\"index\":35,\"value\":0,\"dataGroupId\":11}]");

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
