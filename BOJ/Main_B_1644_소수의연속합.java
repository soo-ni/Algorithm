/**
 * 
 * 46256 kb
 * 240 ms
 * using Two Pointer
 * 소수 구하기: 에라토스테네스의 체 (https://marobiana.tistory.com/91)
 * 
 */
package study_Sep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_1644_소수의연속합 {
	
	static int N, answer;
	static int[] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[4000001];
		getPrime();
		
		answer = 0;
		int s = 0, e = 0, sum = 0;
		while(true) {
			if(sum<N) {
				sum+=map[e++];
			}else {
				sum-=map[s++];
			}
			
			if(sum==N) {
				answer++;
			}
			
			if(map[e] == 0) break;
			if(map[s] > N) break;
		}
		
		System.out.println(answer);
	}
	
	private static void getPrime() {
		int[] prime = new int[4000001];
		for(int i=0; i<4000001; i++) {
			prime[i]=i;
		}
		
		int sqrt = (int) Math.sqrt(4000001);
		for(int i=2; i<sqrt+1; i++) {
			if(prime[i]==0) continue;
			for(int j=i+i; j<4000001; j+=i) {
				prime[j]=0;
			}
		}
		
		int idx=0;
		for(int i=2; i<4000001; i++) {
			if(prime[i]>0) {
				map[idx++] = prime[i];
			}
		}
	}
}
