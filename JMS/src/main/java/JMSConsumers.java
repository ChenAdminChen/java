import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

public class JMSConsumers  implements MessageListener {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionfactory =null;
        Connection connection=null;
        Session session=null;
        if(connectionfactory==null){
            connectionfactory = new ActiveMQConnectionFactory("hnyfadmin","hnyfadmin","tcp://localhost:61616");
            //接收对象时，设置这个为true
//            ((ActiveMQConnectionFactory) connectionfactory).setTrustAllPackages(true);
        }
        if(connection==null){
            connection = connectionfactory.createConnection();
            connection.start();
        }
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic queue1 = new ActiveMQTopic("userOnlines");
        MessageConsumer consumer1 = session.createConsumer(queue1);
        consumer1.setMessageListener(new JMSConsumers());

    }

    public void onMessage(Message message) {
        //instanceof 测试它所指向的对象是否是TextMessage类
        if(message instanceof TextMessage){ //接受文本消息
            TextMessage text = (TextMessage) message;
            try {
                System.out.println("message:"+message);
                System.out.println("发送的文本消息内容为："+text.getText());

                Thread.sleep(30000);

            } catch (JMSException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(message instanceof MapMessage){ //接收map消息
            MapMessage map = (MapMessage) message;
            try {
                System.out.println("姓名："+map.getString("name"));
                System.out.println("年龄:"+map.getInt("age"));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

//        if(message instanceof ObjectMessage){ //接收object
//            try {
//                System.out.println("ObjectMessage");
//                ObjectMessage objMsg =(ObjectMessage) message;
//                JMS jms=(JMS) objMsg.getObject();
//                System.out.println(jms);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        if(message instanceof BytesMessage){ //接收字节消息
            byte[] b = new byte[1024];
            int len = -1;
            BytesMessage byteMsg = (BytesMessage)message;
            try {
                while((len=byteMsg.readBytes(b))!=-1){
                    System.out.println(new String(b, 0, len));
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
            /*
                  if(message instanceof StreamMessage){ //接收流消息
                    StreamMessage message = (StreamMessage)message;
                    System.out.println(message.readString());
                    System.out.println(message.readLong());
                }
             */

    }
}
