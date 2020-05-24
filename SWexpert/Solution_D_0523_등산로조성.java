package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D_0523_등산로조성 {
	
	static int T, N, K, ans;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Point> top;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	//map size
			K = Integer.parseInt(st.nextToken());	//dig size
			map = new int[N][N];	//map
			visited = new boolean[N][N];	//visited check
			top = new ArrayList<Point>();
			
			int max = -1, temp;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					temp = Integer.parseInt(st.nextToken());
					map[i][j] = temp;
					
					if(temp>max) {
						max=temp;
						top.clear();
						top.add(new Point(i, j));
					}else if(temp==max) {
						top.add(new Point(i, j));
					}
				}
			}
			
			ans=0;
			for(Point p: top) {
				dfs(p.x, p.y, 1, false);
				init();	//visited init
			}
			
			sb.append('#').append(t+1).append(' ').append(ans).append('\n');
			
		}	//test case end
		
		System.out.println(sb.toString());
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void dfs(int x, int y, int cnt, boolean dig) {
		visited[x][y]=true;
		
		int nx, ny;
		for(int i=0; i<4; i++) {
			nx = x+dx[i];
			ny = y+dy[i];
			
			if(nx<0||ny<0||nx>N-1||ny>N-1) continue;
			if(visited[nx][ny]) continue;
			
			if(map[nx][ny]<map[x][y]) {	//작을 때
				dfs(nx, ny, cnt+1, dig);
			}else {
				if(!dig) {	//큰데 아직 땅 안팠을 때
					for(int k=1; k<=K; k++) {
						if(map[nx][ny]-k<map[x][y] && map[nx][ny]-k>-1) {
							map[nx][ny]-=k;
							dfs(nx, ny, cnt+1, true);
							map[nx][ny]+=k;
						}else {
							ans = Math.max(cnt, ans);
						}
					}
				}else {
					ans = Math.max(cnt, ans);
				}
			}
		}
		
		visited[x][y]=false;
	}


	private static void init() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				visited[i][j]= false;
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
