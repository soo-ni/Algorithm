/**
 * 13836 kb	
 * 100 ms
 * 1h 30m
 * using Dijkstra
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_1261_알고스팟 {

	static final int INF = Integer.MAX_VALUE;
	static int N, M, ans;
	static int[][] map, dijkstra;
	static PriorityQueue<Point> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());	// col
		N = Integer.parseInt(st.nextToken());	// row
		map = new int[N][M];	// map
		dijkstra = new int[N][M];	// weight

		for(int i=0; i<N; i++) {
			Arrays.fill(dijkstra[i], INF);
		}
		
		String s;
		for(int i=0; i<N; i++) {
			s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j]=s.charAt(j)-'0';
			}
		}
		
		ans = 0;
		queue = new PriorityQueue<Point>();
		
		queue.add(new Point(0, 0, 0));
		dijkstra[0][0]=0;
		go();
		
		System.out.println(ans);
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void go() {
		Point current; int x, y, nx, ny, cnt;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			cnt = current.cnt;
			
			if(x==N-1 && y==M-1) {
				ans=cnt;
				return;
			}
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
				
				if(dijkstra[nx][ny]>cnt+map[nx][ny]) {
					dijkstra[nx][ny]=cnt+map[nx][ny];
					queue.add(new Point(nx, ny, cnt+map[nx][ny]));
				}
			}
			
		}
	}

	static class Point implements Comparable<Point> {
		int x, y, cnt;
		public Point(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Point o) {
			return this.cnt-o.cnt;
		}
	}
}
