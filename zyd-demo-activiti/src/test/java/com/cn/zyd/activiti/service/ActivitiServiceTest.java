package com.cn.zyd.activiti.service;

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
        activitiService.deploy("helloworld.bpmn", bpmnXml);
    }
    @Test
    public void testBpmnXml2Sql2() {
        activitiService.deploy("测试serviceTask2.bpmn", bpmnXml2);

    }

    String bpmnXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/test\">\n" +
            "  <process id=\"helloworld\" name=\"helloworldProcess\" isExecutable=\"true\">\n" +
            "    <startEvent id=\"startevent1\" name=\"Start\"></startEvent>\n" +
            "    <endEvent id=\"endevent1\" name=\"End\"></endEvent>\n" +
            "    <userTask id=\"usertask1\" name=\"提交申请\" activiti:assignee=\"${userId1}\"></userTask>\n" +
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

    String bpmnXml2="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/processdef\">\n" +
            "  <process id=\"测试serviceTask流程\" name=\"开始\" isExecutable=\"true\">\n" +
            "    <startEvent id=\"sid-D12F8FBA-51FC-4248-8910-5455193D00FD\"></startEvent>\n" +
            "    <serviceTask id=\"sid-86A58111-8395-4E55-867B-C37902F8E085\" name=\"流程1\" activiti:class=\"com.cn.zyd.activiti.process.Process1\"></serviceTask>\n" +
            "    <sequenceFlow id=\"sid-83276C19-3125-411F-8D3F-A2248A6C3BA7\" sourceRef=\"sid-D12F8FBA-51FC-4248-8910-5455193D00FD\" targetRef=\"sid-86A58111-8395-4E55-867B-C37902F8E085\"></sequenceFlow>\n" +
            "    <serviceTask id=\"sid-95B0CC59-CABE-4BEA-BE36-DC09377CF5EA\" name=\"流程2\" activiti:class=\"com.cn.zyd.activiti.process.Process2\"></serviceTask>\n" +
            "    <sequenceFlow id=\"sid-755A216E-7B1C-4F54-8AD5-77599FDA68F2\" sourceRef=\"sid-86A58111-8395-4E55-867B-C37902F8E085\" targetRef=\"sid-95B0CC59-CABE-4BEA-BE36-DC09377CF5EA\"></sequenceFlow>\n" +
            "    <endEvent id=\"sid-3FCB8146-275A-4A72-8451-7A66621921B2\"></endEvent>\n" +
            "    <sequenceFlow id=\"sid-ADBB4612-E6B1-4545-88A3-DB0C0A6DAB59\" sourceRef=\"sid-95B0CC59-CABE-4BEA-BE36-DC09377CF5EA\" targetRef=\"sid-3FCB8146-275A-4A72-8451-7A66621921B2\"></sequenceFlow>\n" +
            "  </process>\n" +
            "  <bpmndi:BPMNDiagram id=\"BPMNDiagram_process\">\n" +
            "    <bpmndi:BPMNPlane bpmnElement=\"process\" id=\"BPMNPlane_process\">\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"sid-D12F8FBA-51FC-4248-8910-5455193D00FD\" id=\"BPMNShape_sid-D12F8FBA-51FC-4248-8910-5455193D00FD\">\n" +
            "        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"167.0\" y=\"142.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"sid-86A58111-8395-4E55-867B-C37902F8E085\" id=\"BPMNShape_sid-86A58111-8395-4E55-867B-C37902F8E085\">\n" +
            "        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"312.0\" y=\"117.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"sid-95B0CC59-CABE-4BEA-BE36-DC09377CF5EA\" id=\"BPMNShape_sid-95B0CC59-CABE-4BEA-BE36-DC09377CF5EA\">\n" +
            "        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"525.0\" y=\"117.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNShape bpmnElement=\"sid-3FCB8146-275A-4A72-8451-7A66621921B2\" id=\"BPMNShape_sid-3FCB8146-275A-4A72-8451-7A66621921B2\">\n" +
            "        <omgdc:Bounds height=\"28.0\" width=\"28.0\" x=\"759.0\" y=\"145.0\"></omgdc:Bounds>\n" +
            "      </bpmndi:BPMNShape>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-83276C19-3125-411F-8D3F-A2248A6C3BA7\" id=\"BPMNEdge_sid-83276C19-3125-411F-8D3F-A2248A6C3BA7\">\n" +
            "        <omgdi:waypoint x=\"197.0\" y=\"157.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"312.0\" y=\"157.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-755A216E-7B1C-4F54-8AD5-77599FDA68F2\" id=\"BPMNEdge_sid-755A216E-7B1C-4F54-8AD5-77599FDA68F2\">\n" +
            "        <omgdi:waypoint x=\"412.0\" y=\"157.0\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"525.0\" y=\"157.0\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "      <bpmndi:BPMNEdge bpmnElement=\"sid-ADBB4612-E6B1-4545-88A3-DB0C0A6DAB59\" id=\"BPMNEdge_sid-ADBB4612-E6B1-4545-88A3-DB0C0A6DAB59\">\n" +
            "        <omgdi:waypoint x=\"625.0\" y=\"157.50505050505052\"></omgdi:waypoint>\n" +
            "        <omgdi:waypoint x=\"759.0007141581865\" y=\"158.85859307230493\"></omgdi:waypoint>\n" +
            "      </bpmndi:BPMNEdge>\n" +
            "    </bpmndi:BPMNPlane>\n" +
            "  </bpmndi:BPMNDiagram>\n" +
            "</definitions>";

    @Test
    public void testSqlBuild() {
        System.out.println(activitiService.builderSql("f4d3b797-e352-11e9-b2dc-3c918097a91a"));;
    }
}
