package tree;

import map.BSTMap;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {
    private class Node{
        public K key;
        public V value;
        public int height;
        public Node left, right;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }
    private Node root;
    private int size;

    public void add(K key, V value) {
        add(root, key, value);
    }

    private int getHeight(Node node){
        return node == null ? 0 : node.height;
    }

    private int getBalanceFactor(Node node){
        if(node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for(int i = 1; i < keys.size(); ++ i){
            if(keys.get(i - 1).compareTo(keys.get(i)) > 0){
                return false;
            }
        }
        return true;
    }
    private void inOrder(Node node, ArrayList<K> keys){
        if(node == null){
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }
    public boolean isBalanced(){
        return isBalanced(root);
    }
    private boolean isBalanced(Node node){
        if(node == null){
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1){
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    private Node add(Node node, K key, V value){
        if(node == null){
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
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balanceFactor = getBalanceFactor(node);

        // 左侧的左侧添加了节点 LL
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            return rightRotate(node);
        }
        // 右侧的右侧添加了节点 RR
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0){
            return leftRotate(node);
        }
        // 左侧的右侧添加了节点 LR
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            // 先操作左侧的右子树
            node.left = leftRotate(node.left);
            // LL
            return rightRotate(node);
        }
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            // 先操作右侧的左子树
            node.right = rightRotate(node.right);
            // RR
            return leftRotate(node);
        }
        return node;
    }
    private Node rightRotate(Node y){
        Node x = y.left;
        Node t3 = x.right;
        x.right = y;
        y.left = t3;

        // 更新高度，只有x和y的高度会改变。先更新y
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }
    private Node leftRotate(Node y){
        Node x = y.right;
        Node t3 = x.left;
        x.left = y;
        y.right = t3;

        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }

    public V remove(K key) {
        Node node = remove(root, key);
        return node == null ? null : node.value;
    }

    private Node remove(Node node, K key){
        if(node == null){
            return null;
        }
        Node retNode;
        if(key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            retNode = node;
        }else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            retNode =  node;
        }else{
            // 找到该元素，删除
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                -- size;
                retNode = rightNode;
            }
            else if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                -- size;
                retNode = leftNode;
            }else{
                Node successorNode = minimum(node.right);
                successorNode.right = removeMin(node.right);
                successorNode.left = node.left;
                node.left = node.right = null;
                retNode = successorNode;
            }
        }

        if(retNode == null){
            return null;
        }
        // retNode
        int balanceFactor = getBalanceFactor(retNode);
        // 左侧的左侧添加了节点 LL
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0){
            return rightRotate(retNode);
        }
        // 右侧的右侧添加了节点 RR
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0){
            return leftRotate(retNode);
        }
        // 左侧的右侧添加了节点 LR
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            // 先操作左侧的右子树
            retNode.left = leftRotate(retNode.left);
            // LL
            return rightRotate(retNode);
        }
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
            // 先操作右侧的左子树
            retNode.right = rightRotate(retNode.right);
            // RR
            return leftRotate(retNode);
        }
        return retNode;
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
            throw new IllegalArgumentException("The key is not exists in the AVLTree.");
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
