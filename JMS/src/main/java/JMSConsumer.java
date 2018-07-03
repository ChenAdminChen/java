import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JMSConsumer  implements MessageListener{

    private JMSConnectionInfo jmsConnectionInfo;

    private Session session;

    public JMSConsumer(){
        jmsConnectionInfo = JMSConnectionInfo.getInstance();

    }

    public void consumer(){

        Connection connection = jmsConnectionInfo.getConnection();
        Topic topic = new ActiveMQTopic("user.onlines");

        try {
            connection.start();

            this.session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

            MessageConsumer messageConsumer =jmsConnectionInfo.getSession().createConsumer(topic);

            messageConsumer.setMessageListener(new JMSConsumer());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    /**
     * 接收消息
     * @param message
     */
    public void onMessage(Message message) {
        //instanceof 测试它所指向的对象是否是TextMessage类
        if(message instanceof TextMessage){ //接受文本消息
            TextMessage text = (TextMessage) message;
            try {
                System.out.println("message:"+message);
                System.out.println("发送的文本消息内容为："+text.getText());

                if(text.getText().equals("getOnlineUser")){
                    this.sendSet();
                }

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        if(message instanceof MapMessage){ //接收map消息
            MapMessage map = (MapMessage) message;
            try {
                Set<String> strings = new HashSet<String>();
                strings = (Set)map.getObject("jid");
                for(int i =0; i<strings.size(); i++){
                    System.out.println(strings.iterator());
                }
                System.out.println("姓名："+map.getString("name"));
                System.out.println("年龄:"+map.getInt("age"));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendSet(){

        Set<String> stringSet = new HashSet<String>();

        stringSet.add("1004@yf.com");
        stringSet.add("1005@yf.com");
        stringSet.add("1006@yf.com");
        stringSet.add("1007@yf.com");
        stringSet.add("1008@yf.com");
        stringSet.add("1009@yf.com");
        stringSet.add("1010@yf.com");

        System.out.println("sizw:" + stringSet.size());

        for (String s : stringSet) {

            try {
                this.sendTextMsg(jmsConnectionInfo.getSession(),s,"userOnlines");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
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
}
