/**
 * 12968 kb	
 * 76 ms
 * using DP
 * 다시풀어보기ㅠㅠ
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_5557_1학년_답봄 {
	static int N;
	static int[] map;
	static long[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		map = new int[N];
		dp = new long[N][21];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][map[0]]=1;
		for(int i=1; i<N; i++) {
			for(int j=0; j<21; j++) {
				if(dp[i-1][j]>0) {
					if(j+map[i]<21) dp[i][j+map[i]]+=dp[i-1][j];
					if(j-map[i]>-1) dp[i][j-map[i]]+=dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[N-2][map[N-1]]);
	}
}
