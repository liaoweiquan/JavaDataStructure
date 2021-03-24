package stack;

import linked.LinkedList;

public class LinkedStack<E> implements Stack<E>{

    private LinkedList<E> list;

    public LinkedStack(){
        list = new LinkedList<>();
    }


    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("stack.Stack: top ");
        sb.append(list);
        return sb.toString();
    }
}
