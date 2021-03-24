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
    public boolean contains(E e){
        return contains(root, e);
    }
    private boolean contains(Node node, E e){
        if(node == null){
            return false;
        }
        if(e.compareTo(node.e) == 0){
            return true;
        }else if(e.compareTo(node.e) < 0){
            return contains(node.left, e);
        }else{
            return contains(node.right, e);
        }
    }

    public E minimum(){
        if(isEmpty()){
            throw new IllegalArgumentException("The BST is empty.");
        }
        return minimum(root).e;
    }
    private Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }
    public E maximum(){
        if(isEmpty()){
            throw new IllegalArgumentException("The BST is empty.");
        }
        return maximum(root).e;
    }
    private Node maximum(Node node){
        if(node.right == null){
            return node;
        }
        return minimum(node.right);
    }

    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }
    private Node removeMin(Node node){
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            -- size;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }
    private Node removeMax(Node node){
        if(node.right == null){
            Node nodeLeft = node.left;
            node.left = null;
            -- size;
            return nodeLeft;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e){
        remove(root, e);
    }
    private Node remove(Node node, E e){
        if(node == null){
            return null;
        }
        if(e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
        }else if(e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
        }else{
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                -- size;
                return rightNode;
            }
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                -- size;
                return leftNode;
            }
            // 删除任意节点
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right); // size-- 已经执行了
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
        return node;
    }
}
