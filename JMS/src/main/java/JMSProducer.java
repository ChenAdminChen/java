import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class JMSProducer {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionfactory = new ActiveMQConnectionFactory("hnyfadmin", "hnyfadmin", "tcp://192.168.1.168:61616");

        //创建与JMS服务的连接:ConnectionFactory被管理的对象，由客户端创建，用来创建一个连接对象
        Connection connection = connectionfactory.createConnection();
        /*
                确认消息的方式有如下三种：
                AUTO_ACKNOWLEDGE(自动通知)
                CLIENT_ACKNOWLEDGE(客户端自行决定通知时机)
                DUPS_OK_ACKNOWLEDGE(延时//批量通知)

         */

        /*
            打开会话，一个单独的发送和接受消息的线程上下文
            为true时，事务会话必须session.commit();
        */
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        JMSProducer qs = new JMSProducer();
//        qs.sendTextMsg(session,"helli text","user.onlines");

        Set<String> stringSet = new HashSet<String>();

        stringSet.add("1004@yf.com");
        stringSet.add("1005@yf.com");
        stringSet.add("1006@yf.com");
        stringSet.add("1007@yf.com");
        stringSet.add("1008@yf.com");
        stringSet.add("1009@yf.com");
        stringSet.add("1010@yf.com");

        System.out.println("sizw:" + stringSet.size());
//            stringSet.stream().map(m -> {
//                try {
//                    return qs.sendTextMsg(session,m,"user.onlines");
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            });

        //for (String s : stringSet) {
            qs.sendTextMsg(session, "getOnlineUsers", "user.online.query");
        //}


/*
            JMS jms = new JMS();//发送Object类型消息
            jms.setName("zhangsan");
            jms.setSex("男");
            qs.sendObj(session,jms,"queue.msgObj");
        */

//        session.commit(); //在事务性会话中，只有commit之后，消息才会真正到达目的地
//        session.close();
//        connection.close();
    }

    /*
       发送文本消息
     */
    public void sendTextMsg(Session session, String MsgContent, String name) throws JMSException {
        Topic queue = new ActiveMQTopic(name); // Topic topic=new ActiveMQTopic(name); 创建topic
        MessageProducer msgProducer = session.createProducer(queue);
        Message textMessage = session.createTextMessage(MsgContent);
        msgProducer.send(textMessage);
    }

    /*
       发送MAP类型消息
     */
    public void sendMap(Session session, MapMessage map, String name) throws JMSException {
        Topic topic = new ActiveMQTopic(name);   // Queue queue = new ActiveMQQueue(name);
        MessageProducer msgProducer1 = session.createProducer(topic);
        msgProducer1.send(map);
        //msgProducer1.setDeliveryMode(DeliveryMode.NON_PERSISTENT); 设置了重启之后消息会丢失
        //msgProducer1.setTimeToLive(1000*60*60);  消息有效期1小时
    }

    /*
       发送Object类型消息
     */
    public void sendObj(Session session, Object obj, String name) throws JMSException {

        Topic topic = new ActiveMQTopic(name);
        //发送对象时必须让该对象实现serializable接口
        ObjectMessage objMsg = session.createObjectMessage((Serializable) obj);
        MessageProducer msgProducer = session.createProducer(topic);
        msgProducer.send(objMsg);
    }
}
