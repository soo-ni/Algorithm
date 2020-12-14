/**
 * 
 * 56204 kb
 * 308 ms
 * using DFS+BFS
 * 
 */

package study_Dec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_17244_아맞다우산 {

	static int N, M, sRow, sCol, eRow, eCol, T, answer;
	static char[][] map;
	static int[] selected;
	static boolean[] sVisited;
	static boolean[][] visited;
	static ArrayList<Point> things;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		things = new ArrayList<>();
		
		String s; char c;
		for(int i=0; i<N; i++) {
			s = br.readLine();
			for(int j=0; j<M; j++) {
				c = s.charAt(j);
				map[i][j] = c;
				
				if(c=='S') {
					sRow=i; sCol=j;
				}
				if(c=='E') {
					eRow=i; eCol=j;
				}
				if(c=='X') {
					things.add(new Point(i, j, 0));
				}
			}
		}
		
		answer = Integer.MAX_VALUE;
		T = things.size();
		
		if(T==0) {
			answer = bfs(sRow, sCol, eRow, eCol);
		}else {
			selected = new int[T];
			sVisited = new boolean[T];
			dfs(0);
		}
		
		System.out.println(answer);
	}
	
	private static void dfs(int idx) {
		if(idx==T) {
			go();
//			System.out.println();
			return;
		}
		
		for(int i=0; i<T; i++) {
			if(sVisited[i]) continue;
			sVisited[i]=true;
			selected[idx]=i;
			dfs(idx+1);
			sVisited[i]=false;
		}
	}

	private static void go() {
		int sum=0, idx, pre;
		for(int i=0; i<=T; i++) {
			if(i==T) {
				pre = selected[i-1];
				sum += bfs(things.get(pre).x, things.get(pre).y, eRow, eCol);
				break;
			}
			
			idx = selected[i];
			if(i==0) {
				sum += bfs(sRow, sCol, things.get(idx).x, things.get(idx).y);
			}else {
				pre = selected[i-1];
				sum += bfs(things.get(pre).x, things.get(pre).y, things.get(idx).x, things.get(idx).y);
			}
		}
		
		answer = Math.min(answer, sum);
	}

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static LinkedList<Point> queue;
	private static int bfs(int startX, int startY, int endX, int endY) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visited[i][j]=false;
			}
		}
		
		queue = new LinkedList<>();
		queue.add(new Point(startX, startY, 0));
		visited[startX][startY]=true;
		
		Point current; int x, y, nx, ny, cnt;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			cnt = current.cnt;
			
			if(x==endX && y==endY) {
				return cnt;
			}
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny]=='#') continue;
				
				queue.add(new Point(nx, ny, cnt+1));
				visited[nx][ny]=true;
			}
		}
		
		return 0;
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
