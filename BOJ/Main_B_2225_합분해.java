/**
 * 
 * 15032 kb
 * 216 ms
 * using DP	
 * 
 */

package study_Sep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_2225_합분해 {
	
	static int N, K;
	static long[][] dp;
	static long MOD = 1000000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new long[K+1][N+1];
		
		for(int i=0; i<N+1; i++) {
			dp[1][i] = 1;
		}
		
		for(int i=1; i<K+1; i++) {
			for(int j=0; j<N+1; j++) {
				for(int k=0; k<j+1; k++) {
					dp[i][j] += dp[i-1][j-k];
					dp[i][j] %= MOD;
				}
			}
		}
		
		System.out.println(dp[K][N]);
	}

}
