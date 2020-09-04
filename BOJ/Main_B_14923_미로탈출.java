/**
 * 
 * 119392 kb	
 * 1040 ms
 * bfs
 * 
 */
package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_14923_미로탈출 {

	static int N, M;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 행
		M = Integer.parseInt(st.nextToken());	// 열
		map = new int[N][M];
		Point start = new Point(0, 0, 0, 0);
		Point end = new Point(0, 0, 0, 0);
		
		int x, y;
		for(int i=0; i<2; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken())-1;
			y = Integer.parseInt(st.nextToken())-1;
			if(i==0) start = new Point(x, y, 0, 0);
			else end = new Point(x, y, 0, 0);
		}
		
		int temp;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
			}
		}
		
		int answer = bfs(start, end);
		System.out.println(answer);
		
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static int bfs(Point start, Point end) {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		boolean[][][] visited = new boolean[N][M][2];
		queue.add(start);
		visited[start.x][start.y][start.k]=true;
		
		Point current; int x, y, nx, ny, cnt, k;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			cnt = current.cnt;
			k = current.k;
			
			if(x==end.x && y==end.y) {
				return cnt;
			}
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
				if(visited[nx][ny][k]) continue;
				
				if(map[nx][ny]>0) {
					if(k>0) continue;
					visited[nx][ny][k+1]=true;
					queue.add(new Point(nx, ny, cnt+1, k+1));
				}else {
					visited[nx][ny][k]=true;
					queue.add(new Point(nx, ny, cnt+1, k));
				}
			}
			
		}
		
		return -1;
	}

	static class Point implements Comparable<Point> {
		int x, y, cnt, k;
		public Point(int x, int y, int cnt, int k) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.k = k;
		}
		@Override
		public int compareTo(Point o) {
			return this.cnt-o.cnt;
		}
	}
}
