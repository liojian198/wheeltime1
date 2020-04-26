package com.bin.wheeltime.wheel;

import com.bin.wheeltime.ticknode.TickNode;
import com.bin.wheeltime.ticknode.TickNodeLinkedUtil;

public class Wheel {
    //轮子大小,槽容器
    private TickNode[] tickNodes;
    //wheelSize 当前轮最大槽数
    private Integer wheelSize;
    //tickMs 当前轮的基数, 默认是1, 这时不用校准
    private Long tickMs;
    //currentTime 当前轮的tick值，当前的slot值 取值范围[0,mn_maxsize)
    private Integer currentSlot;
    //上一轮
    private Wheel preWheel;
    //下一轮
    private Wheel nextWheel;

    public Wheel (Integer wheelSize) {
        tickNodes = new TickNode[wheelSize];
        for(int i = 0; i < wheelSize; i++) {
            tickNodes[i] = new TickNode();
        }
        this.tickMs = 1L;
        this.preWheel = null;
        this.nextWheel = null;
        this.wheelSize = wheelSize;
        this.currentSlot = 0;
    }

    //设置前后轮关系
    public boolean setNeighbours (Wheel prevNeighbourWheel, Wheel nextNeighbourWheel) {

        if(null == prevNeighbourWheel && null == nextNeighbourWheel ) {
            return false;
        }
        preWheel = prevNeighbourWheel;
        nextWheel = nextNeighbourWheel;
        return true;
    }

    //根据之前轮计算基数（要先把所有轮先后关系确定后再调用）
    public Long calcTicketMS() {
        Wheel temp = preWheel;
        while(null != temp) {
            tickMs *= temp.getWheelSize();
            temp = temp.preWheel;
        }
        return tickMs;
    }


    public boolean add(TickNode node, Long relativeTick) {
        Long roundIndex = relativeTick / tickMs;
        Integer leftTicks = wheelSize - currentSlot;
        if(roundIndex < leftTicks) {
            Integer index = currentSlot + roundIndex.intValue();
            TickNode tickNode = tickNodes[index];
            TickNodeLinkedUtil.addNodeAfterHead(tickNode, node);
            return true;
        } else if (null != nextWheel) {
            return nextWheel.add(node, relativeTick - (leftTicks * tickMs));
        } else {
            return false;
        }
    }

    public TickNode tick(Long nowAbsoluteTick) {
        if(currentSlot >= wheelSize && null != nextWheel) {
            //当前轮已满，把下一轮的tick槽移上来
            currentSlot = 0;
            TickNode nextWheelData = nextWheel.tick(nowAbsoluteTick);
            while (null != nextWheelData) {
                Long absTick = nextWheelData.getAbsoluteTick() + nextWheelData.getRelativeTick();
                Long indexId =  (absTick - nowAbsoluteTick) / tickMs;

                if(indexId < wheelSize) {
                    TickNode node = tickNodes[indexId.intValue()];
                    TickNodeLinkedUtil.addNodeAfterHead(node, nextWheelData);
                    nextWheelData = nextWheelData.getNextNode();
                } else  {
                    //TODO error ??
                }
            }
        } else if(currentSlot >= wheelSize && null == nextWheel) {
            //todo error
        }

        TickNode headNode = tickNodes[currentSlot];
        TickNode result = headNode.getNextNode();
        headNode.setNextNode(null);
        currentSlot ++ ;
        return result;
    }


    public TickNode[] getTickNodes() {
        return tickNodes;
    }

    public void setTickNodes(TickNode[] tickNodes) {
        this.tickNodes = tickNodes;
    }

    public Integer getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(Integer wheelSize) {
        this.wheelSize = wheelSize;
    }

    public Long getTickMs() {
        return tickMs;
    }

    public void setTickMs(Long tickMs) {
        this.tickMs = tickMs;
    }

    public Integer getCurrentSlot() {
        return currentSlot;
    }

    public void setCurrentSlot(Integer currentSlot) {
        this.currentSlot = currentSlot;
    }

    public Wheel getPreWheel() {
        return preWheel;
    }

    public void setPreWheel(Wheel preWheel) {
        this.preWheel = preWheel;
    }

    public Wheel getNextWheel() {
        return nextWheel;
    }

    public void setNextWheel(Wheel nextWheel) {
        this.nextWheel = nextWheel;
    }
}
