package com.cn.zyd.activiti.scene;

import com.cn.zyd.activiti.dto.CompleteTaskDto;
import com.cn.zyd.activiti.dto.ProcessStartDto;
import com.cn.zyd.activiti.dto.TaskParam;
import com.cn.zyd.activiti.service.DeploymentService;
import com.cn.zyd.activiti.service.ProcessService;
import com.cn.zyd.activiti.service.UserTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zyd
 * @date 2019/12/24 21:56
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WithdrawTest {

    /**
     * 测试的流程
     */
    public static final String bpmnXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/processdef\">\n" +
            "  <process id=\"withdraw\" name=\"总审批2\" isExecutable=\"true\">\n" +
            "    <startEvent id=\"sid-C41D8366-EB11-442C-AC91-45F34D649A6F\" name=\"开始\"></startEvent>\n" +
            "    <endEvent id=\"sid-07F71FFD-C906-4565-83DB-47A2F743428C\" name=\"结束\"></endEvent>\n" +
            "    <userTask id=\"sid-936AEFF6-31E0-40FA-8D85-7222C4BED029\" name=\"提交\" activiti:assignee=\"${user1}\"></userTask>\n" +
            "    <userTask id=\"sid-DCA44047-CF32-4829-8C92-15BF4B6433A7\" name=\"审批1\" activiti:assignee=\"${user2}\"></userTask>\n" +
            "    <userTask id=\"sid-7C85A53A-58B2-4AD9-B082-48188CB3B3A8\" name=\"总审批1\" activiti:assignee=\"${user3}\"></userTask>\n" +
            "    <sequenceFlow id=\"sid-C70652BE-80ED-4B75-93F4-2225E807D872\" sourceRef=\"sid-C41D8366-EB11-442C-AC91-45F34D649A6F\" targetRef=\"sid-936AEFF6-31E0-40FA-8D85-7222C4BED029\"></sequenceFlow>\n" +
            "    <sequenceFlow id=\"sid-96410023-7F3D-49C7-A983-9A3E7FB7240F\" sourceRef=\"sid-936AEFF6-31E0-40FA-8D85-7222C4BED029\" targetRef=\"sid-DCA44047-CF32-4829-8C92-15BF4B6433A7\"></sequenceFlow>\n" +
            "    <sequenceFlow id=\"sid-2F34BB8A-0B79-4C83-8A4E-CF7FE13DF811\" sourceRef=\"sid-DCA44047-CF32-4829-8C92-15BF4B6433A7\" targetRef=\"sid-7C85A53A-58B2-4AD9-B082-48188CB3B3A8\"></sequenceFlow>\n" +
            "    <userTask id=\"sid-E441D246-1C9C-4AC3-84FA-7D5D62B483A9\" name=\"总审批2\" activiti:assignee=\"${user4}\"></userTask>\n" +
            "    <sequenceFlow id=\"sid-43C3834E-D0FE-4F88-A863-39A11014D18B\" sourceRef=\"sid-DCA44047-CF32-4829-8C92-15BF4B6433A7\" targetRef=\"sid-E441D246-1C9C-4AC3-84FA-7D5D62B483A9\"></sequenceFlow>\n" +
            "    <sequenceFlow id=\"sid-2FE945A2-E3FD-47DF-809F-FF1CCEB63947\" sourceRef=\"sid-7C85A53A-58B2-4AD9-B082-48188CB3B3A8\" targetRef=\"sid-07F71FFD-C906-4565-83DB-47A2F743428C\"></sequenceFlow>\n" +
            "    <sequenceFlow id=\"sid-3F6D5C0D-1A1A-426F-8B31-23D196E4DAC6\" sourceRef=\"sid-E441D246-1C9C-4AC3-84FA-7D5D62B483A9\" targetRef=\"sid-07F71FFD-C906-4565-83DB-47A2F743428C\"></sequenceFlow>\n" +
            "  </process>\n" +
            "  <bpmndi:BPMNDiagram id=\"BPMNDiagram_process\">\n" +
            "    <bpmndi:BPMNPlane bpmnElement=\"process\" id=\"BPMNPlane_process\">\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"sid-C41D8366-EB11-442C-AC91-45F34D649A6F\" id=\"BPMNShape_sid-C41D8366-EB11-442C-AC91-45F34D649A6F\">\n" +
            "        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"165.0\" y=\"139.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"sid-07F71FFD-C906-4565-83DB-47A2F743428C\" id=\"BPMNShape_sid-07F71FFD-C906-4565-83DB-47A2F743428C\">\n" +
            "        <omgdc:Bounds height=\"28.0\" width=\"28.0\" x=\"960.0\" y=\"135.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"sid-936AEFF6-31E0-40FA-8D85-7222C4BED029\" id=\"BPMNShape_sid-936AEFF6-31E0-40FA-8D85-7222C4BED029\">\n" +
            "        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"285.0\" y=\"114.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"sid-DCA44047-CF32-4829-8C92-15BF4B6433A7\" id=\"BPMNShape_sid-DCA44047-CF32-4829-8C92-15BF4B6433A7\">\n" +
            "        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"501.0\" y=\"114.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"sid-7C85A53A-58B2-4AD9-B082-48188CB3B3A8\" id=\"BPMNShape_sid-7C85A53A-58B2-4AD9-B082-48188CB3B3A8\">\n" +
            "        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"690.0\" y=\"15.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"sid-E441D246-1C9C-4AC3-84FA-7D5D62B483A9\" id=\"BPMNShape_sid-E441D246-1C9C-4AC3-84FA-7D5D62B483A9\">\n" +
            "        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"690.0\" y=\"195.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-2F34BB8A-0B79-4C83-8A4E-CF7FE13DF811\" id=\"BPMNEdge_sid-2F34BB8A-0B79-4C83-8A4E-CF7FE13DF811\">\n" +
            "        <omgdi:waypoint x=\"601.0\" y=\"127.80952380952381\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"690.0\" y=\"81.19047619047619\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-96410023-7F3D-49C7-A983-9A3E7FB7240F\" id=\"BPMNEdge_sid-96410023-7F3D-49C7-A983-9A3E7FB7240F\">\n" +
            "        <omgdi:waypoint x=\"385.0\" y=\"154.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"501.0\" y=\"154.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-3F6D5C0D-1A1A-426F-8B31-23D196E4DAC6\" id=\"BPMNEdge_sid-3F6D5C0D-1A1A-426F-8B31-23D196E4DAC6\">\n" +
            "        <omgdi:waypoint x=\"790.0\" y=\"216.6239316239316\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"960.8593654067547\" y=\"153.82946399580808\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-43C3834E-D0FE-4F88-A863-39A11014D18B\" id=\"BPMNEdge_sid-43C3834E-D0FE-4F88-A863-39A11014D18B\">\n" +
            "        <omgdi:waypoint x=\"601.0\" y=\"175.42857142857144\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"690.0\" y=\"213.57142857142856\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-2FE945A2-E3FD-47DF-809F-FF1CCEB63947\" id=\"BPMNEdge_sid-2FE945A2-E3FD-47DF-809F-FF1CCEB63947\">\n" +
            "        <omgdi:waypoint x=\"790.0\" y=\"75.08547008547009\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"961.0089979560883\" y=\"143.7813923413346\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-C70652BE-80ED-4B75-93F4-2225E807D872\" id=\"BPMNEdge_sid-C70652BE-80ED-4B75-93F4-2225E807D872\">\n" +
            "        <omgdi:waypoint x=\"195.0\" y=\"154.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"285.0\" y=\"154.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "    </bpmndi:BPMNPlane>\n" +
            "  </bpmndi:BPMNDiagram>\n" +
            "</definitions>";

    @Autowired
    private DeploymentService deploymentService;
    @Autowired
    public ProcessService processService;
    @Autowired
    private UserTaskService userTaskService;

    /**
     * 部署流程
     */
    @Test
    public void deploy() {
        deploymentService.deploy("withdraw.bpmn", null, bpmnXml);
    }

    /**
     * 启动流程
     */
    @Test
    public void start() {
        ProcessStartDto processStartDto = new ProcessStartDto();
        processStartDto.setProcessDefinitionKey("withdraw")
                .setProcessName("测试回收")
                .setTaskParam(new TaskParam("user1", "张三"));
        processService.startProcess(processStartDto);
    }

    /**
     * 张三查看自己的任务
     */
    @Test
    public void queryMyTask() {
        System.out.println(userTaskService.queryTaskByUser("张三", 0, 10));
        System.out.println(userTaskService.queryHistoryTask("张三", 0, 10));
        System.out.println(userTaskService.queryHisComment("张三","4b37c3a4-26f5-11ea-9d17-3c918097a91a"));
    }

    /**
     * 完成第一步
     */
    @Test
    public void complete() {
        CompleteTaskDto completeTaskDto = new CompleteTaskDto();
        completeTaskDto.setUserId("张三");
        completeTaskDto.setTaskId("3251dca8-26f5-11ea-b609-3c918097a91a");
        completeTaskDto.setComment("张三提交材料");
        completeTaskDto.setTaskParam(new TaskParam("user2", "李四"));
//        completeTaskDto.setTaskParam(new TaskParam("user3", "王五").putAndReturn("user4","赵六"));
        userTaskService.completeTask(completeTaskDto);
    }

    /**
     * 撤销
     */
    @Test
    public void testRevoke() {
        System.out.println(userTaskService.revoke("f56739e5-26f4-11ea-8e93-3c918097a91a", "张三"));
    }

}
