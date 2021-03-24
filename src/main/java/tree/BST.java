package tree;

public class BST<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node left, right;
        public Node(E e){
            this.e = e;
            this.left = this.right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

//    public void add(E e){
//        if(root == null){
//            root = new Node(e);
//            ++ size;
//        }else{
//
//        }
//    }
//    递归终止条件过于臃肿 而且进行了多次比较
//    private void add(Node node, E e){
//        if(e.equals(node.e)){
//            return;
//        }else if(e.compareTo(node.e) < 0 && node.left == null){
//            node.left = new Node(e);
//            ++ size;
//            return;
//        }else if(e.compareTo(node.e) > 0 && node.right == null) {
//            node.right = new Node(e);
//            ++size;
//            return;
//        }
//        if(e.compareTo(node.e) < 0){
//            add(node.left, e);
//        }else{
//            add(node.right, e);
//        }
//    }

    public void add(E e){
        root = add(root, e);
    }
    private Node add(Node node, E e){
        if(node == null){
            ++ size;
            return new Node(e);
        }
        if(e.compareTo(node.e) < 0){
            node.left =  add(node.left, e);
        }else if(e.compareTo(node.e) > 0){
            node.right = add(node.right, e);
        }
        return node;
    }
}
