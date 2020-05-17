/**
 * 14992 kb	
 * 104 ms
 * 3h
 * using BFS
 * 와.. 문제이해 제대로 하자 ...ㅠ...
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_1113_수영장만들기 {

	static int N, M, min, max, ans;
	static int[][] map;
	static boolean[][] visited;
	static LinkedList<Point> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// row
		M = Integer.parseInt(st.nextToken());	// col
		map = new int[N+2][M+2];	// map
		visited = new boolean[N+2][M+2];	// visited check
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		
		String s; int temp;
		for(int i=1; i<N+1; i++) {
			s = br.readLine();
			for(int j=0; j<M; j++) {
				temp = s.charAt(j)-'0'-1;
				map[i][j+1] = temp;
				
				min = Math.min(min, temp);	// 수영장 높이의 최대최소값 구하기
				max = Math.max(max, temp);
			}
		}
		
		if(min==max) {
			System.out.println(0);
			return;
		}
		
		ans=0;
		queue = new LinkedList<Point>();
		for(int height=max; height>min; height--) {
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<M+1; j++) {
					if(!visited[i][j] && map[i][j]>=height) {
						queue.add(new Point(i, j));
						visited[i][j]=true;
						bfs(height);
					}
				}
			}
			
			isPool(height);
			initVisited();
		}
		
		System.out.println(ans);
	}

	private static void isPool(int height) {
		queue.add(new Point(0, 0));
		visited[0][0]=true;
		
		Point current; int x, y, nx, ny;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>N+1||ny>M+1) continue;
				if(!visited[nx][ny]) {
					queue.add(new Point(nx, ny));
					visited[nx][ny]=true;
				}
			}
		}
		
		int sum=0;
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<M+1; j++) {
				if(!visited[i][j]) {
					sum+=(height-map[i][j]);
					map[i][j]=height;
				}
			}
		}
		
		ans+=sum;
	}

	private static void initVisited() {
		for(int i=0; i<N+2; i++) {
			for(int j=0; j<M+2; j++) {
				visited[i][j]=false;
			}
		}
	}

	static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
	private static void bfs(int height) {
		Point current; int x, y, nx, ny;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			
			for(int i=0; i<8; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
//				if(nx<0||ny<0||nx>N+1||ny>M+1) continue;
				if(!visited[nx][ny] && map[nx][ny]>=height) {
					queue.add(new Point(nx, ny));
					visited[nx][ny]=true;
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
