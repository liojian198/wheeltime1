package com.bin.wheeltime;

import com.bin.wheeltime.ticknode.TickNode;
import com.bin.wheeltime.wheel.WheelGroup;

public class WheelTimeTest {
    public static void main(String[] args) {
        final WheelGroup wheelGroup = new WheelGroup(10, 10);
        final Long startTime = System.currentTimeMillis()/1000;
        wheelGroup.setCurrentTick(startTime);
        wheelGroup.addTickNode(new TickNode(), 5L);
        wheelGroup.addTickNode(new TickNode(), 10L);
        wheelGroup.addTickNode(new TickNode(), 20L);
        wheelGroup.addTickNode(new TickNode(), 50L);
        wheelGroup.addTickNode(new TickNode(), 200L);
        wheelGroup.addTickNode(new TickNode(), 500L);
        new Thread(
                new Runnable() {
                    public void run() {
                        int i = 0;
                        while (i < 1000)
                        {
                            TickNode node = wheelGroup.tick();
                            if(null != node) {
                                System.err.println(System.currentTimeMillis()/1000 - startTime);
                            }
                            i++;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();
    }
}
