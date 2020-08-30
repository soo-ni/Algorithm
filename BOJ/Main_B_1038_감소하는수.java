package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_1038_감소하는수 {

	static int cnt, N;
	static int[][] dp = new int[10][10];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		cnt=0;
		for(int i=0; i<10; i++) {
			dp[0][i]=1;
			cnt++;
		}
		
		if(N>1023) {
			System.out.println(-1); return;
		}
		
		int[] selected = null;
		L: for(int i=1; i<10; i++) {
			for(int j=i; j<10; j++) {
				for(int k=0; k<j; k++) {
					dp[i][j]+=dp[i-1][k];
				}
				cnt+=dp[i][j];
				
				if(cnt>N) {
					cnt-=dp[i-1][j-1];
					selected = new int[i+1];
					selected[i] = j;
					flag=false;
					dfs(i, j, selected);	// 자릿수, 첫번째 자리수
					break L;
				}
			}
		}
		
		long ans=0;
		for(int i=selected.length-1; i>-1; i--) {
			ans += selected[i]*Math.pow(10, i);
		}
		System.out.println(ans);
	}
	
	static boolean flag;
	private static void dfs(int digit, int first, int[] selected) {
		if(flag) return;
		if(digit==0) {
			cnt++;
			if(cnt==N) {
				flag=true;
				return;
			}
			return;
		}
		
		for(int i=0; i<10; i++) {
			if(first>i) {
				selected[digit-1]=i;
				dfs(digit-1, i, selected);
			}
		}
	}

}
