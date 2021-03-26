package set;

import tree.AVLTree;

public class AVLTreeSet<E extends  Comparable<E>> implements Set<E> {
    private AVLTree<E, Integer> avlTree;

    public AVLTreeSet(){
        avlTree = new AVLTree<>();
    }
    @Override
    public void add(E e) {
        avlTree.add(e, 1);
    }

    @Override
    public void remove(E e) {
        avlTree.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return avlTree.contains(e);
    }

    @Override
    public int getSize() {
        return avlTree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avlTree.isEmpty();
    }
}
