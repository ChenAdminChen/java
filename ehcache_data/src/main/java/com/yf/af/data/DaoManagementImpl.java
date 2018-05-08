package com.yf.af.data;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class DaoManagementImpl implements DaoManagement {
    private static DaoManagement uniqueInstance = null;
    static private Logger logger = LoggerFactory.getLogger(DaoManagementImpl.class);
//	private static String[] configFiles={"META-INF/spring/mybatis-spring.xml"};
    //创建Spring应用上下文
//	private static ApplicationContext context = new ClassPathXmlApplicationContext(configFiles);

    @Autowired
    ApplicationContext context;

    // singleton
//	@SuppressWarnings("resource")
//	public boolean init(ApplicationContext confilgfile)
//	{		
//		new ClassPathXmlApplicationContext(context);
//		new ClassPathXmlApplicationContext(confilgfile);
//		return true;
//	}

//	@SuppressWarnings("resource")
//	public boolean init(){
//		logger.debug("init");
//		new ClassPathXmlApplicationContext(context);	
//		return true;
//	}

    /* (non-Javadoc)
     * @see com.yf.ax.dao.DaoManagment#getService(java.lang.String)
     */
    public Object getService(String name) {
        return context.getBean(name);
    }


    //region transaction management

    @Autowired
    DataSourceTransactionManager transactionManager;
//	private TransactionStatus transStatus;

    /**
     * TODO: 2017/4/21 transStatus 采用传输方式认证可否实现多服务事务管控
     *
     * @throws Exception
     */
//	@Override
//	public void startTransaction() throws Exception {
//		if (transStatus == null || transStatus.isCompleted())
//			transStatus = transactionManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
//		else
//			throw new Exception("current in a transaction.");
//	}

    public TransactionStatus startTransaction() {
        return transactionManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW));
    }

    public void commitTransaction(TransactionStatus transStatus) {
        transactionManager.commit(transStatus);
    }

    public void rollbackTransaction(TransactionStatus transStatus) {
        transactionManager.rollback(transStatus);
    }

    //endregion

    private DaoManagementImpl() {

    }

    public static DaoManagement getInstance() {
        if (uniqueInstance == null) {

            uniqueInstance = new DaoManagementImpl();

        }
        return uniqueInstance;
    }
}
