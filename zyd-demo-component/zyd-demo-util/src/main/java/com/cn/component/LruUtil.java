package com.cn.component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zyd
 * @date 2019/11/15 10:21
 * @desc 最近访问最常使用算法
 */
public final class LruUtil<K, V> {

    private LruUtil() {
    }

    private Map<K, Node<K, V>> nodeMap;
    private int capacity;
    private Node<K, V> head;
    private Node<K, V> tail;

    public LruUtil(int capacity) {
        this.nodeMap = new HashMap<>();
        this.capacity = capacity;
    }

    /**
     * 设置内存
     * 1 判断nodeMap是否存在对应的K的node节点
     * 1.1 存在则替换node中节点的值,并且从链表中移除该节点,将节点放在头部
     * 2 如果不存在
     * 2.1 如果容量不满足，则从链表中移除尾巴节点，并且从map中移除该节点，然后将节点放到头部
     * 2.2 如果容量满足,同时直接插入nodeMap则直接放到头节点
     */
    public void set(K k, V v) {
        Node<K, V> node = nodeMap.get(k);
        if (node != null) {
            removeLinked(node);
        } else {
            node = new Node<>(k, v);
            if (nodeMap.size() >= capacity) {
                nodeMap.remove(tail.k);
                removeLinked(tail);
            }
            nodeMap.put(k, node);
        }
        setHead(k, node);
    }

    /**
     * 获取node判断是否存在
     * 如果存在则直接返回，并且将节点放到头节点
     */
    public V get(K k) {
        Node<K, V> node = nodeMap.get(k);
        if (node == null) {
            return null;
        }
        removeLinked(node);
        setHead(k, node);
        return node.v;
    }

    private void setHead(K k, Node<K, V> n) {
        if (head == null) {
            head = n;
            tail = n;
            return;
        }
        n.next = head;
        head.prev = n;
        head = n;
    }

    /**
     * 只有存在才会调用remove方法
     */
    private void removeLinked(Node<K, V> node) {
        //1 如果node前节点是空的话，则代表他是头节点,则指向下一个节点
        if (node.prev == null) {
            head = head.next;
        } else {
            node.prev.next = node.next;
        }
        //2 如果node后节点是空的话，则代表他是尾节点,则将前节点指向末节点
        if (node.next == null) {
            tail = node.prev;
        } else {
            node.next.prev = node.prev;
        }
        node.prev = null;
        node.next = null;
    }

    public static class Node<K, V> {
        private K k;
        private V v;
        private Node<K, V> prev;
        private Node<K, V> next;

        private Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }

}
