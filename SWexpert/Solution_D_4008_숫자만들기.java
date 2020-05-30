/**
 * 21,048 kb
 * 136 ms
 * using DFS
 * ㅎ..잘생각해보기..
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D_4008_숫자만들기 {
	
	static int T, N, min, max;
	static int[] numbers, operator, selected;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());	//test case
		
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());	//숫자 갯수
			numbers = new int[N];	//숫자
			operator = new int[4];	//연산자
			
			st = new StringTokenizer(br.readLine(), " ");
			int temp;
			for(int i=0; i<4; i++) {
				temp = Integer.parseInt(st.nextToken());
				operator[i] = temp;
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			selected = new int[N-1];
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			dfs(0);	//idx, 순열돌리기
			
			sb.append('#').append(t+1).append(' ').append(max-min).append('\n');
		}	//test case end
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int idx) {
		if(idx==N-1) {
//			for(int h: selected) System.out.print(h+" "); System.out.println();
			int sum = calculate();
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(operator[i]>0) {
				selected[idx]=i;
				operator[i]--;
				dfs(idx+1);
				operator[i]++;
			}
		}
	}

	private static int calculate() {
		int temp, sum=numbers[0];
		for(int i=0; i<N-1; i++) {
			temp = selected[i];
			switch(temp) {
			case 0: sum=sum+numbers[i+1]; break;
			case 1: sum=sum-numbers[i+1]; break;
			case 2: sum=sum*numbers[i+1]; break;
			case 3: sum=sum/numbers[i+1]; break;
			}
		}
		
		return sum;
	}

}
