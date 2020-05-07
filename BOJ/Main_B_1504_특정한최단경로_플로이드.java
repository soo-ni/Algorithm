package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1504_특정한최단경로_플로이드 {

	static int N, E, V1, V2;
	static int[][] map = new int[801][801];
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 정점
		E = Integer.parseInt(st.nextToken());	// 간선
		for(int i=0; i<N+1; i++) {
			for(int j=0; j<N+1; j++) {
				if(i==j) map[i][j]=0;
				else map[i][j]=INF;
			}
		}
		
		int u, v, w;
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			map[u][v] = Math.min(map[u][v], w);
			map[v][u] = Math.min(map[v][u], w);
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		V1 = Integer.parseInt(st.nextToken());
		V2 = Integer.parseInt(st.nextToken());
		
		for(int k=1; k<N+1; k++) {	// 경유
			for(int i=1; i<N+1; i++) {	// 출발
				for(int j=1; j<N+1; j++) {	// 도착
					if(map[i][k]==INF || map[k][j]==INF) continue;
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}
		
		int ans = INF;
		if(map[1][V1]!=INF && map[V1][V2]!=INF && map[V2][N]!=INF) {
			ans = Math.min(ans, map[1][V1]+map[V1][V2]+map[V2][N]);
		}
		if(map[1][V2]!=INF && map[V2][V1]!=INF && map[V1][N]!=INF) {
			ans = Math.min(ans, map[1][V2]+map[V2][V1]+map[V1][N]);
		}
		
		if(ans==INF) System.out.println(-1);
		else System.out.println(ans);
		
	}
	
}
