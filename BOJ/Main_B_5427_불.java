/**
 * 118948 kb	
 * 760 ms
 * using BFS
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_5427_불 {
	
	static int T, w, h, ans;
	static char[][] map;
	static LinkedList<Point> queueFire, queue;
	static boolean token;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			w = Integer.parseInt(st.nextToken());	// col
			h = Integer.parseInt(st.nextToken());	// row
			map = new char[h][w];	// map
			queueFire = new LinkedList<Point>();	// 불
			queue = new LinkedList<Point>();		// 상근
			visited = new boolean[h][w];
			
			String s; char c;
			for(int i=0; i<h; i++) {
				s = br.readLine();
				for(int j=0; j<w; j++) {
					c = s.charAt(j);
					map[i][j] = c;
					if(c=='*') queueFire.add(new Point(i, j, 0));
					if(c=='@') {
						queue.add(new Point(i, j, 0));
						visited[i][j]=true;
					}
				}
			}
			
			token=false;
			ans = 0;
			// 1. 불은 그냥 map을 갱신
			// 2. 그다음 상근이 옮기기
			while(true) {
				bfs();
				move();
				
				if(token) break;
				if(queue.isEmpty()) break;
			}
			
			if(!token)	System.out.println("IMPOSSIBLE");
			else		System.out.println(ans);
		}	// test case end
	}
	
	private static void move() {
		Point current; int nx, ny;
		int size = queue.size();
		while(size>0) {
			current = queue.poll();
			
			for(int i=0; i<4; i++) {
				nx = current.x+dx[i];
				ny = current.y+dy[i];
				
				if(nx<0||ny<0||nx>h-1||ny>w-1) {
					ans=current.cnt+1;
					token=true;
					return;
				}
				
				if(map[nx][ny]=='.' && !visited[nx][ny]) {
					queue.add(new Point(nx, ny, current.cnt+1));
					visited[nx][ny] = true;
				}
			}
			
			size--;
		}
	}

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void bfs() {
		int size = queueFire.size();
		Point current; int nx, ny;
		while(size>0) {
			current = queueFire.poll();
			
			for(int i=0; i<4; i++) {
				nx = current.x+dx[i];
				ny = current.y+dy[i];
				
				if(nx<0||ny<0||nx>h-1||ny>w-1) continue;
				if(map[nx][ny]=='.' || map[nx][ny]=='@') {	// @일때도 해줘야됨 ㅠㅠ
					queueFire.add(new Point(nx, ny, 0));
					map[nx][ny] = '*';
				}
			}
			
			size--;
		}
	}

	static class Point {
		int x, y, cnt;
		public Point(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}
