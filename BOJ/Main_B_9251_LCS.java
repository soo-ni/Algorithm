/**
 * 
 * 18300 kb
 * 120 ms
 * dp
 * 
 */
package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_9251_LCS {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		int lenS1 = s1.length();
		int lenS2 = s2.length();
		
		int[][] dp = new int[lenS1+1][lenS2+1];
		
		int max = 0;
		for(int i=1; i<lenS1+1; i++) {
			for(int j=1; j<lenS2+1; j++) {
				if(s1.charAt(i-1)==s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1]+1;
				}else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
				max = Math.max(dp[i][j], max);
			}
		}
		
		System.out.println(max);
	}
}
