/**
 *
 * 78124 kb
 * 464 ms
 * dp
 * 
 */
package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_14925_목장건설하기 {

	static int M, N;
	static int[][] map, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M+1][N+1];
		dp = new int[M+1][N+1];
		
		int temp;
		for(int i=1; i<M+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j]=temp;
			}
		}
		
		int L = 0;
		
		for(int i=1; i<M+1; i++) {
			for(int j=1; j<N+1; j++) {
				if(map[i][j]==0) {
					dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1])+1;
					L = Math.max(L, dp[i][j]);
				}
			}
		}
		
		System.out.println(L);
		
	}

}
