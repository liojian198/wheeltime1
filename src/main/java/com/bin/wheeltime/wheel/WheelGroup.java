package com.bin.wheeltime.wheel;

import com.bin.wheeltime.ticknode.TickNode;

public class WheelGroup {

    private Long currentTick;
    private Wheel[] wheels;

    public WheelGroup(Integer wheelCounts, Integer wheelSize) {
        wheels = new Wheel[wheelCounts];
        for(int i = 0; i < wheelCounts; i++) {
            wheels[i] = new Wheel(wheelSize);
        }

        //设置前后关系
        wheels[0].setNeighbours(null, wheels[1]);
        for(int i = 1; i <= wheelCounts - 2; i ++) {
            wheels[i].setNeighbours(wheels[i - 1], wheels[i + 1]);
        }
        wheels[wheelCounts - 1].setNeighbours(wheels[wheelCounts - 2], null);
        //计算各个轮的基数
        for(int i = 0 ; i < wheelCounts; i++) {
            wheels[i].calcTicketMS();
        }
    }

    public boolean addTickNode(TickNode node, Long relativeTicks) {
        node.setAbsoluteTick(currentTick);
        node.setRelativeTick(relativeTicks);
        return wheels[0].add(node, relativeTicks);
    }

    public TickNode tick() {
        TickNode list = wheels[0].tick(currentTick);
        currentTick ++;
        return list;
    }

    public Long getCurrentTick() {
        return currentTick;
    }

    public void setCurrentTick(Long currentTick) {
        this.currentTick = currentTick;
    }
}
