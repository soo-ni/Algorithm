/**
 * 
 * G5
 * 소수구하기: 에라토스테네스의 체
 * 21832 kb
 * 292 ms
 * 
 */
package study_2021_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1747_소수n팰린드롬 {
	
	static boolean[] check;
	static int N;
	static int INF=2000000;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		check = new boolean[INF+1];
		int sqrt = (int) Math.sqrt(INF);
		
		check[0]=true; check[1]=true;
		// 1. 소수인 수 확인
		for(int i=2; i<sqrt; i++) {
			for(int j=i*i; j<=INF; j+=i) {
				check[j]=true;
			}
		}
		
		// 2. N보다 큰 소수에서 팰린드롬 찾기
		boolean flag=false;
		int answer=0;
		for(int i=N; i<INF; i++) {
			if(!check[i]) {
				String str = String.valueOf(i);
				int s=0, e=str.length()-1;
				
				flag=true;
				while(s<e) {
					if(str.charAt(s)==str.charAt(e)) {
						s++;
						e--;
					}else if(str.charAt(s)!=str.charAt(e)) {
						flag=false;
						break;
					}
				}
			}
			if(flag) {
				answer=i;
				break;
			}
		}
		
		System.out.println(answer);
		
	}

}
