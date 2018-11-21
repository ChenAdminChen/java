import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SendJms {
    private ExecutorService executorService;
    private JMSConnectionInfo jmsConnectionInfo;
    private Runnable runnable;

    public SendJms() {

        this.jmsConnectionInfo= JMSConnectionInfo.getInstance();

        this.runnable= new JMSConnectRunnable();

        this.executorService = Executors.newSingleThreadExecutor();

        this.executorService.execute(runnable);

    }

    public void sendMessage() {

        Topic topic = new ActiveMQTopic("user.online");

        try {

            System.out.println("sending: " + jmsConnectionInfo.getAtomicBoolean().get());
            if(jmsConnectionInfo.getAtomicBoolean().get()) {

                Session session = jmsConnectionInfo.getSession();

                MessageProducer msgProducer = session.createProducer(topic);
                Message message = session.createTextMessage("testssss");

                msgProducer.send(message);

            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        SendJms sendJms = new SendJms();

        while (true) {
            sendJms.sendMessage();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                return;
            }
        }

    }
}
