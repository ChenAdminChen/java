package coms.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

/**
 * Created by chen on 2017/7/11.
 */
public class ServiceTask2 implements JavaDelegate {

    private Logger logger = Logger.getLogger(ServiceTask2.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) {
        try {
            Thread.sleep(10000);

            logger.info("variavles=" + delegateExecution.getVariables());
            delegateExecution.setVariable("task2", "I am task 2");
            logger.info("I am task 2.");


        } catch (Exception e) {
            logger.info(e.toString());
        }
    }


}
