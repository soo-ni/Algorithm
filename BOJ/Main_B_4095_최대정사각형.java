/**
 * 
 * 316788 kb	
 * 1380 ms
 * using DP
 * 
 */

package study_Sep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_4095_최대정사각형 {
	
	static int N, M, answer;
	static int[][] map, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if(N==0 && M==0) break;
			
			map = new int[N+1][M+1];
			dp = new int[N+1][M+1];
			
			for(int i=1; i<N+1; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<M+1; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = 0;
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<M+1; j++) {
					// 왼위, 왼, 위 확인
					if(map[i][j]<1) continue;
					dp[i][j] = Math.min(dp[i-1][j-1], dp[i][j-1]);
					dp[i][j] = Math.min(dp[i-1][j], dp[i][j]) + 1;
					
					answer = Math.max(answer, dp[i][j]);
				}
			}
			
			sb.append(answer).append('\n');
			
		}
		
		System.out.println(sb.toString());
	}

}
