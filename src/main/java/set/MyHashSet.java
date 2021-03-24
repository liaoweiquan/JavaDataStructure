package set;

import java.util.Iterator;
import java.util.LinkedList;

class MyHashSet {
    private int capacity;
    private int size;
    private double loadFactory;
    private LinkedList[] lists;
    /** Initialize your data structure here. */
    public MyHashSet() {
        size = 0;
        capacity = 16;
        loadFactory = 0.75;
        lists = new LinkedList[capacity];
    }

    public int getSize(){return size;}
    public int getCapacity(){return capacity;}
    public void add(int key) {
        if(contains(key)) return;
        if(size + 1 > capacity){
            overSize();
        }
        int hash = hashCode(key, capacity);
        if(lists[hash] == null){
            lists[hash] = new LinkedList<Integer>();
        }
        lists[hash].addFirst(key);
        ++ size;
    }

    public void remove(int key) {
        if(! contains(key)) return;
        int hash = hashCode(key, capacity);
        Iterator it = lists[hash].iterator();
        while(it.hasNext()){
            if(it.next().equals(key)){
                it.remove();
                -- size;
                break;
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = hashCode(key, capacity);
        if(lists[hash] == null) return false;
        Iterator it = lists[hash].iterator();
        while(it.hasNext()){
            if(it.next().equals(key)){
                return true;
            }
        }
        return false;
    }

    private int hashCode(int key, int capacity){
//        int h;
//        return ((h = key.hashCode()) ^ ( h >>> 16));
        return (int)(key % (loadFactory * capacity));
    }
    private void overSize(){
        int newCapacity = capacity << 1;
        LinkedList[] newLists = new LinkedList[newCapacity];
        for(int i = 0; i < capacity; ++ i){
            if(lists[i] == null) continue;
            Iterator it = lists[i].iterator();
            while(it.hasNext()){
                int key = (int)it.next();
                int hash = hashCode(key, newCapacity);
                if(newLists[hash] == null){
                    newLists[hash] = new LinkedList<Integer>();
                }
                newLists[hash].addFirst(key);
            }
        }
        capacity = newCapacity;
        lists = newLists;
    }

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        for(int i = 1; i < 18; ++ i){
            myHashSet.add(i);
        }
        myHashSet.remove(17);
        if(myHashSet.contains(17)){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
        System.out.println("size:"+ myHashSet.getSize());
        System.out.println("capacity:" + myHashSet.getCapacity());
    }
}

/**
 * Your set.MyHashSet object will be instantiated and called as such:
 * set.MyHashSet obj = new set.MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
