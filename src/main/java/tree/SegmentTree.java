package tree;

import java.util.Arrays;

public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){
        this.merger = merger;
        data = (E[])new Object[arr.length];
        for(int i = 0; i < arr.length; ++ i){
            data[i] = arr[i];
        }
        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0, 0, arr.length - 1);
    }

    private void buildSegmentTree(int treeIndex, int l, int r){
        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        // 业务逻辑
        tree[treeIndex] = merger.merge(tree[leftTreeIndex] , tree[rightTreeIndex]);
    }

    public void set(int index, E e){
        if(index < 0 || index >= data.length){
            throw new IllegalArgumentException("The index is out of boundary.");
        }
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }
    private void set(int treeIndex, int l, int r, int index, E e){
        if(l == r){
            tree[treeIndex] = e;
            return;
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if(index >= mid + 1){
            set(rightTreeIndex, mid + 1, r, index, e);
        }else if(index <= mid){
            set(leftTreeIndex, l, mid, index, e);
        }
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }


    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length){
            throw new IllegalArgumentException("The index is out of boundary.");
        }
        return data[index];
    }

    public E query(int queryL, int queryR){
        if(queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR){
            throw new IllegalArgumentException("The query params is out of boundary.");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }
    private E query(int treeIndex, int l, int r, int queryL, int queryR){
        if(l == queryL && r == queryR){
            return tree[treeIndex];
        }
        int mid = (r - l) / 2 + l;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if(queryL >= mid + 1){
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        }else if(queryR <= mid){
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }else{
            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            return merger.merge(leftResult, rightResult);
        }
    }

    private int leftChild(int index){
        return 2 * index + 1;
    }

    private int rightChild(int index){
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < tree.length; ++ i){
            if(tree[i] != null){
                sb.append(tree[i]);
            }else{
                sb.append("null");
            }
            if(i != tree.length - 1){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
