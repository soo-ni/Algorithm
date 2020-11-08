/**
 * 
 * 54184 kb
 * 204 ms
 * using DP
 * 	
 */

package study_Sep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_12865_평범한배낭 {
	
	static int N, K;
	static int[][] map, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][2];
		dp = new int[N+1][K+1];
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<K+1; j++) {
				if(map[i][0]<=j) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-map[i][0]]+map[i][1]);
				}else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}

}
