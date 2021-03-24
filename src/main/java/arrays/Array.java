package arrays;

import java.util.Arrays;

public class Array <E>{
    private E[] data;
    private int size;
//    private int capacity;


    public Array(int capacity){
//        this.capacity = capacity;
        this.size = 0;
        data = (E[])new Object[capacity];
    }
    public Array(){
//        this(10);
        this(0);
        data = (E[])new Object[10];
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    public void addLast(E e){
//        if(size == data.length){
//            throw new IllegalArgumentException("AddLast failed. Arrays.Array is full.");
//        }
//        data[size] = e;
//        ++ size;
        add(size, e);
    }
    public void addFirst(E e){
        add(0, e);
    }
    public void add(int index, E e){

        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Required index >= 0 and index < size");
        }
        if(size == data.length){
//            throw new IllegalArgumentException("Add failed. Arrays.Array is full.");
            resize(data.length << 1);
        }
        for(int i = size - 1; i >= index; -- i){
            data[i + 1] = data[i];
        }
        data[index] = e;
        ++ size;
    }

    public E get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Add failed. Required index >= 0 and index < size");
        }
        return data[index];
    }

    public E getLast(){
        return get(size - 1);
    }

    public E getFirst(){
        return get(0);
    }

    public void set(int index, E e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Required index >= 0 and index < size");
        }
        data[index] = e;
    }

    public boolean contains(E e){
        for(int i = 0; i < size; ++ i){
            if(data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    public int find(E e){
        for(int i = 0; i < size; ++ i){
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    public E remove(int index){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Required index >= 0 and index < size");
        }
        E res = data[index];
        for(int i = index + 1; i < size; ++ i){
            data[i - 1] = data[i];
        }
        -- size;
        data[size] = null;
        if(size == data.length >> 2 && (data.length >> 1 > 0)){
            // lazy resize
            // 防止复杂度过大
            resize(data.length >> 1);
        }
        return res;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    public boolean removeElement(E e){
        int index = find(e);
        if(index != -1){
            remove(index);
            return true;
        }
        return false;
    }

    public void removeAllElement(E e){
        int index = find(e);
        while (index != -1){
            remove(index);
            index = find(e);
        }
    }

    private void resize(int newCapacity){
//        int newCapacity = data.length << 1;
        E[] newData = (E[]) new Object[newCapacity];
        for(int i = 0; i < size; ++ i){
            newData[i] = data[i];
        }
        this.data = newData;
    }

    @Override
    public String toString() {
        return "Arrays.Array{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }
}
