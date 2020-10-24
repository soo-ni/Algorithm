/***
 * 
 * 29488 kb	
 * 316 ms
 * using Two Pointers
 * 
 */

package study_Sep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_2230_수고르기 {

	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		int s=0, e=1, sub, min=Integer.MAX_VALUE;
		while(true) {
			sub = arr[e]-arr[s];
			
			if(sub==M) {
				min=sub;
				break;
			}
			
			if(sub<M) {	// 차이가 M보다 작으면 e를 늘려준다
				e++;
			}else {
				if(s<e-1) s++;	// 차이가 M보다 크면 s를 늘려준다
				else e++;		// 차이가 M보다 큰데 s가 바로 직전에 있을 때
				
				min = Math.min(min, sub);
			}
			
			if(e==N) break;
		}
		
		for(int i=s; i<N-1; i++) {
			if((sub=arr[N-1]-arr[i])>M) {
				min = Math.min(min, sub);
			}
			
			if(sub==M) {
				min=sub;
				break;
			}
		}
		
		System.out.println(min);
	}
}
