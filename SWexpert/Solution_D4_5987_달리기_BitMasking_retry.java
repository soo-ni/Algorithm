/**
* 64,260 kb
* 174 ms
*
*/

package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_5987_달리기_BitMasking_retry {
	
	static int T, N, M;
	static int[] player;
	static long[] memo;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		T =  Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N =  Integer.parseInt(st.nextToken());	
			M =  Integer.parseInt(st.nextToken());
			player = new int[N];	// 나보다 앞에있는 사람 저장
			memo = new long[1<<N];
			
			int x, y;
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				x =  Integer.parseInt(st.nextToken())-1;	// N이 1번부터 시작-1
				y =  Integer.parseInt(st.nextToken())-1;	// N이 1번부터 시작-1
				player[y] |= (1<<x);	// x가 y보다 앞에 나와야함
			}
			
			Arrays.fill(memo, -1);
			System.out.println("#"+(t+1)+" "+running(0));
		}	// test case
	}

	private static long running(int people) {	// 뽑힌 사람들의 bitmask
		if(people==(1<<N)-1) {	// 모두 다 뽑힌 경우는 1가지 경우밖에 없음
			return 1;
		}
		
		if(memo[people]>0) {
			return memo[people];
		}
		
		long sum=0;
		for(int i=0; i<N; i++) {
			if((people&(1<<i))==0) {	// 뽑히지 않았다면
				if((player[i]&people)!=player[i]) continue;	// 뽑으려고 하는 애 앞에 내 앞에 와야하는 모두가 없다면 뽑지 않음
				sum += running(people|(1<<i));	// 뽑아서 넘겨줌
			}
		}
		
		return memo[people]=sum;
	}
}
