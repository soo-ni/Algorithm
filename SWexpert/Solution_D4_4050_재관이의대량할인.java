/**
 * 87,900 kb
 * 329 ms
 * ..?..머쓱..
 * 
 */

package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution_D4_4050_재관이의대량할인 {
	static int T, N;
	static ArrayList<Integer> clothes;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());	// test case
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// 옷 개수 
			clothes = new ArrayList<Integer>();
			
			st = new StringTokenizer(br.readLine(), " ");
			int temp;
			for(int i=0; i<N; i++) {
				temp = Integer.parseInt(st.nextToken()); 
				clothes.add(temp);
			}
			
			Collections.sort(clothes, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2-o1;
				}
			});
			
			int sum=0;
			for(int i=0; i<N; i++) {
				if((i+1)%3==0) continue;
				sum+=clothes.get(i);
			}
			
			sb.append("#"+(t+1)+" "+sum+"\n");
		}	// test case end
		
		System.out.println(sb.toString());
	}
}
