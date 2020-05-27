/**
 * 88,140 kb
 * 450 ms
 * using BFS 
 * 1h30m
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_D_2117_홈방범서비스 {
	static int T, N, M, max;
	static int[] K, ans;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());	//test case
		K = new int[42];	//운영 비용
		for(int i=1; i<42; i++) {
			K[i] = (i*i) + (i-1)*(i-1);
		}
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	//map 크기
			M = Integer.parseInt(st.nextToken());	//지불 가능 금액
			map = new int[N][N];	//map
			visited = new boolean[N][N];
			ans = new int[41];
			max = 0;	//최대 지불 금액
			
			int temp;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					temp = Integer.parseInt(st.nextToken());
					map[i][j] = temp;
					if(temp>0) max++;
				}
			}
			
			queue = new LinkedList<Point>();
			max = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					queue.add(new Point(i, j, 0));
					visited[i][j]=true;
					if(map[i][j]>0) ans[0]=1;
					bfs(i, j);
					init();
				}
			}
			
			sb.append('#').append(t+1).append(' ').append(max).append('\n');
			
		}	//test case end
		
		System.out.println(sb.toString());
	}
	
	private static void init() {
		int sum=0;
		for(int i=0; i<41; i++) {
			sum+=ans[i];
			if(sum*M<K[i+1])	continue;
			else 			max = Math.max(max, sum);
		}
		
		for(int i=0; i<N; i++) {
			Arrays.fill(visited[i], false);
		}
		Arrays.fill(ans, 0);
	}

	static LinkedList<Point> queue;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void bfs(int row, int col) {
		Point current; int x, y, dis, nx, ny, distance, temp=0, sum;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			dis = current.dis;
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>N-1||ny>N-1) continue;
				if(visited[nx][ny]) continue;

				distance = Math.abs(nx-row)+Math.abs(ny-col);
				if(map[nx][ny]>0) {	//집이 있으면
					ans[distance]++;
				}
				
				queue.add(new Point(nx, ny, distance));
				visited[nx][ny]=true;
			}
		}
	}
	
	static class Point {
		int x, y, dis;
		public Point(int x, int y, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
	}
	
}

