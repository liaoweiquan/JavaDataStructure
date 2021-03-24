//import java.util.*;
//
//class MyHashMap {
//    private int capacity;
//    private int size;
//    private double loadFactory;
//    private Arrays.LinkedList<Node>[] lists;
//    /** Initialize your data structure here. */
//    public MyHashMap() {
//        size = 0;
//        capacity = 16;
//        loadFactory = 0.75;
//        lists = new Arrays.LinkedList[capacity];
//    }
//
//
//    /** value will always be non-negative. */
//    public void put(int key, int value) {
//
//        int hash = hashCode(key, this.capacity);
//
//        Node node = new Node(key, value);
//        if( lists[hash] == null){
//            if((size + 1) * loadFactory >= capacity){
//                overCapacity();
//            }
//            lists[hash] = new Arrays.LinkedList<>();
//            lists[hash].addFirst(node);
//            ++ size;
//        }else{
//            // find update?
//            Iterator<Node> it = lists[hash].iterator();
//            while (it.hasNext()){
//                Node cur = it.next();
//                if(cur.first == key){
//                    it.remove();
//                    cur.second = value;
//                    lists[hash].addFirst(cur);
//                    return;
//                }
//            }
//            if((size + 1) * loadFactory >= capacity){
//                overCapacity();
//                ++ size;
//            }
//            if(lists[hash] == null){
//                lists[hash] = new Arrays.LinkedList<>();
//            }
//            lists[hash].addFirst(node);
//        }
//    }
//
//    private void overCapacity(){
//        int newCapacity = this.capacity << 1;
//        Arrays.LinkedList<Node>[] newLists = new Arrays.LinkedList[newCapacity];
//        for(int i = 0; i < this.capacity; ++ i){
//            if(lists[i] != null){
//                for (Node node : lists[i]) {
//                    int hash = hashCode(node.first, newCapacity);
//                    if (newLists[hash] == null) {
//                        newLists[hash] = new Arrays.LinkedList<>();
//                    }
//                    newLists[hash].addFirst(node);
//                }
//            }
//        }
//        this.capacity = newCapacity;
//        this.lists = newLists;
//    }
//
//    private int hashCode(int key, int capacity){
//        int h = 1;
//        Object obj = key;
//        h ^= obj.hashCode();
//        h ^= (h >>> 20) ^ (h >>> 12);
//        return (h ^ (h >>> 7) ^ (h >>> 4)) & (capacity - 1);
////        return key % capacity;
//    }
//    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
//    public int get(int key) {
//        int hash = hashCode(key, this.capacity);
//        if(lists[hash] == null){
//            return -1;
//        }
//        for (Node node : lists[hash]) {
//            if(node.first == key){
//                return node.second;
//            }
//        }
//        return -1;
//    }
//
//    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
//    public void remove(int key) {
//        int hash = hashCode(key, this.capacity);
//        if(lists[hash] != null){
//            Iterator<Node> it = lists[hash].iterator();
//            while(it.hasNext()){
//                if(it.next().first == key){
//                    it.remove();
//                    return;
//                }
//            }
//        }
//
//    }
//
//    private class Node{
//        int first;
//        int second;
//        Node(int first, int second){
//            this.first = first;
//            this.second = second;
//        }
//
//        public int getFirst() {
//            return first;
//        }
//
//        public int getSecond() {
//            return second;
//        }
//    }
//}
//
///**
// * Your MyHashMap object will be instantiated and called as such:
// * MyHashMap obj = new MyHashMap();
// * obj.put(key,value);
// * int param_2 = obj.get(key);
// * obj.remove(key);
// */