/**
 * 13340 kb	
 * 84 ms
 * bfs
 *  
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_14503_로봇청소기 {
	
	static int N, M, ans;
	static int[][] map;
	static boolean[][] visited;
	
	static LinkedList<Point> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 행
		M = Integer.parseInt(st.nextToken());	// 열
		map = new int[N][M];	// 영역
		visited = new boolean[N][M];	// 방문 체크
		
		st = new StringTokenizer(br.readLine(), " ");
		int row = Integer.parseInt(st.nextToken());	// 시작row
		int col = Integer.parseInt(st.nextToken());	// 시작col
		int dir = Integer.parseInt(st.nextToken());	// 시작dir
		
		int temp;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if(temp>0) visited[i][j] = true;				
			}
		}
		
		queue = new LinkedList<Point>();
		queue.add(new Point(row, col, dir));
		visited[row][col]=true;
		ans=1;
		bfs();
		
		System.out.println(ans);
		
	}
	
	static int[] dx = {-1, 0, 1, 0};	// 상 우 하 좌
	static int[] dy = {0, 1, 0, -1};
	private static void bfs() {
		
		Point current;
		while(!queue.isEmpty()) {
			current = queue.poll();
			
			int x, y, dir;
			for(int i=3; i>=0; i--) {
				dir = (current.d+i)%4;
				x = current.x+dx[dir];
				y = current.y+dy[dir];
				
				if(x<0||y<0||x>=N||y>=M) {
					continue;
				}
				
				if(map[x][y]<1 && !visited[x][y]) {	// 왼쪽이 방문도 안하고 비어있다면
					ans++;
					queue.add(new Point(x, y, dir));
					visited[x][y]=true;
					break;
				}
			}

			if(!queue.isEmpty()) continue;	// 옮겨갔을 경우는 밑에 처리 X
			
			dir = (current.d+2)%4;	// 현재 방향의 반대(후진) 위해서
			x = current.x+dx[dir];
			y = current.y+dy[dir];
			
			if(x<0||y<0||x>=N||y>=M||map[x][y]>0) {
				break;
			}
			
			queue.add(new Point(x, y, current.d));	// 방향 유지한채로 후진
		}
	}
	
	static class Point {
		int x, y, d;
		public Point(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}
