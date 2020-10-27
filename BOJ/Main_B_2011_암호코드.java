/**
 * 
 * 20284 kb	
 * 152 ms
 * using DP
 * 
 */

package study_Sep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_2011_암호코드 {
	
	
	static int[] map, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int N = s.length();
		map = new int[N+1];
		dp = new int[1000000];
		
		for(int i=1; i<N+1; i++) {
			map[i] = s.charAt(i-1)-'0';
		}
		
		dp[0] = 1;
		for(int i=1; i<N+1; i++) {
			if(map[i]>0) {
				dp[i] = (dp[i-1]+dp[i]) % 1000000;
			}
			
			int next = map[i-1]*10 + map[i];
			
			if(next>9 && next<27) {
				dp[i] = (dp[i-2]+dp[i]) % 1000000;
			}
		}
		
		int answer = dp[N]%1000000;
		System.out.println(answer);		
	}

}
