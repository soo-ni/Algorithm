/**
 * 
 * 27116 kb	
 * 304 ms
 * using DP
 * 
 */

package study_Sep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_13398_연속합2 {

	static int N, answer;
	static int[] map;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N];
		dp = new int[N][2];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][0] = map[0];
		dp[0][1] = map[0];
		
		answer = dp[0][0];
		for(int i=1; i<N; i++) {
			dp[i][0] = Math.max(dp[i-1][0]+map[i], map[i]);
			dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]+map[i]);
			
			answer = Math.max(answer, dp[i][0]);
			answer = Math.max(answer, dp[i][1]);
		}
		
		System.out.println(answer);
	}
}
