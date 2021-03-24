package linked;

public class LinkedList<E> {
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }
        public Node(E e){
            this(e, null);
        }
        public Node(){
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;
    public LinkedList(){
        dummyHead = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    public void addFirst(E e){
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
//        head = new Node(e, head); // more elegant
        add(e, 0);
        ++ size;
    }
    public void add(E e, int index){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("The index is out of boundary");
        }
//        if(index == 0){
//            addFirst(e);
//            return;
//        }
//
        Node prev = dummyHead;
        for(int i = 0; i < index; ++ i){
            prev = prev.next;
        }
        Node node = new Node(e);
        node.next = prev.next;
        prev.next = node;
    }
    public void addLast(E e){
        add(e, size);
    }
    public E get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("The index is out of boundary");
        }
        Node cur = dummyHead.next;
        for(int i = 0; i < index; ++ i){
            cur = cur.next;
        }
        return cur.e;
    }
    public E getFirst(){
        return get(0);
    }
    public E getLast(){
        return get(size - 1);
    }
    public void set(E e, int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("The index is out of boundary");
        }
        Node cur = dummyHead.next;
        for(int i = 0; i < index; ++ i){
            cur= cur.next;
        }
        cur.e = e;
    }
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while(cur != null){
            if(e.equals(cur.e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("The index is out of boundary");
        }
        Node prev = dummyHead;
        for(int i = 0; i < index; ++ i){
            prev = prev.next;
        }
        Node ret = prev.next;
        prev.next = ret.next;
        ret.next = null;
        -- size;
        return ret.e;
    }

    public E removeFirst(){

        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = dummyHead.next;
        while(cur != null){
            sb.append(cur).append("->");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }
}
