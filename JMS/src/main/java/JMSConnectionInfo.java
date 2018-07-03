import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.Session;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * JMS连接的信息保存处理，单例模式
 */
//@Data
public
class JMSConnectionInfo {
    private static JMSConnectionInfo instance =null;

    private ActiveMQConnectionFactory activeMQConnectionFactory;
    private Connection connection;

    private Session session;

//    @Setter(AccessLevel.NONE)
    private AtomicBoolean atomicBoolean = new AtomicBoolean();

    public ActiveMQConnectionFactory getActiveMQConnectionFactory() {
        return activeMQConnectionFactory;
    }

    public void setActiveMQConnectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
        this.activeMQConnectionFactory = activeMQConnectionFactory;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public AtomicBoolean getAtomicBoolean() {
        return atomicBoolean;
    }

    public void setAtomicBoolean(AtomicBoolean atomicBoolean) {
        this.atomicBoolean = atomicBoolean;
    }

    private JMSConnectionInfo(){
        this.activeMQConnectionFactory =  new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

    }

    public static JMSConnectionInfo getInstance(){
        if(instance ==null){
            instance =  new JMSConnectionInfo();
        }
        return instance;
    }

}
