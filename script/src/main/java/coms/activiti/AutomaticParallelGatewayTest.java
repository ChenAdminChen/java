package coms.activiti;

import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.test.Deployment;
import org.activiti.engine.impl.repository.DeploymentBuilderImpl;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * Created by chen on 2017/7/11.
 */
public class AutomaticParallelGatewayTest extends AbstractTest  {
    String deploymentId=null;
    @Override
    protected void initialize() throws Exception {
//        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().addClasspathResource("GatewayTest.testAutomaticForkJoin.bpmn20.xml");
//        System.out.println(deploymentBuilder +"\t\t repositoryService.createDeployment");
//
//        deploymentId = deploymentBuilder.deploy().getId();
//        System.out.println(deploymentId+"  deploymentId");
    }

    @Override
    protected void destroy() throws Exception {
        //删除数据库中的记录
//        repositoryService.deleteDeployment(deploymentId, true);
    }

    @Deployment
    public void testForkJoin() {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("AutomaticParalellBasedForkJoin");
        System.out.println(pi + "\t\t pi");

       /* ProcessInstance pi2 = runtimeService.startProcessInstanceByKey("AutomaticParalellBasedForkJoin");
        System.out.println(pi2 + "\t\t pi2");
*/
        assertEquals(true, pi.isEnded());
    }
}
