/**
 * 15364 kb	
 * 168 ms
 * using BFS
 * map초기 세팅할 때, 미리 바꿔주기!
 * 
 */

package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_2146_다리만들기 {
	static int N, cnt, ans;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// row, col
		map = new int[N][N];	// map
		visited = new boolean[N][N];	// visited check
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cnt=2; queue = new LinkedList<Point>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==1) {
					queue.add(new Point(i, j, 0));
					map[i][j]=cnt;
					bfs();
					cnt++;
				}
			}
		}
		
		ans = Integer.MAX_VALUE;
		pqueue = new PriorityQueue<Point>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]>0) {
					pqueue.add(new Point(i, j, 0));
					find(map[i][j]);
					init();
				}
			}
		}
		
		System.out.println(ans);
	}
	
	private static void init() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				visited[i][j]=false;
			}
		}
	}

	private static void find(int island) {
		Point current; int x, y, num, nx, ny;
		boolean token=false;
		while(!pqueue.isEmpty()) {
			current = pqueue.poll();
			x = current.x;
			y = current.y;
			num = current.cnt;
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>N-1||ny>N-1) continue;
				if(map[nx][ny]==0 && !visited[nx][ny]) {
					pqueue.add(new Point(nx, ny, num+1));
					visited[nx][ny]=true;
				}
				
				if(map[nx][ny]!=0 && map[nx][ny]!=island) {
					ans = Math.min(ans, num);
					token=true;
				}
			}
			
			if(token) return;
		}
		
	}

	static PriorityQueue<Point> pqueue;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static LinkedList<Point> queue;
	private static void bfs() {
		Point current; int x, y, nx, ny;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>N-1||ny>N-1) continue;
				if(map[nx][ny]==1) {
					queue.add(new Point(nx, ny, 0));
					map[nx][ny]=cnt;
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
