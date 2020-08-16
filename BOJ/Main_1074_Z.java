/**
 * 
 * 분할정복..? 재귀로도 풀어보기!
 * 12968 kb
 * 76 ms
 * 
 */

package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074_Z {

	static int n, r, c;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());	// 제곱
		r = Integer.parseInt(st.nextToken());	// 행
		c = Integer.parseInt(st.nextToken());	// 열
		
		int ans = 0;

		int size = (int) Math.pow(2, n);
		int row=0, col=0;
		while(size>0) {
			size/=2;
			
			//1사분면
			if(r<row+size && c<col+size) {
				ans+=size*size*0;
			}
			
			//2사분면
			else if(r<row+size && c>=col+size) {
				ans+=size*size*1;
				col+=size;
			}
			
			//3사분면
			else if(r>=row+size && c<col+size) {
				ans+=size*size*2;
				row+=size;
			}
			
			//4사분면
			else if(r>=row+size && c>=col+size) {
				ans+=size*size*3;
				row+=size;
				col+=size;
			}
		}	
		
		System.out.println(ans);
	}
	
}
