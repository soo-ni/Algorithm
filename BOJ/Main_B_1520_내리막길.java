/**
 * 41580 kb	
 * 308 ms
 * using DFS + MEMO
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_1520_내리막길 {
	static int M, N;
	static int[][] map;
	static long[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());	// row
		N = Integer.parseInt(st.nextToken());	// col
		map = new int[M][N];
		dp = new long[M][N];
		for(int i=0; i<M; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		long ans=0;
		ans = dfs(0, 0);
		System.out.println(ans);
	}
	
	private static long dfs(int x, int y) {
		if(x==M-1 && y==N-1) return 1;
		if(dp[x][y]!=-1) return dp[x][y];
		
		long sum=0;
		if(x>0 && map[x-1][y]<map[x][y]) sum+=dfs(x-1, y);		// 상
		if(x<M-1 && map[x+1][y]<map[x][y]) sum+=dfs(x+1, y);	// 하
		if(y<N-1 && map[x][y+1]<map[x][y]) sum+=dfs(x, y+1);	// 우
		if(y>0 && map[x][y-1]<map[x][y]) sum+=dfs(x, y-1);		// 좌
		
		return dp[x][y]=sum;
	}
}
