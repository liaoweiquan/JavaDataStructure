package tree;

import com.sun.org.apache.regexp.internal.RE;

public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = RED; // 默认新节点是红色
        }
    }
    private Node root;
    private int size;

    public void add(K key, V value) {
        add(root, key, value);
        root.color = BLACK;
    }

    private Node add(Node node, K key, V value){
        if(node == null){
            // RBTree中添加一个新元素 默认插入红色节点
            ++ size;
            return new Node(key, value);
        }else{
            if(key.compareTo(node.key) < 0){
                node.left = add(node.left, key, value);
            }else if(key.compareTo(node.key) > 0){
                node.right = add(node.right, key, value);
            }else{
                node.value = value;
            }
        }
        if(isRed(node.left) && ! isRed(node.right)){
            node = leftRotate(node);
        }
        if(isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }
        if(isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }
        return node;
    }


    /**
     * 左旋转
     * x的颜色<-node的颜色
     * node颜色<-红色
     * 2-结点转换成3-节点 or 3-结点转换成临时4-节点
     * @param node
     * @return
     */
    private Node leftRotate(Node node){
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     * 右旋转
     * @param node
     * @return
     */
    private Node rightRotate(Node node){
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }
    private boolean isRed(Node node){
        return node != null && node.color == RED;
    }
    private void flipColors(Node node){
        node.color = node.color == BLACK ? RED : BLACK;
    }
    public V remove(K key) {
        Node node = remove(root, key);
        return node == null ? null : node.value;
    }

    // BST的删除
    private Node remove(Node node, K key){
        if(node == null){
            return null;
        }
        if(key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            return node;
        }else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            return node;
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
            Node successorNode = minimum(node.right);
            successorNode.right = removeMin(node.right);
            successorNode.left = node.left;
            node.left = node.right = null;
            return successorNode;
        }
    }
    private Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
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

    private Node getNode(Node node, K key){
        if(node == null){
            return null;
        }
        if(key.compareTo(node.key) == 0){
            return node;
        }else if(key.compareTo(node.key) < 0){
            return getNode(node.left, key);
        }else{
            return getNode(node.right, key);
        }
    }


    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if(node == null){
            throw new IllegalArgumentException("The key is not exists in the RBTree.");
        }
        node.value = newValue;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
