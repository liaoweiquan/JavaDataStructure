package heap;

import arrays.Array;

public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    public int getSize(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    public void add(E e){
        data.addLast(e);
        shiftUp(data.getSize() - 1);
    }
    // shiftUp 比较与parentNode的大小 不满足则交换，直到满足
    private void shiftUp(int index){
        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0){
            int parentIndex = parent(index);
            data.swap(index, parentIndex);
            index = parentIndex;
        }
    }
    public E findMax(){
        if(data.isEmpty()){
            throw new IllegalArgumentException("The Heap is empty.");
        }
        return data.get(0);
    }
    public E extractMax(){
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        shiftDown(0);
        return ret;
    }
    private void shiftDown(int index){
        while(leftChild(index) < data.getSize()){
            int left = leftChild(index);
            if(left + 1 < data.getSize() && data.get(left + 1).compareTo(data.get(left)) > 0){
                // 有右节点 且 右节点 > 左节点
                // right = left + 1
                left ++;
            }
            if(data.get(index).compareTo(data.get(left)) >= 0){
                break; // 退出shiftDown
            }
            data.swap(index, left);
            index = left;
        }
    }

    // 取出堆中最大元素 替换成元素e
    public E replace(E e){
        E ret = findMax();
        data.set(0, e);
        shiftDown(0);
        return  ret;
    }

    private int parent(int index){
        if(index == 0){
            throw new IllegalArgumentException("Root does not have parent node.");
        }
        return (index - 1) / 2;
    }
    private int leftChild(int index){
        return index * 2 + 1;
    }
    private int rightChild(int index){
        return index * 2 + 2;
    }
}
