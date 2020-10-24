/**
 * 
 * 14196 kb
 * 108 ms
 * using Two Pointers
 * 
 */

package study_Sep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_2003_수들의합2 {
	
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int s = 0, e = 0, sum = 0;
		int answer = 0;
		while(true) {
			if(sum > M && s < e) {
				sum -= arr[s++];
			}else {
				sum += arr[e++];
			}
			
			if(sum==M) answer++;
			if(e==N) break;
			
		}
		
		for(int i=s; i<N; i++) {
			if((sum-=arr[i])==M) answer++;
		}
		System.out.println(answer);
	}
}
