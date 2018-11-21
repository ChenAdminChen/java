package coms.activiti;

import junit.framework.TestCase;
import org.activiti.engine.*;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.impl.repository.DeploymentBuilderImpl;
import org.activiti.engine.repository.Deployment;

/**
 * Created by chen on 2017/7/11.
 */

public abstract class AbstractTest extends TestCase {
    private ProcessEngine processEngine;
    protected String deploymentId;
    protected RepositoryService repositoryService;
    protected RuntimeService runtimeService;
    protected TaskService taskService;
    protected FormService formService;
    protected HistoryService historyService;
    protected IdentityService identityService;
    protected ManagementService managementService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        if (processEngine == null) {

//            processEngine=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
            processEngine = ProcessEngines.getDefaultProcessEngine();
        }

        System.out.println(processEngine+"\t\t processEngine");

        repositoryService = processEngine.getRepositoryService();
        runtimeService = processEngine.getRuntimeService();
        taskService = processEngine.getTaskService();
        formService = processEngine.getFormService();
        historyService = processEngine.getHistoryService();
        identityService = processEngine.getIdentityService();
        managementService = processEngine.getManagementService();

       /* runtimeService.addEventListener(new ActivitiEventListener() {
            @Override
            public void onEvent(ActivitiEvent activitiEvent) {
                System.out.println(activitiEvent.getProcessInstanceId() + ": " + activitiEvent.getType());
            }

            @Override
            public boolean isFailOnException() {
                return false;
            }
        });*/


      initialize();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        destroy();
    }

    protected abstract void initialize() throws Exception;

    protected abstract void destroy() throws Exception;
}
