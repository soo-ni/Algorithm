/**
 * 20324 kb
 * 122 ms
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_5658_보물상자비밀번호 {

	static int T, N, K, rotation;
	static LinkedList<Integer> pw;
	static LinkedList<Long> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());	// test case
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// 개수
			K = Integer.parseInt(st.nextToken());	// 몇번째 큰수
			rotation = N/4;	// 회전 수
			pw = new LinkedList<Integer>();	// 숫자
			queue = new LinkedList<Long>();
			String s = br.readLine();
			int len = s.length();
			
			char c; int temp;
			for(int i=0; i<len; i++) {
				c = s.charAt(i);
				switch(c) {
				case 'A': temp=10; break;
				case 'B': temp=11; break;
				case 'C': temp=12; break;
				case 'D': temp=13; break;
				case 'E': temp=14; break;
				case 'F': temp=15; break;
				default: temp=c-'0'; break;
				}
				pw.add(temp);
			}
			
			getCode();
			
			for(int i=0; i<rotation; i++) {
				temp = pw.pollLast();
				pw.addFirst(temp);
				getCode();
			}
			Collections.sort(queue, null);
			
			len = rotation*rotation;
			int k=0; long pre=0, tempL=0;
			while(!queue.isEmpty()) {
				tempL=queue.pollLast();
				if(pre!=tempL) {
					k++; pre=tempL;
					if(k==K) break;
				}
			}
			
			sb.append("#"+(t+1)+" "+pre+"\n");
		}	// test case end
		System.out.println(sb.toString());
	}
	
	private static void getCode() {
		int j = rotation-1;
		long sum = 0;
		for(int i=0; i<N; i++) {
			sum += (long)pw.get(i)*Math.pow(16, j);
			j--;
			if(j<0) {
				queue.add(sum);
				j=rotation-1;
				sum = 0;
			}
		}
	}
}
