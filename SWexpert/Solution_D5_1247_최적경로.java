/**
* 26,308 kb
* 298 ms
*
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Points{
	int x, y;
	Points(int x, int y){
		this.x=x; this.y=y;
	}
}

public class Solution_D5_1247_최적경로 {

	static int T, N, ans;
	static Points[] customer;
	static Points home, office;
	static boolean[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			customer = new Points[N+1];
			selected = new boolean[N+1];
			
			int x, y;
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			office = new Points(x, y);
			customer[0] = office;
			
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			home = new Points(x, y);
			
			for(int i=0; i<N; i++) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				customer[i+1] = new Points(x, y);
			}
			
			Arrays.fill(selected, true);
			ans = Integer.MAX_VALUE;
			selected[0] = false;
			getPath(0, 0, 0);	// idx, num, sum
			
			System.out.println("#"+(t+1)+" "+ans);
		}	// test case
		
	}

	private static void getPath(int idx, int num, int sum) {
		int path;

		if(sum>ans) {
			return;
		}
		
		if(idx==N) {
			path = Math.abs(customer[num].x-home.x)
					+Math.abs(customer[num].y-home.y);
			if(sum+path<ans) {
				ans=sum+path;
			}
			
			return;
		}
		
		for(int i=1; i<N+1; i++) {
			if(selected[i]) {
				selected[i] = false;
				path = Math.abs(customer[num].x-customer[i].x)
						+Math.abs(customer[num].y-customer[i].y);
				getPath(idx+1, i, sum+path);
				selected[i] = true;
			}
		}
	}
}
