package graph;

import java.util.Arrays;

public class GraphAlgorithms {
    private int[][] graph;
    private int size;
    private int flag;

    public static void main(String[] args) {
        int size = 6;
        int flag = -1;
        int[][] graph = new int[size][size];
        for(int i = 0; i < size; ++ i){
            for(int j = 0; j < size; ++ j){
                graph[i][j] = 0x3f3f3f3f;
            }
        }
        for(int i = 0; i < size; ++ i) graph[i][i] = 0;

        graph[0][1] = graph[1][0] = 1;
        graph[1][4] = graph[4][1] = 4;
        graph[4][5] = graph[5][4] = 6;
        graph[5][3] = graph[3][5] = 6;
        graph[0][3] = graph[3][0] = 4;
        graph[0][2] = graph[2][0] = 2;
        graph[1][2] = graph[2][1] = 3;
        graph[2][4] = graph[4][2] = 5;
        graph[2][5] = graph[5][2] = 5;
        graph[2][3] = graph[3][2] = 6;
        GraphAlgorithms graphAlgorithms = new GraphAlgorithms();
        System.out.println(graphAlgorithms.prim(graph, size));
    }

    public void Floyd(int[][] d, int size){
        for(int k = 0; k < size; ++ k){
            for(int i = 0; i < size; ++ i){
                for(int j = 0; j < size; ++ j){
                    if(d[i][k] + d[k][j] < d[i][j]){
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }
    }

    public int prim(int[][] graph, int size){
        int[] dis = new int[size];
        final int INF = 0x3f3f3f3f;
        int cnt = 0;
        boolean[] vis = new boolean[size];
        Arrays.fill(dis, 0x3f3f3f);
        Arrays.fill(vis, false);
        dis[0] = 0;
        for(int i = 0; i < size; ++ i){
            int u = -1, MIN = INF;
            for(int j = 0; j < size; ++ j){
                if(!vis[j] && dis[j] < MIN){
                    u = j;
                    MIN = dis[j];
                }
            }
            if(u == -1) return -1;
            vis[u] = true;
            cnt += dis[u];
            for(int v = 0; v < size; ++ v){
                if(!vis[v] && graph[u][v] != INF && graph[u][v] < dis[v]){
                    dis[v] = graph[u][v];
                }
            }
        }
        return cnt;
    }
    public void Dijkstra(int[][] graph, int size, int start){

        int[] dis = new int[size];
        boolean[] vis = new boolean[size];
        Arrays.fill(dis, 0x3f3f3f);
        Arrays.fill(vis, false);
        dis[0] = 0;
        dis[start] = 0;
        for(int i = 0; i < size; ++ i){
            // 找到dis[]中最小值的下标
            int u = -1, MIN = 0x3f3f3f;
            for(int j = 0; j < size; ++ j){
                if(! vis[j] && dis[j] < MIN){
                    u = j;
                    MIN = dis[j];
                }
            }
            if(u == -1) return;
            vis[u] = true;
            for(int v = 0; v < size; ++ v){
                if(! vis[v] && graph[u][v] != -1){
                    // u使得dis[v]缩小
                    if(dis[u] + graph[u][v] < dis[v]){
                        dis[v] = dis[u] + graph[u][v];
                    }
                }
            }
        }
    }
}
