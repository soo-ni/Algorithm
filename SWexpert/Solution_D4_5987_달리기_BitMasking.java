/**
* 58,648 kb
* 170 ms
*
*/

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Sooni
 * Bit masking & Memoization 이용해서 달리기 풀기
 * memo: 뽑힌 player의 경우의 수 (ex. 1, 2, 3, 4가 뽑혔을 때 만들어질 수 있는 경우의 수)
 */

public class Solution_D4_5987_달리기_BitMasking {
	
	static int T, N, M;
	static int player[];
	static long memo[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			player = new int[N];	// player 순서 표현(i번째 있는 애는 1로 표시된 애보다 뒤에 있어야함)
			memo = new long[1<<N];	// N명의 자리 만들기
			Arrays.fill(memo, -1);
			
			int x, y;
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken())-1;
				y = Integer.parseInt(st.nextToken())-1;
				player[y] |= (1<<x);	// x는 y보다 앞에온다
			}
			
			System.out.println("#"+(t+1)+" "+running(0));
		}	// test case
	}

	private static long running(int current) {
		if(current==(1<<N)-1) return 1;	// 모두 뽑히면 한가지 경우밖에 없으므로 return 1
		if(memo[current]>-1) return memo[current];
		
		long sum = 0;
		for(int i=0; i<N; i++) {
			if((current&(1<<i))==0) {	// 아직 안뽑힌 경우
				if((player[i]&current)!=player[i]) continue;	// 내 앞에 나와야하는 애가 없으면 continue
				sum += running(current|(1<<i));
			}
		}
		
		return memo[current] = sum;
	}
}
