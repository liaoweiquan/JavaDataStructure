import heap.MaxHeap;
import map.LinkedListMap;
import set.BSTSet;
import set.LinkedListSet;
import stack.LinkedStack;
import tree.AVLTree;
import tree.Merger;
import tree.SegmentTree;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        for(int i = 1; i < 50; ++ i){
            avlTree.add(i, i);
            if(!avlTree.isBalanced()){
                System.out.println("NO Balance TREE!");
            }
        }
        avlTree.remove(3);
        avlTree.remove(6);
        if(avlTree.isBalanced()) {
            System.out.println("Still balanced!");
        }
    }
}
