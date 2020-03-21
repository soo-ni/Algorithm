/**
 * 13148	
 * 92 ms
 * bfs
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main_B_11559_PuyoPuyo {
	
	static char[][] map;
	static boolean[][] visited, temp_visited;
	static int width, height, ans;
	static boolean token;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		height=12; width=6;
		map = new char[height][width];	// 뿌요
		visited = new boolean[height][width];	// 뿌요 체크
		temp_visited = new boolean[height][width];	// 뿌요 체크
		
		
		char c; String s;
		for(int i=0; i<height; i++) {
			s = br.readLine();
			for(int j=0; j<width; j++) {
				c = s.charAt(j);
				map[i][j] = c;
				if(c=='.') {
					visited[i][j]=true;
				}
			}
		}
		
		ans=0;
		token=true;
		while(token) {
			token=false;
			for(int i=height-1; i>-1; i--) {
				for(int j=0; j<width; j++) {
					if(!visited[i][j]) {
						bfs(i, j, map[i][j]);	// row, col, puyo
					}
				}
			}
			
			if(!token) break;
			update_map();
			ans++;
		}
		System.out.println(ans);
		
	}

	private static void update_map() {
		// 1. map 갱신
		// 2. visited 갱신
		for(int i=height-1; i>0; i--) {
			for(int j=0; j<width; j++) {
				if(map[i][j]=='.') {
					for(int ni=i-1; ni>-1; ni--) {
						if(map[ni][j]!='.') {
							map[i][j] = map[ni][j];
							map[ni][j] = '.';
							visited[i][j] = visited[ni][j];
							visited[ni][j] = true;
							break;
						}
					}
				}
			}
		}
	}

	static LinkedList<Point> queue = new LinkedList<Point>();
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void bfs(int row, int col, char puyo) {
		int cnt=1;	// 터지는 뿌요 개수
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				temp_visited[i][j] = visited[i][j];
			}
		}
		
		queue.clear();
		queue.offer(new Point(row, col));
		temp_visited[row][col]=true;
		
		Point current; int x, y;
		while(!queue.isEmpty()) {
			current = queue.poll();
			
			for(int i=0; i<4; i++) {
				x = current.x+dx[i];
				y = current.y+dy[i];
				
				if(x<0||y<0||x>=height||y>=width) {
					continue;
				}
				
				if(!temp_visited[x][y] && map[x][y]==puyo) {
					queue.add(new Point(x, y));
					temp_visited[x][y] = true;
					cnt++;
				}
			}
		}
		
		if(cnt>3) {
			for(int i=0; i<height; i++) {
				for(int j=0; j<width; j++) {
					visited[i][j] = temp_visited[i][j];
					if(temp_visited[i][j]) {
						map[i][j] = '.';
					}
				}
			}
			token=true;
		}
		
	}
	
	static class Point {
		int x, y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
