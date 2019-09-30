package com.cn.zyd.auth.service;

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
public class ActivitiServiceTest {
    @Autowired
    private ActivitiService activitiService;

    /**
     * 测试流程创建
     */
    @Test
    public void testBpmnXml2Sql() {
        activitiService.createProcess("helloworld.bpmn", bpmnXml);
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
}
