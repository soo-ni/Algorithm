/**
 * 14564 kb	
 * 104 ms
 * using DP
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_2156_포도주시식 {
	
	static int N;
	static int[] wine;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 포도주 개수
		wine = new int[N+1];
		dp = new int[N+1][2];	// 2개 연속 선택하는 경우 찾으려고?
		
		for(int i=1; i<N+1; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		if(N==1) {
			System.out.println(wine[1]);
			return;
		}
		
		dp[0][0]=0;
		dp[0][1]=0;
		dp[1][0]=wine[1];
		dp[1][1]=0;
		dp[2][0]=wine[1]+wine[2];
		dp[2][1]=wine[1];
		
		for(int i=3; i<N+1; i++) {
			dp[i][0]=Math.max(wine[i]+wine[i-1]+dp[i-3][0], Math.max(wine[i]+wine[i-1]+dp[i-2][1], wine[i]+dp[i-1][1]));	// 현재 i 선택했을때
			dp[i][1]=Math.max(dp[i-1][0], dp[i-1][1]);	// 현재 i 선택 안했을때
		}
		
		System.out.println(Math.max(dp[N][0], dp[N][1]));
	}

}
