package com.ctff.cloud.activiti.service.impl;

import com.cn.zyd.activiti.service.impl.DeploymentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangyd
 * @date 2019/9/26 23:57
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeploymentServiceImplTest {
    @Autowired
    private DeploymentServiceImpl deploymentServiceImpl;

    /**
     * 测试流程创建
     */
    @Test
    public void testBpmnXml2Sql() {
        System.out.println(deploymentServiceImpl.deploy("helloworld.bpmn", null, bpmnXml));
    }

    /**
     * 测试流程更新
     */
    @Test
    public void testBpmnXmlUpdate() {
        System.out.println(deploymentServiceImpl.deploy("helloworld.bpmn", "1c5c54ea-e971-11e9-819f-3c918097a91a", bpmnXmlUpdate));
    }

    /**
     * 测试包括驳回的流程
     */
    @Test
    public void testBackProcess() {
        System.out.println(deploymentServiceImpl.deploy("backTest.bpmn", null, backBpmnXml));

    }

    @Test
    public void testlistDeployment() {
        System.out.println(deploymentServiceImpl.listDeployment());
    }

    @Test
    public void testlistProcessDefine() {
        System.out.println(deploymentServiceImpl.listProcess());
    }

    @Test
    public void testDetail() {
        System.out.println(deploymentServiceImpl.detail("5d7a7c95-e9a3-11e9-a399-3c918097a91a"));
    }

    @Test
    public void testRemove() {
        String deploymentId = "1c5c54ea-e971-11e9-819f-3c918097a91a";
        deploymentServiceImpl.remove(deploymentId);
        deploymentServiceImpl.detail(deploymentId);
    }

    String bpmnXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/test\">\n" +
            "  <process id=\"helloworld\" name=\"helloworldProcess\" isExecutable=\"true\">\n" +
            "    <startEvent id=\"startevent1\" name=\"Start\"></startEvent>\n" +
            "    <endEvent id=\"endevent1\" name=\"End\"></endEvent>\n" +
            "    <userTask id=\"usertask1\" name=\"提交申请\" activiti:assignee=\"张三\"></userTask>\n" +
            "    <userTask id=\"usertask2\" name=\"审批【部门经理】\" activiti:assignee=\"李四\"></userTask>\n" +
            "    <userTask id=\"usertask3\" name=\"审批【总经理】\" activiti:assignee=\"王五\"></userTask>\n" +
            "    <sequenceFlow id=\"flow1\" sourceRef=\"startevent1\" targetRef=\"usertask1\"></sequenceFlow>\n" +
            "    <sequenceFlow id=\"flow2\" sourceRef=\"usertask1\" targetRef=\"usertask2\"></sequenceFlow>\n" +
            "    <sequenceFlow id=\"flow3\" sourceRef=\"usertask2\" targetRef=\"usertask3\"></sequenceFlow>\n" +
            "    <sequenceFlow id=\"flow4\" sourceRef=\"usertask3\" targetRef=\"endevent1\"></sequenceFlow>\n" +
            "  </process>\n" +
            "  <bpmndi:BPMNDiagram id=\"BPMNDiagram_helloworld\">\n" +
            "    <bpmndi:BPMNPlane bpmnElement=\"helloworld\" id=\"BPMNPlane_helloworld\">\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"startevent1\" id=\"BPMNShape_startevent1\">\n" +
            "        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"330.0\" y=\"20.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"endevent1\" id=\"BPMNShape_endevent1\">\n" +
            "        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"330.0\" y=\"380.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"usertask1\" id=\"BPMNShape_usertask1\">\n" +
            "        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"295.0\" y=\"100.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"usertask2\" id=\"BPMNShape_usertask2\">\n" +
            "        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"295.0\" y=\"200.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"usertask3\" id=\"BPMNShape_usertask3\">\n" +
            "        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"295.0\" y=\"290.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"flow1\" id=\"BPMNEdge_flow1\">\n" +
            "        <omgdi:waypoint x=\"347.0\" y=\"55.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"347.0\" y=\"100.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"flow2\" id=\"BPMNEdge_flow2\">\n" +
            "        <omgdi:waypoint x=\"347.0\" y=\"155.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"347.0\" y=\"200.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"flow3\" id=\"BPMNEdge_flow3\">\n" +
            "        <omgdi:waypoint x=\"347.0\" y=\"255.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"347.0\" y=\"290.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"flow4\" id=\"BPMNEdge_flow4\">\n" +
            "        <omgdi:waypoint x=\"347.0\" y=\"345.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"347.0\" y=\"380.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "    </bpmndi:BPMNPlane>\n" +
            "  </bpmndi:BPMNDiagram>\n" +
            "</definitions>";

    String bpmnXmlUpdate = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/test\">\n" +
            "  <process id=\"helloworld\" name=\"helloworldProcess\" isExecutable=\"true\">\n" +
            "    <startEvent id=\"startevent1\" name=\"Start\"></startEvent>\n" +
            "    <endEvent id=\"endevent1\" name=\"End\"></endEvent>\n" +
            "    <userTask id=\"usertask1\" name=\"提交申请\" activiti:assignee=\"张三\"></userTask>\n" +
            "    <userTask id=\"usertask2\" name=\"审批【测试更新】\" activiti:assignee=\"李四\"></userTask>\n" +
            "    <userTask id=\"usertask3\" name=\"审批【总经理】\" activiti:assignee=\"王五\"></userTask>\n" +
            "    <sequenceFlow id=\"flow1\" sourceRef=\"startevent1\" targetRef=\"usertask1\"></sequenceFlow>\n" +
            "    <sequenceFlow id=\"flow2\" sourceRef=\"usertask1\" targetRef=\"usertask2\"></sequenceFlow>\n" +
            "    <sequenceFlow id=\"flow3\" sourceRef=\"usertask2\" targetRef=\"usertask3\"></sequenceFlow>\n" +
            "    <sequenceFlow id=\"flow4\" sourceRef=\"usertask3\" targetRef=\"endevent1\"></sequenceFlow>\n" +
            "  </process>\n" +
            "  <bpmndi:BPMNDiagram id=\"BPMNDiagram_helloworld\">\n" +
            "    <bpmndi:BPMNPlane bpmnElement=\"helloworld\" id=\"BPMNPlane_helloworld\">\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"startevent1\" id=\"BPMNShape_startevent1\">\n" +
            "        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"330.0\" y=\"20.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"endevent1\" id=\"BPMNShape_endevent1\">\n" +
            "        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"330.0\" y=\"380.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"usertask1\" id=\"BPMNShape_usertask1\">\n" +
            "        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"295.0\" y=\"100.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"usertask2\" id=\"BPMNShape_usertask2\">\n" +
            "        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"295.0\" y=\"200.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"usertask3\" id=\"BPMNShape_usertask3\">\n" +
            "        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"295.0\" y=\"290.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"flow1\" id=\"BPMNEdge_flow1\">\n" +
            "        <omgdi:waypoint x=\"347.0\" y=\"55.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"347.0\" y=\"100.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"flow2\" id=\"BPMNEdge_flow2\">\n" +
            "        <omgdi:waypoint x=\"347.0\" y=\"155.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"347.0\" y=\"200.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"flow3\" id=\"BPMNEdge_flow3\">\n" +
            "        <omgdi:waypoint x=\"347.0\" y=\"255.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"347.0\" y=\"290.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"flow4\" id=\"BPMNEdge_flow4\">\n" +
            "        <omgdi:waypoint x=\"347.0\" y=\"345.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"347.0\" y=\"380.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "    </bpmndi:BPMNPlane>\n" +
            "  </bpmndi:BPMNDiagram>\n" +
            "</definitions>";

    String backBpmnXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/processdef\">\n" +
            "  <process id=\"process\" name=\"测试请假流程\" isExecutable=\"true\">\n" +
            "    <startEvent id=\"startEvent\"></startEvent>\n" +
            "    <userTask id=\"usertask1\" name=\"提交申请\" activiti:assignee=\"${apply_user}\"></userTask>\n" +
            "    <sequenceFlow id=\"sid-F317712C-E962-47E4-99D1-FD4F5C2BEE09\" sourceRef=\"startEvent\" targetRef=\"usertask1\"></sequenceFlow>\n" +
            "    <userTask id=\"userTask2\" name=\"审批【部门经理】\" activiti:assignee=\"${dep_manager}\"></userTask>\n" +
            "    <sequenceFlow id=\"sid-638CAA07-7E98-40E5-A863-4E7BE2786821\" sourceRef=\"usertask1\" targetRef=\"userTask2\"></sequenceFlow>\n" +
            "    <exclusiveGateway id=\"sid-3E15AF44-9C9A-4683-B9BA-B03D6CAC3CD5\"></exclusiveGateway>\n" +
            "    <sequenceFlow id=\"sid-58148203-9872-4C33-8776-CD001B9305D1\" sourceRef=\"userTask2\" targetRef=\"sid-3E15AF44-9C9A-4683-B9BA-B03D6CAC3CD5\"></sequenceFlow>\n" +
            "    <userTask id=\"userTask3\" name=\"审批【总经理】\" activiti:assignee=\"${boss}\"></userTask>\n" +
            "    <sequenceFlow id=\"sid-6E36EFDD-A65D-4B9C-8D18-790CB59270DA\" sourceRef=\"sid-3E15AF44-9C9A-4683-B9BA-B03D6CAC3CD5\" targetRef=\"usertask1\">\n" +
            "      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${status==3}]]></conditionExpression>\n" +
            "    </sequenceFlow>\n" +
            "    <sequenceFlow id=\"sid-4C5E0E7C-AD2A-4696-857C-4AE6437D5639\" sourceRef=\"sid-3E15AF44-9C9A-4683-B9BA-B03D6CAC3CD5\" targetRef=\"userTask3\">\n" +
            "      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${status==4}]]></conditionExpression>\n" +
            "    </sequenceFlow>\n" +
            "    <endEvent id=\"sid-617996E6-C122-47F4-A21B-7689C4FCBABD\"></endEvent>\n" +
            "    <sequenceFlow id=\"sid-4291134F-3AC2-4EDE-9145-A9FB6DB0FD80\" sourceRef=\"userTask3\" targetRef=\"sid-617996E6-C122-47F4-A21B-7689C4FCBABD\"></sequenceFlow>\n" +
            "  </process>\n" +
            "  <bpmndi:BPMNDiagram id=\"BPMNDiagram_process\">\n" +
            "    <bpmndi:BPMNPlane bpmnElement=\"process\" id=\"BPMNPlane_process\">\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"startEvent\" id=\"BPMNShape_startEvent\">\n" +
            "        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"103.5\" y=\"159.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"usertask1\" id=\"BPMNShape_usertask1\">\n" +
            "        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"210.0\" y=\"134.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"userTask2\" id=\"BPMNShape_userTask2\">\n" +
            "        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"405.0\" y=\"134.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"sid-3E15AF44-9C9A-4683-B9BA-B03D6CAC3CD5\" id=\"BPMNShape_sid-3E15AF44-9C9A-4683-B9BA-B03D6CAC3CD5\">\n" +
            "        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"600.0\" y=\"225.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"userTask3\" id=\"BPMNShape_userTask3\">\n" +
            "        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"810.0\" y=\"134.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"sid-617996E6-C122-47F4-A21B-7689C4FCBABD\" id=\"BPMNShape_sid-617996E6-C122-47F4-A21B-7689C4FCBABD\">\n" +
            "        <omgdc:Bounds height=\"28.0\" width=\"28.0\" x=\"1076.5\" y=\"158.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-6E36EFDD-A65D-4B9C-8D18-790CB59270DA\" id=\"BPMNEdge_sid-6E36EFDD-A65D-4B9C-8D18-790CB59270DA\">\n" +
            "        <omgdi:waypoint x=\"600.5\" y=\"245.5\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"260.0\" y=\"245.5\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"260.0\" y=\"214.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-4291134F-3AC2-4EDE-9145-A9FB6DB0FD80\" id=\"BPMNEdge_sid-4291134F-3AC2-4EDE-9145-A9FB6DB0FD80\">\n" +
            "        <omgdi:waypoint x=\"910.0\" y=\"173.56616052060738\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"1076.5005269769872\" y=\"172.12147048176152\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-58148203-9872-4C33-8776-CD001B9305D1\" id=\"BPMNEdge_sid-58148203-9872-4C33-8776-CD001B9305D1\">\n" +
            "        <omgdi:waypoint x=\"505.0\" y=\"174.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"620.0\" y=\"174.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"620.0\" y=\"225.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-F317712C-E962-47E4-99D1-FD4F5C2BEE09\" id=\"BPMNEdge_sid-F317712C-E962-47E4-99D1-FD4F5C2BEE09\">\n" +
            "        <omgdi:waypoint x=\"133.5\" y=\"174.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"210.0\" y=\"174.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-638CAA07-7E98-40E5-A863-4E7BE2786821\" id=\"BPMNEdge_sid-638CAA07-7E98-40E5-A863-4E7BE2786821\">\n" +
            "        <omgdi:waypoint x=\"310.0\" y=\"174.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"405.0\" y=\"174.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-4C5E0E7C-AD2A-4696-857C-4AE6437D5639\" id=\"BPMNEdge_sid-4C5E0E7C-AD2A-4696-857C-4AE6437D5639\">\n" +
            "        <omgdi:waypoint x=\"639.5\" y=\"245.5\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"719.25\" y=\"245.5\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"719.25\" y=\"174.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"810.0\" y=\"174.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "    </bpmndi:BPMNPlane>\n" +
            "  </bpmndi:BPMNDiagram>\n" +
            "</definitions>";

    /**
     * 测试流程更新
     */
    @Test
    public void testMorePeopleBpmn() {
        System.out.println(deploymentServiceImpl.deploy("test_more_people.bpmn", null, morePeopleBpmn));
    }

    private String morePeopleBpmn = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/processdef\">\n" +
            "  <process id=\"process\" name=\"多人会签\" isExecutable=\"true\">\n" +
            "    <startEvent id=\"sid-1318B065-4D7A-46DD-8FD2-7DA2582E2980\" name=\"开始\"></startEvent>\n" +
            "    <endEvent id=\"sid-E3CDC355-00B2-4DDD-A390-087614A97F9B\" name=\"结束\"></endEvent>\n" +
            "    <userTask id=\"look_activity\" name=\"填写活动感想\" activiti:assignee=\"${assignee}\">\n" +
            "\t  <multiInstanceLoopCharacteristics isSequential=\"false\"\n" +
            "\t\t activiti:collection=\"${assigneeList}\" activiti:elementVariable=\"assignee\" >\n" +
            "\t\t<completionCondition>${nrOfCompletedInstances/nrOfInstances == 1}</completionCondition>\n" +
            "\t  </multiInstanceLoopCharacteristics>\n" +
            "\t</userTask>\n" +
            "    <userTask id=\"start_activity\" name=\"发起活动\" activiti:assignee=\"${start_user}\"></userTask>\n" +
            "    <sequenceFlow id=\"sid-82C67226-A68C-4B99-BC6F-21A402B18804\" sourceRef=\"sid-1318B065-4D7A-46DD-8FD2-7DA2582E2980\" targetRef=\"start_activity\"></sequenceFlow>\n" +
            "    <sequenceFlow id=\"sid-BA052161-6895-408A-B704-C4F2B311D5FA\" sourceRef=\"start_activity\" targetRef=\"look_activity\"></sequenceFlow>\n" +
            "    <userTask id=\"sid-E386DA9A-6B63-40E2-AD40-FE2A1837A93D\" name=\"查看结果\" activiti:assignee=\"${start_user}\"></userTask>\n" +
            "    <sequenceFlow id=\"sid-A4AAB451-FFCD-42F2-89D2-3BC0D1097C90\" sourceRef=\"look_activity\" targetRef=\"sid-E386DA9A-6B63-40E2-AD40-FE2A1837A93D\"></sequenceFlow>\n" +
            "    <sequenceFlow id=\"sid-390B9A00-01D5-4E18-8EA3-FAB960FEA7EC\" sourceRef=\"sid-E386DA9A-6B63-40E2-AD40-FE2A1837A93D\" targetRef=\"sid-E3CDC355-00B2-4DDD-A390-087614A97F9B\"></sequenceFlow>\n" +
            "  </process>\n" +
            "  <bpmndi:BPMNDiagram id=\"BPMNDiagram_process\">\n" +
            "    <bpmndi:BPMNPlane bpmnElement=\"process\" id=\"BPMNPlane_process\">\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"sid-1318B065-4D7A-46DD-8FD2-7DA2582E2980\" id=\"BPMNShape_sid-1318B065-4D7A-46DD-8FD2-7DA2582E2980\">\n" +
            "        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"75.0\" y=\"115.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"sid-E3CDC355-00B2-4DDD-A390-087614A97F9B\" id=\"BPMNShape_sid-E3CDC355-00B2-4DDD-A390-087614A97F9B\">\n" +
            "        <omgdc:Bounds height=\"28.0\" width=\"28.0\" x=\"720.0\" y=\"121.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"look_activity\" id=\"BPMNShape_look_activity\">\n" +
            "        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"345.0\" y=\"90.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"start_activity\" id=\"BPMNShape_start_activity\">\n" +
            "        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"165.0\" y=\"90.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"sid-E386DA9A-6B63-40E2-AD40-FE2A1837A93D\" id=\"BPMNShape_sid-E386DA9A-6B63-40E2-AD40-FE2A1837A93D\">\n" +
            "        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"527.0\" y=\"95.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-390B9A00-01D5-4E18-8EA3-FAB960FEA7EC\" id=\"BPMNEdge_sid-390B9A00-01D5-4E18-8EA3-FAB960FEA7EC\">\n" +
            "        <omgdi:waypoint x=\"627.0\" y=\"135.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"720.0\" y=\"135.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-BA052161-6895-408A-B704-C4F2B311D5FA\" id=\"BPMNEdge_sid-BA052161-6895-408A-B704-C4F2B311D5FA\">\n" +
            "        <omgdi:waypoint x=\"265.0\" y=\"130.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"345.0\" y=\"130.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-82C67226-A68C-4B99-BC6F-21A402B18804\" id=\"BPMNEdge_sid-82C67226-A68C-4B99-BC6F-21A402B18804\">\n" +
            "        <omgdi:waypoint x=\"105.0\" y=\"130.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"165.0\" y=\"130.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-A4AAB451-FFCD-42F2-89D2-3BC0D1097C90\" id=\"BPMNEdge_sid-A4AAB451-FFCD-42F2-89D2-3BC0D1097C90\">\n" +
            "        <omgdi:waypoint x=\"445.0\" y=\"130.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"486.0\" y=\"130.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"486.0\" y=\"135.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"527.0\" y=\"135.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "    </bpmndi:BPMNPlane>\n" +
            "  </bpmndi:BPMNDiagram>\n" +
            "</definitions>";
}
