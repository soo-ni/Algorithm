/**
 * 278424 kb
 * 812 ms
 * using dfs, bfs
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_14502_연구소 {
	
	static int N, M, ans, totalZero;
	static int[][] map;
	static boolean[][] visited;
	static Point[] wall;
	static ArrayList<Point> virus;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 세로
		M = Integer.parseInt(st.nextToken());	// 가로
		map = new int[N][M];			// map
		visited = new boolean[N][M];	// visited check
		virus = new ArrayList<Point>();	// 바이러스 좌표
		totalZero = 0;					// 안전 영역 개수
		
		int temp;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				
				if(temp<1) totalZero++;
				if(temp>1) virus.add(new Point(i, j));
			}
		}
		
		ans = 0;
		dfs(0, 0, 0);	// preX, preY, idx
		System.out.println(ans);
	}
	
	private static void dfs(int preX, int preY, int idx) {
		if(idx==3) {
			int num = bfs();
			if(num>ans) {
				ans=num;
			}
			return;
		}
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<M; j++) {
//				if(map[i][j]<1) {
//					map[i][j]=1;
//					dfs(i, j, idx+1);
//					map[i][j]=0;
//				}
//			}
//		}
		
		int x, y;
		for(int i=0; i<N*M; i++) {
			x=i/M; y=i%M;
			if(map[x][y]<1) {
				map[x][y]=1;
				dfs(x, y, idx+1);
				map[x][y]=0;
			}
		}
		
	}

	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	static LinkedList<Point> queue;
	private static int bfs() {
		queue = new LinkedList<Point>();
		for(int i=0; i<N; i++) {
			Arrays.fill(visited[i], false);	// visited init
		}
		int tempZero = totalZero-3;

		int len = virus.size();
		Point current;
		for(int i=0; i<len; i++) {
			current = virus.get(i);
			queue.add(current);
			visited[current.x][current.y]=true;
		}
		
		while(!queue.isEmpty()) {
			current = queue.poll();
			
			int x, y;
			for(int i=0; i<4; i++) {
				x = current.x+dx[i];
				y = current.y+dy[i];
				
				if(x<0||y<0||x>N-1||y>M-1) {
					continue;
				}
				
				if(!visited[x][y] && map[x][y]!=1) {
					queue.add(new Point(x, y));
					visited[x][y] = true;
					if(map[x][y]<1) tempZero--;
				}
			}
		}

		return tempZero;
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
