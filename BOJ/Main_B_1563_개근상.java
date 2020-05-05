/**
 * 13032 kb
 * 76 ms
 * using DFS + MEMO
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_B_1563_개근상 {
	static int N;
	static long[][][] dp;
	static final int MOD = 1000000;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new long[1001][2][3];	// 날짜, 지각, 결석
		for(int i=0; i<1001; i++) {
			Arrays.fill(dp[i][0], -1);
			Arrays.fill(dp[i][1], -1);
		}
		
		System.out.println(dfs(0, 0, 0));
	}
	
	private static long dfs(int idx, int L, int A) {
		if(L==2) return 0;
		if(A==3) return 0;
		
		if(idx==N) {
			return 1;
		}
		
		if(dp[idx][L][A]!=-1) return dp[idx][L][A];
		long sum = dfs(idx+1, L, 0) + dfs(idx+1, L+1, 0) + dfs(idx+1, L, A+1);
		return dp[idx][L][A]=sum%MOD;
	}

}
