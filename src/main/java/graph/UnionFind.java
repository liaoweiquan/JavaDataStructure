package graph;

public class UnionFind {
    private int count;
    private int[] parent;
    private int[] rank;

    public UnionFind(int count) {
        this.count = count;
        parent = new int[count];
        rank = new int[count];
        for(int i = 0; i < count; ++ i){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    private int find(int p){
        if(p != parent[p]){
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }
    public void unionElement(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot){
            return;
        }
        if(rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }else if(rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        }else{
            parent[pRoot] = qRoot;
            ++ rank[qRoot];
        }
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        uf.unionElement(1, 2);
        uf.unionElement(2, 3);
        uf.unionElement(3, 4);
        if(uf.isConnected(1, 9)){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }
}
