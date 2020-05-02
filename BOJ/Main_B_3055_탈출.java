/**
 * 13216 kb	
 * 76 ms
 * using BFS
 * *체크 안해줘서 물 오지게 돌아다닐뻔 ..ㅎㅎ
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_3055_탈출 {

	static int R, C, ans;
	static char[][] map;
	static boolean[][] visited;
	static LinkedList<Point> queue_w, queue_a;
	static boolean token;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());	// row
		C = Integer.parseInt(st.nextToken());	// col
		map = new char[R][C];	// map
		visited = new boolean[R][C];	// visited check
		queue_w = new LinkedList<Point>();	// water
		queue_a = new LinkedList<Point>();	// animal
		
		String s; char c;
		for(int i=0; i<R; i++) {
			s = br.readLine();
			for(int j=0; j<C; j++) {
				c = s.charAt(j);
				map[i][j] = c;
				
				if(c=='*') queue_w.add(new Point(i, j, 0));
				if(c=='S') {
					queue_a.add(new Point(i, j, 0));
					visited[i][j]=true;
				}
			}
		}
		
		ans = 1001;
		token = false;
		while(true) {
			// 1. 물 이동
			bfs_w();
			
			// 2. 고슴도치 이동
			bfs_a();
			
			if(queue_a.isEmpty()) break;
		}
		
		if(ans>1000) System.out.println("KAKTUS"); 
		else System.out.println(ans);
		
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void bfs_a() {
		int size = queue_a.size();
		
		Point current; int x, y, nx, ny, cnt;
		while(size-->0) {
			current = queue_a.poll();
			x = current.x;
			y = current.y;
			cnt = current.cnt;
			
			if(map[x][y]=='D') {
				if(ans>cnt) ans=cnt;
			}
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				if(nx<0||ny<0||nx>R-1||ny>C-1) continue;
				if(!visited[nx][ny] && map[nx][ny]!='*' && map[nx][ny]!='X') {
					queue_a.add(new Point(nx, ny, cnt+1));
					visited[nx][ny]=true;
				}
			}
		}
	}

	private static void bfs_w() {
		int size = queue_w.size();
		
		Point current; int x, y, nx, ny;
		while(size-->0) {
			current = queue_w.poll();
			x = current.x;
			y = current.y;
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				if(nx<0||ny<0||nx>R-1||ny>C-1) continue;
				if(map[nx][ny]=='.' || map[nx][ny]=='S') {
					queue_w.add(new Point(nx, ny, 0));
					map[nx][ny]='*';
				}
			}
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
