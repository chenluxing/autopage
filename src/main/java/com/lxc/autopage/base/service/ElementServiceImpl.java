package com.lxc.autopage.base.service;

import com.lxc.autopage.base.module.Element;
import com.lxc.autopage.base.module.NodeElement;
import com.lxc.autopage.base.module.enums.ElementType;
import com.lxc.autopage.base.module.enums.RelationType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/1.
 */
@Service
public class ElementServiceImpl {

    public List<Element> getGroups(){
        List<Element> groups = new ArrayList<>();

        Element element4 = new Element();
        element4.setElementType(ElementType.GROUP);
        element4.setDisplayName("被保险人信息");
        element4.setName("insuredInfo");
        element4.addSubElements(getNodeElements(111));
        groups.add(element4);

        Element element3 = new Element();
        element3.setElementType(ElementType.GROUP);
        element3.setDisplayName("车主信息");
        element3.setName("carOwnerInfo");
        element3.addSubElements(getNodeElements(111));
        groups.add(element3);

        Element element2 = new Element();
        element2.setElementType(ElementType.GROUP);
        element2.setDisplayName("车辆信息");
        element2.setName("carInfo");
        element2.addSubElements(getNodeElements(111));
        groups.add(element2);

        Element element1 = new Element();
        element1.setElementType(ElementType.GROUP);
        element1.setDisplayName("附加险信息");
        element1.setName("subInsInfo");
        element1.addSubElements(getNodeElements(111));
        groups.add(element1);

        return groups;
    }

    public List<Element> getNodeElements(Integer groupId){
        List<Element> nodeList = new ArrayList<>();

        NodeElement node5 = new NodeElement();
        node5.setName("insuredName");
        node5.setDisplayName("被保险人");
        node5.setElementType(ElementType.INPUT);
        node5.setRelationType(RelationType.MASTER);
        nodeList.add(node5);

        NodeElement node4 = new NodeElement();
        node4.setName("insuredsex");
        node4.setDisplayName("性别");
        node4.setElementType(ElementType.SELECT);
        node4.setRelationType(RelationType.MASTER);

        NodeElement node4_1 = new NodeElement();
        node4_1.setValue("1");
        node4_1.setDisplayName("男");
        node4_1.setElementType(ElementType.OPTION);
        node4_1.setRelationType(RelationType.SUB);
        node4.addSubElement(node4_1);

        NodeElement node4_2 = new NodeElement();
        node4_2.setValue("2");
        node4_2.setDisplayName("女");
        node4_2.setElementType(ElementType.OPTION);
        node4_2.setRelationType(RelationType.SUB);
        node4.addSubElement(node4_2);

        nodeList.add(node4);

        NodeElement node3 = new NodeElement();
        node3.setName("insuredmobilePhone");
        node3.setDisplayName("联系电话");
        node3.setElementType(ElementType.INPUT);
        node3.setRelationType(RelationType.MASTER);
        nodeList.add(node3);

        NodeElement node2_1 = new NodeElement();
        node2_1.setName("xcwsp");
        node2_1.setDisplayName("新车未上牌");
        node2_1.setElementType(ElementType.CHECKBOX);
        node2_1.setRelationType(RelationType.SLAVE);
        node2_1.setScript("onclick='javascript:alert(1)'");

        NodeElement node2 = new NodeElement();
        node2.setName("licenseNo");
        node2.setDisplayName("车牌号码");
        node2.setElementType(ElementType.INPUT);
        node2.setRelationType(RelationType.MASTER);
        node2.addSlaveElement(node2_1);
        nodeList.add(node2);

        NodeElement node1 = new NodeElement();
        node1.setName("brandName");
        node1.setDisplayName("车辆型号");
        node1.setElementType(ElementType.INPUT);
        node1.setRelationType(RelationType.MASTER);
        nodeList.add(node1);

        return nodeList;
    }

}
