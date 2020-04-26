package com.bin.wheeltime.ticknode;


public class TickNode {
    //加入时的当前绝对tick
    private Long absoluteTick;
    //相对于加入时的tick
    private Long relativeTick;
    //链表上一节点
    private TickNode preNode;
    //链表下一节点
    private TickNode nextNode;
    //循环次数，0表示无限循环
    private Integer maxLoopTimes;

    public Long getAbsoluteTick() {
        return absoluteTick;
    }

    public void setAbsoluteTick(Long absoluteTick) {
        this.absoluteTick = absoluteTick;
    }

    public Long getRelativeTick() {
        return relativeTick;
    }

    public void setRelativeTick(Long relativeTick) {
        this.relativeTick = relativeTick;
    }

    public TickNode getPreNode() {
        return preNode;
    }

    public void setPreNode(TickNode preNode) {
        this.preNode = preNode;
    }

    public TickNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(TickNode nextNode) {
        this.nextNode = nextNode;
    }

    public Integer getMaxLoopTimes() {
        return maxLoopTimes;
    }

    public void setMaxLoopTimes(Integer maxLoopTimes) {
        this.maxLoopTimes = maxLoopTimes;
    }
}
