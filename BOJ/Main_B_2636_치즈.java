/**
 * 15124 kb	
 * 124 ms
 * using bfs
 * 다시 풀어보기
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_2636_치즈 {
	/**
	 * 1. 공기 먼저 bfs
	 * 2. 치즈 bfs (사방에서 visited가 true이면 가장자리임)
	 * cheeze가 0이될때까지 1.2. 반복
	 * 
	 */
	
	static int N, M, cheeze, preCheeze;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// row
		M = Integer.parseInt(st.nextToken());	// col
		map = new int[N+2][M+2];				// map
		visited = new boolean[N+2][M+2];		// air visited check
		cheeze = 0;								// init cheeze
		
		int temp;
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<M+1; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if(temp>0) cheeze++;
			}
		}
		
		queue = new LinkedList<Point>();
		
		int time=0, cnt;
		while(true) {
			cnt = 0;
			L1: for(int i=0; i<N+2; i++) {
				for(int j=0; j<M+2; j++) {
					if(!visited[i][j] && map[i][j]==0) {
						bfs(i, j);	// 공기 bfs
						queue.add(new Point(i, j));
						visited[i][j] = true;
						break L1;
					}
				}
			}
			
			for(int i=0; i<N+2; i++) {
				for(int j=0; j<M+2; j++) {
					if(map[i][j]==1 && check(i, j)) {
						map[i][j]=0;
						cnt++;
					}
				}
			}
		
			for(int i=0; i<N+2; i++)
				Arrays.fill(visited[i], false);
			
			cheeze-=cnt;
			if(cheeze==0) break;
			time++;
			preCheeze = cheeze;
		}
		
		System.out.println(time+" "+preCheeze);
		
	}

	private static boolean check(int row, int col) {
		int nx, ny;
		for(int i=0; i<4; i++) {
			nx = row+dx[i];
			ny = col+dy[i];
			
			if(nx<0||ny<0||nx>N+1||ny>M+1) continue;
			if(visited[nx][ny]) return true; 
		}
		return false;
	}

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static LinkedList<Point> queue;
	private static void bfs(int row, int col) {
		Point current; int nx, ny;
		while(!queue.isEmpty()) {
			current = queue.poll();
			
			for(int i=0; i<4; i++) {
				nx = current.x+dx[i];
				ny = current.y+dy[i];
				
				if(nx<0||ny<0||nx>N+1||ny>M+1) continue;
				
				if(!visited[nx][ny] && map[nx][ny]==0) {
					visited[nx][ny]=true;
					queue.add(new Point(nx, ny));
				}
			}
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
