/**
* DFS, BFS 쓰면 터짐
* DP도 터짐
* 문제 이상해ㅡㅡ
*
*/

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_D4_7194_화섭이의미생물배양 {
	
	static int T, start, end, a, b, ans;
	static boolean visited[];
	static int dp[];// = new int[100000001];
	
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
			visited = new boolean[end+1];
			dp = new int[end+1];
			
			ans = Integer.MAX_VALUE;
			goHomeDP();
			ans = dp[start];
			if(ans==Integer.MAX_VALUE) ans=-1;
			
			System.out.println("#"+(t+1)+" "+ans);
		}	// test case
	}
	
	private static void goHomeDP() {
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[end]=0;
		if(b==1) {
            if((end-start)%a==0) {
                dp[start] = (end-start)/a;
            }
            return;
        }
		
		int aX, bX;		
		for(int i=end; i>start; i--) {
			if(dp[i]<Integer.MAX_VALUE) {
				if(i%b>0) {
					aX = i-a;
					dp[aX] = Math.min(dp[i]+1, dp[aX]);
					continue;
				}
				
				aX = i-a;
				bX = i/b;
				dp[aX] = Math.min(dp[i]+1, dp[aX]);
				dp[bX] = Math.min(dp[i]+1, dp[bX]);
			}
				
		}
	}

	private static void goHomeDFS(int cur, int cnt) {
		if(cur>end) return;
		if(cur==end) {
			if(ans>cnt) ans=cnt;
			return;
		}
		
		int aX = cur+a;
		int bX = cur*b;

		if(aX<end+1) {
			goHomeDFS(aX, cnt+1);
		}
		
		if(bX<end+1) {
			goHomeDFS(bX, cnt+1);
		}

	}
	
	private static void goHomeBFS() {
		LinkedList<Point> queue = new LinkedList<Point>();
		queue.add(new Point(start, 0));
		visited[start]=true;
		
		Point current;		
		while(!queue.isEmpty()) {
			Collections.sort(queue);
			current = queue.poll();
			
			if(current.x==end) {
				if(ans>current.y) {
					ans=current.y;
				}
			}
			
			int aX = current.x+a;
			int bX = current.x*b;
			
			if(aX<end+1 && !visited[aX]) {
				queue.add(new Point(aX, current.y+1));
				visited[aX]=true;
			}

			if(bX<end+1 && !visited[bX]) {
				queue.add(new Point(bX, current.y+1));
				visited[bX]=true;
			}
			
		}
	}

	static class Point implements Comparable<Point> {
		int x, y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			return this.y-o.y;
		}
	}
}
