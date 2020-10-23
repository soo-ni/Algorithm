/**
 * 
 * 33204 kb	
 * 284 ms
 * using two pointers (?)
 * 
 */

package study_Sep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_2470_두용액 {
	
	static int N;
	static int[] arr, answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int answer1=0, answer2=0;
		int min = Integer.MAX_VALUE, sub;
		int s = 0, e = N-1;
		while(s<e) {
			/**
			 * sub: arr[s]+arr[e]
			 * min: Math.min(abs(0-sub))
			 * sub == 0: break
			 * sub<0 s++
			 * sub>0 e--
			 */
			
			sub = arr[s] + arr[e];
			if(sub==0) {
				answer1 = arr[s];
				answer2 = arr[e];
				break;
			}
			
			if(Math.abs(0-sub)<min) {
				answer1 = arr[s];
				answer2 = arr[e];
				min = Math.abs(0-sub);
			}
			
			if(sub<0) {
				s++;
			}else {
				e--;
			}
		}
		
		System.out.println(answer1 + " " + answer2);
	}

}
