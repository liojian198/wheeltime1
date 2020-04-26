package com.bin.wheeltime.ticknode;

import java.util.LinkedList;
import java.util.List;

public class TickNodeLinkedUtil {
    public static boolean addNodeAfterHead(TickNode head, TickNode node) {
        TickNode nextNode = head.getNextNode();
        //双向链表加入节点
        if(null != nextNode) {
            nextNode.setPreNode(node);
        }
        node.setNextNode(head.getNextNode());
        node.setPreNode(head);
        head.setNextNode(node);
        return true;
    }
}
