import javax.jms.Connection;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Session;

public class JMSConnectRunnable implements Runnable, ExceptionListener {

    private JMSConnectionInfo jmsConnectionInfo = JMSConnectionInfo.getInstance();
    private Thread currentThread;

    public void run() {

        currentThread = Thread.currentThread();

        while (true) {


            System.out.println(jmsConnectionInfo.getAtomicBoolean().get());

            while (!jmsConnectionInfo.getAtomicBoolean().get()) {
                System.out.println("connect/re-connect");

                try {
                    Connection connection = jmsConnectionInfo.getActiveMQConnectionFactory().createConnection();
                    connection.setExceptionListener(this);

                    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                    jmsConnectionInfo.setConnection(connection);

                    jmsConnectionInfo.setSession(session);

                    jmsConnectionInfo.getAtomicBoolean().set(true);

                    JMSConsumer jmsConsumer = new JMSConsumer();
                    jmsConsumer.consumer();
                } catch (JMSException e) {

                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();

                }

                if (!jmsConnectionInfo.getAtomicBoolean().get())
                try {
                    System.out.println("wait for next re-connect");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("wake up for re-connect");
//                        e.printStackTrace();
                }
            }

            try {
                System.out.println("connected, sleep");
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                System.out.println("wake up");
//                e.printStackTrace();
            }

        }

    }

    public void onException(JMSException e) {
        // if(e.toString().contains("The Session is closed")){

        jmsConnectionInfo.getAtomicBoolean().set(false);

        if (currentThread != null)
            currentThread.interrupt();
        //}
    }
}
