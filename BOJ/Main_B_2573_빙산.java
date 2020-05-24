/**
 * 168016 kb, 640 ms
 * 	90112 kb, 544 ms
 * using BFS
 * 40m
 * 녹이는걸 어렵게 생각할 필요가 없었음!
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_2573_빙산 {
	static int N, M, ans, cnt;
	static int[][] map, temp_map;
	static boolean[][] visited;
	static LinkedList<Point> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	//row
		M = Integer.parseInt(st.nextToken());	//col
		map = new int[N][M];	//map
		temp_map = new int[N][M];	//temp map(update next year)
		visited = new boolean[N][M];	//visited check
		
		int temp;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				temp_map[i][j] = temp;
			}
		}
		
		queue = new LinkedList<Point>();
		
		ans=0;
		while(true) {
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<M; j++) {
//					temp = map[i][j];
//					if(temp>0 && !visited[i][j]) {	//빙산이고 아직 방문하지 않았다면
//						queue.add(new Point(i, j));
//						visited[i][j]=true;
//						bfs();	//빙산깎기
//					}
//				}
//			}
			melting();
			init();	//visited init
			cnt=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					temp = map[i][j];
					if(temp>0 && !visited[i][j]) {	//빙산이고 아직 방문하지 않았다면
						queue.add(new Point(i, j));
						visited[i][j]=true;
						bfs_island();	//섬갯수확인하기
					}
				}
				
				if(cnt>1) break;
			}

			init();	//visited init
			
			ans++;	//시간++
			if(cnt>1) break;	//섬이 2개이상
			if(cnt<1) break;	//다녹을때까지 하나라면
		}
		
		if(cnt<1) ans=0;
		System.out.println(ans);
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void bfs_island() {
		Point current; int x, y, nx, ny;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
				if(visited[nx][ny]) continue;
				
				if(map[nx][ny]>0) {
					queue.add(new Point(nx, ny));
					visited[nx][ny]=true;
				}
			}
		}
		cnt++;
	}

	private static void bfs() {
		Point current; int x, y, nx, ny, sum, temp;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			
			sum=0;
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
				
				if(map[nx][ny]<1) {
					sum++;
				}
				
				if(map[nx][ny]>0 && !visited[nx][ny]) {
					queue.add(new Point(nx, ny));
					visited[nx][ny]=true;
				}
			}
			
			temp=map[x][y];
			temp_map[x][y] = temp-sum<0? 0: temp-sum;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j]=temp_map[i][j];
			}
		}
	}
	
	private static void melting() {
		int nx, ny, sum, temp;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				temp=map[i][j];
				if(temp>0) {
					sum=0;
					visited[i][j]=true;
					for(int dir=0; dir<4; dir++) {
						nx = i+dx[dir];
						ny = j+dy[dir];
						
						if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
						
						if(map[nx][ny]<1 && !visited[nx][ny]) {
							sum++;
						}
						
					}
					
					map[i][j] = temp-sum<0? 0: temp-sum;
				}
			}
		}
	}

	private static void init() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visited[i][j] = false;
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
