import heap.MaxHeap;
import map.LinkedListMap;
import set.BSTSet;
import set.LinkedListSet;
import stack.LinkedStack;
import tree.Merger;
import tree.SegmentTree;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, Integer::sum);
        System.out.println(segTree);
        System.out.println(segTree.query(1, 2));
    }
}
