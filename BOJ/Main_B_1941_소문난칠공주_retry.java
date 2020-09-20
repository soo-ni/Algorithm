/**
 * 
 * 143328 kb	
 * 484 ms
 * using DFS & BFS
 * 
 */
package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main_B_1941_소문난칠공주 {
	
	static char[][] map;
	static boolean[][] visited;
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[5][5];
		visited = new boolean[5][5];
		
		String s;
		for(int i=0; i<5; i++) {
			s = br.readLine();
			map[i] = s.toCharArray();
		}
		
		answer = 0;
		for(int i=0; i<25; i++) {
			dfs(i, 1, 0);	//pre, cnt, lee
		}
		System.out.println(answer);
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void dfs(int pre, int cnt, int lee) {
		int row = pre/5;
		int col = pre%5;
		
		if(map[row][col]=='S') lee += 1;
		visited[row][col] = true;
		
		if(cnt>6) {
			if(lee>3 && bfs()) {	// 다 연결돼있고 
				answer+=1;
			}
		}else {
			for(int i=pre+1; i<25; i++) {
				if(!visited[i/5][i%5])
					dfs(i, cnt+1, lee);
			}
		}
		
		visited[row][col] = false;
	}
	
	private static boolean bfs() {
		LinkedList<Point> queue = new LinkedList<>();
		boolean[][] check = new boolean[5][5];
		L: for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(visited[i][j]) {
					queue.add(new Point(i, j));
					check[i][j]=true;
					break L;
				}
			}
		}
		
		boolean flag = true;
		int total=1; 
		Point current; int nx, ny;
		while(!queue.isEmpty()) {
			current = queue.poll();
			for(int i=0; i<4; i++) {
				nx = current.x+dx[i];
				ny = current.y+dy[i];
				if(nx<0||ny<0||nx>4||ny>4) continue;
				if(visited[nx][ny] && !check[nx][ny]) {
					total++;
					queue.add(new Point(nx, ny));
					check[nx][ny]=true;
				}
			}
		}
		
		if(total<7) flag=false;
		return flag;
	}
	
	static class Point{
		int x, y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
