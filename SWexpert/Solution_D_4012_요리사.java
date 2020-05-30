/**
 * 22,788 kb
 * 309 ms
 * using DFS
 * 20m
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D_4012_요리사 {
	
	static int T, N, ans;
	static int[][] map;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());	//test case
		
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());	//음식개수
			map = new int[N][N];	//맛
			visited = new boolean[N];	//visited check
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = Integer.MAX_VALUE;
			dfs(0, 0);	//pre, idx
			
			sb.append('#').append(t+1).append(' ').append(ans).append('\n');
		}	//test case end
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int pre, int idx) {
		if(ans==0) return;
		if(idx==N/2) {
			int sum;
			sum = flavor();
			ans = Math.min(ans, sum);
			
			return;
		}
		
		for(int i=pre; i<N; i++) {
			if(visited[i]) continue;
			visited[i]=true;
			dfs(i, idx+1);
			visited[i]=false;
		}
	}

	private static int flavor() {
		int A=0, B=0;
		for(int i=0; i<N; i++) {
			if(visited[i]) {	//A팀
				for(int j=0; j<N; j++) {
					if(i==j) continue;
					if(visited[j]) A+=map[i][j];
					
				}
			}else {	//B팀
				for(int j=0; j<N; j++) {
					if(i==j) continue;
					if(!visited[j]) B+=map[i][j];
				}
			}
		}
		
		return Math.abs(A-B);
	}

}
