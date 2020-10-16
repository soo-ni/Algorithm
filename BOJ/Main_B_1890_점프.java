/**
 * 
 * 13264 kb
 * 92 ms
 * using DFS + DP
 * 
 */

package study_Sep;

import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Main_B_1890_점프 {
	
	static int N;
	static int[][] map;
	static long[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		dp = new long[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		
		long answer = dfs(0, 0);
		System.out.println(answer);
		
	}
	
	static int[] dx = {1, 0};
	static int[] dy = {0, 1};
	private static long dfs(int x, int y) {
		if(x==N-1 && y==N-1) return 1;
		if(dp[x][y]>-1) return dp[x][y];
		if(map[x][y]==0) return 0;
		
		dp[x][y] = 0;
		int nx, ny, next;
		for(int i=0; i<2; i++) {
			next = map[x][y];
			nx = x+next*dx[i];
			ny = y+next*dy[i];
			
			if(nx<0||ny<0||nx>N-1||ny>N-1) continue;
			dp[x][y] += dfs(nx, ny);
		}
		
		return dp[x][y];
	}
	
}
