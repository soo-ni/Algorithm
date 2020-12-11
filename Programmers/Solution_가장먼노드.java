import java.util.Arrays;
import java.util.LinkedList;

class Solution_가장먼노드 {
    
    static boolean[][] map;
    static int[] dijkstra;
    static final int INF = Integer.MAX_VALUE;
    static LinkedList<Integer> queue;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        map = new boolean[n+1][n+1];
        dijkstra = new int[n+1];
        queue = new LinkedList<Integer>();
        Arrays.fill(dijkstra, INF);
        
        int u, v;
        for(int i=0; i<edge.length; i++) {
            u = edge[i][0]; v = edge[i][1];
            map[u][v] = true; map[v][u] = true; // 양방향 간선 연결
        }
        
        dijkstra[1]=0;
        queue.add(1);
        
        solve(n);
        
        int max = 0, cnt=0;
        for(int i=2; i<n+1; i++) {
            if(dijkstra[i] > max) {
                max = dijkstra[i];
                cnt = 1;
            }else if(dijkstra[i]==max) {
                cnt++;
            }
        }
        
        // show();
        answer = cnt;
        return answer;
    }
    
    static public void show() {
        for(int i: dijkstra)
            System.out.print(i+" ");
    }
    
    static public void solve(int n) {
        
        int current;
        while(!queue.isEmpty()) {
            current = queue.poll();
            
            for(int i=1; i<n+1; i++) {
                if(current==i) continue;
                if(map[current][i]) {
                    if(dijkstra[i] > dijkstra[current]+1) {
                        // System.out.println("current: "+current+", next: "+i+" "+(dijkstra[current]+1));
                        dijkstra[i] = dijkstra[current]+1;
                        queue.add(i);
                    }
                }
            }
        }
    }
}
