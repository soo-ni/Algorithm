package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 14364 kb	
 * 108 ms
 * bfs .. ㅎ 숫자 잘보기 ㅎ
 * @author soo-ni
 *
 */
public class Main_B_1726_로봇 {
	
	static int R, C, answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());	// 행
		C = Integer.parseInt(st.nextToken());	// 열
		int[][] map = new int[R][C];	// map
		Point start = new Point();
		Point end = new Point();	// start, end
		
		int temp;
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
			}
		}
		
		int x, y, dir;
		for(int i=0; i<2; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken())-1;
			y = Integer.parseInt(st.nextToken())-1;
			dir = Integer.parseInt(st.nextToken())-1;
			if(i==0) start = new Point(x, y, dir, 0);
			else end = new Point(x, y, dir, 0);
		}
		
		answer = Integer.MAX_VALUE;
		bfs(map, start, end);
		System.out.println(answer);
	}

	static int[] dx = {0, 0, 1, -1};	// 동 서 남 북
	static int[] dy = {1, -1, 0, 0};
	private static void bfs(int[][] map, Point start, Point end) {
		LinkedList<Point> queue = new LinkedList<>();
		
		boolean[][][] visited = new boolean[R][C][4];
		visited[start.x][start.y][start.dir]=true;
		queue.add(start);
		
		Point current; int x, y, nx, ny, dir, cnt;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			dir = current.dir;
			cnt = current.cnt;

			if(x==end.x && y==end.y && dir==end.dir) {
				answer = Math.min(answer, cnt);
			}
			
			// 1. go 명령
			// 2. dir 명령
			int[] check = check(dir);
			for(int i=1; i<4; i++) {
				nx = x+(dx[dir]*i);
				ny = y+(dy[dir]*i);
				
				if(nx<0||ny<0||nx>R-1||ny>C-1) continue;
				if(map[nx][ny]==1) break;
				if(visited[nx][ny][dir]) continue;

				visited[nx][ny][dir]=true;
				queue.add(new Point(nx, ny, dir, cnt+1));
			}
			
			for(int i=0; i<4; i++) {
				if(i==dir) continue;
				if(visited[x][y][i]) continue;
				
				visited[x][y][i]=true;
				queue.add(new Point(x, y, i, cnt+check[i]));
			}
		}
	}
	
	private static int[] check(int dir) {
		int[] result = new int[4];
		
		switch(dir) {
		case 0:
		case 2:
			result[(dir+2)%4]=1; result[(dir+3)%4]=1; result[(dir+1)%4]=2; 
			break;
		case 1:
		case 3:
			result[(dir+1)%4]=1; result[(dir+2)%4]=1; result[(dir-1)%4]=2; 
			break;
		}
		
		return result;
	}

	static class Point {
		int x, y, dir, cnt;
		public Point() {
		}
		public Point(int x, int y, int dir, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}
	}
}
