/**
 * 43388 kb	
 * 380 ms
 * 200416 모든쌍최단경로
 * using 플로이드 알고리즘: O(N^3) 
 * (간단해서 다익스트라: O(N^3) 보다 효율적)
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_11404_플로이드 {
	
	static final long P = 100000*100+1;
	static int N, M;
	static long[][] adjMatrix;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());	// vertex
		M = Integer.parseInt(br.readLine());	// edge
		adjMatrix = new long[N][N];	// weight
		
		// init matrix 
		for(int i=0; i<N; i++)
			Arrays.fill(adjMatrix[i], P);
		
		int u, v, w;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken())-1;	// start node
			v = Integer.parseInt(st.nextToken())-1;	// end node
			w = Integer.parseInt(st.nextToken());	// weight
			
			adjMatrix[u][v] = Math.min(w, adjMatrix[u][v]);
		}
		
		for(int k=0; k<N; k++) {	// k: 경유 노드
			for(int start=0; start<N; start++) {	// start: 시작노드
				if(start==k) continue;

				for(int end=0; end<N; end++) {	// end: 끝노드
					if(start==end || end==k) continue;
					adjMatrix[start][end] = Math.min(adjMatrix[start][end], adjMatrix[start][k]+adjMatrix[k][end]);
				}
			}
		}
		
		long temp;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				temp = adjMatrix[i][j];
				if(temp<P)
					sb.append(temp).append(' ');
				else
					sb.append(0).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}
	
	static class Node {
		int u, v, w;
		public Node(int u, int v, int w) {
			super();
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
}
