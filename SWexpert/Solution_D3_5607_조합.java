/**
* 37,176 kb
* 242 ms
* 죽고싶었다..ㅠ
*/

package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D3_5607_조합 {
	
	static final long P = 1234567891;
	static int T, N, R;
	static long[] memo = new long[1000001];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());	// test case
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// n (nCr)
			R = Integer.parseInt(st.nextToken());	// r (nCr)
            
            memo[0]=1;
			for(int i=1; i<N+1; i++) {
				memo[i]=memo[i-1]*i%P;
			}
	
			/**
			 * 페르마의 소정리 이용
			 * B^-1 = B^(P-2)
			 * A*B^(P-2) = (n!)*(r!(n-r)!)^(P-2) % P
			 * 
			 */
			long ans = 0;
			long A = memo[N]%P;
			long B = power(memo[R], P-2)%P;
			long C = power(memo[N-R], P-2)%P;
			
			ans = A*B%P;
			ans = ans*C%P;
			System.out.println("#"+(t+1)+" "+ans);
			
		}	// test case end
	}
	
	private static long power(long a, long n) {
	      if(n==1) return a;
	      else {
	         if(n%2==0) {
	            return power(a*a%P,n/2);
	         } else {
	            return (power(a*a%P,n/2)*a) % P;
	         }
	      }
	   }

//	private static long factorial(int n) {
//		if(memo[n]>0) return memo[n];
//		if(n==1) return memo[1]=1;
//		
//		memo[n] = factorial(n-1) * n;
//		
//		return memo[n];
//	}
}
