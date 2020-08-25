package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 38264 kb
 * 232 ms
 * using BFS
 * 
 * @author soo-ni
 *
 */
public class Main_2638_치즈 {

	static int[][] map;
	static int N, M, cheeze;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 행
		M = Integer.parseInt(st.nextToken());	// 열
		map = new int[N][M];	// map
		cheeze=0;	// 지즈 갯수
		
		int temp; 
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				temp = Integer.parseInt(st.nextToken());
				if(temp>0) {
					map[i][j] = -1;
					cheeze++;
				}else {
					map[i][j] = 0;
				}
			}
		}
		
		// 1. 겉부분 1로 만들기
		// 2. -1인 부분의 2개 이상이 1이면 1로 만들기
		// 3. 1인 부분 0으로 변경
		int ans=0;
		while(true) {
			fillMap();
			melting();
			initMap();
			ans++;
			if(cheeze==0) break;
		}
		System.out.println(ans);
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void fillMap() {
		LinkedList<Point> queue = new LinkedList<Point>();
		queue.add(new Point(0, 0));
		map[0][0]=1;
		
		Point current; int x, y, nx, ny;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>=N||ny>=M) continue;
				if(map[nx][ny]==0) {
					queue.add(new Point(nx, ny));
					map[nx][ny]=1;
				}
			}
		}
	}

	private static void melting() {
		int nx, ny;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]<0) {
					int air=0;
					for(int dir=0; dir<4; dir++) {
						nx = i+dx[dir]; 
						ny = j+dy[dir];
						if(map[nx][ny]==1) air++;
					}
					if(air>1) {
						cheeze--;
						map[i][j]=2;
					}
				}
			}
		}
	}
	
	private static void initMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]>0) map[i][j]=0;
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
