package com.lxc.autopage.base.module;

import com.lxc.autopage.base.module.enums.ElementType;
import com.lxc.autopage.base.module.enums.RelationType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/1.
 */
public class NodeElement extends Element implements Serializable{

    private String endLabel;
    private String script;
    private List<Element> slaveElements = new ArrayList<>();  // 从属节点
    private RelationType relationType;  // 主从类型

    public void addSlaveElement(Element element){
        slaveElements.add(element);
    }

    public void addSlaveElements(List<Element> elements){
        slaveElements.addAll(elements);
    }

    public String getEndLabel() {
        return endLabel;
    }

    public void setEndLabel(String endLabel) {
        this.endLabel = endLabel;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public List<Element> getSlaveElements() {
        return slaveElements;
    }

    public void setSlaveElements(List<Element> slaveElements) {
        this.slaveElements = slaveElements;
    }

    public RelationType getRelationType() {
        return relationType;
    }

    public void setRelationType(RelationType relationType) {
        this.relationType = relationType;
    }
}
