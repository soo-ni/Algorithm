/**
* 23,820 kb
* 112 ms
*
*/

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_7194_화섭이의미생물배양 {
	
	static int T, start, end, a, b, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			ans = Integer.MAX_VALUE;
			
			goHome();
			if(ans==Integer.MAX_VALUE) ans=-1;
			
			System.out.println("#"+(t+1)+" "+ans);
		}	// test case
	}

	private static void goHome() {
		
		int multi = 1;	// 곱
		int multi_cnt = 0;	// 곱한 수		
		int temp_multi;	// temp 나누기 위해서
		int gap;	// 남은 미생물 수
		
		if(b==1) { // b가 1인경우 무한루프
			if((end-start)%a==0) ans=(end-start)/a;			
			return;
		}
		
		while(end-(start*multi)>=0) {	// gap이 0이 될 때 까지
			gap = end-(start*multi);
			
			if(gap%a==0) {	// gap이 a로 나눠질 수 있다면
				int gap_usingA = gap/a;
				int add_cnt = 0;	// 더한 수
				temp_multi = multi;
				
				for(int i=0; i<=multi_cnt; i++) {
					add_cnt += gap_usingA / temp_multi;
					gap_usingA %= temp_multi;	// 나머지는 다음 차수에서 더해주기
					temp_multi /= b;	// 이전 차수로
				}
				
				ans = Math.min(add_cnt+multi_cnt, ans);
			}
			
			multi_cnt++;
			multi*=b;
		}
		
	}
	
}
