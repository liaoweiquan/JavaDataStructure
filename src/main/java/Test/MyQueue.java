package Test;

import java.util.LinkedList;
import java.util.Stack;

class MyQueue {

    LinkedList<Integer> list;

    /** Initialize your data structure here. */
    public MyQueue() {
        list = new LinkedList<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        list.addLast(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        int res = list.getFirst();
        list.removeFirst();
        return res;
    }

    /** Get the front element. */
    public int peek() {
        return list.getFirst();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return list.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */