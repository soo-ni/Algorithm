/**
 * 333652 kb	
 * 796 ms
 * using DP
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_15486_퇴사2 {

	static int N;
	static int[][] work;
	static long[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 몇일
		work = new int[N+2][2];
		dp = new long[N+2];
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			work[i][0] = Integer.parseInt(st.nextToken());	// 날짜
			work[i][1] = Integer.parseInt(st.nextToken());	// 페이
		}
		
		for(int i=N; i>0; i--) {
			if(i+work[i][0]-1>N) {
				dp[i]=dp[i+1];
				continue;
			}
			
			dp[i] = Math.max(work[i][1]+dp[i+work[i][0]], dp[i+1]);
		}
		
		System.out.println(dp[1]);
	}
}
