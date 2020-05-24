/**
 * 97,148 kb
 * 548 ms
 * 그냥 깡시뮬
 * 57m
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_D_2382_미생물격리 {

	static int N, M, K, T;
	static ArrayList<Point>[][] map;
	static LinkedList<Point> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());	//test case
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	//map크기
			M = Integer.parseInt(st.nextToken());	//시간
			K = Integer.parseInt(st.nextToken());	//군집수
			queue = new LinkedList<Point>();	//for bfs
			map = new ArrayList[N][N];		//map
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = new ArrayList<Point>();
				}
			}
			
			int x, y, cnt, dir;
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				x = Integer.parseInt(st.nextToken());	//행
				y = Integer.parseInt(st.nextToken());	//열
				cnt = Integer.parseInt(st.nextToken());	//미생물수
				dir = Integer.parseInt(st.nextToken());	//방향
				
				queue.add(new Point(x, y, cnt, dir));
			}
			
			for(int i=0; i<M; i++) {	//M시간만큼 수행
				go();
				union();
			}
			
			int ans=0;
			for(Point p: queue) {
				ans += p.cnt;
			}
			
			sb.append('#').append(t+1).append(' ').append(ans).append('\n');
		}	//test case end
		
		System.out.println(sb.toString());
	}
	
	private static void union() {
		int len, max=0, dir=0, sum;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				len = map[i][j].size();
				if(len<1) continue;
				
				if(len==1) {	//하나만 있다면
					queue.add(map[i][j].get(0));
					map[i][j].clear();
				}else {	//여러 미생물이 뭉쳐있다면
					sum=0; max=0;
					for(Point p: map[i][j]) {
						if(p.cnt>max) {	//가장 많은 미생물의 방향 알기위해서
							max=p.cnt;
							dir=p.dir;
						}
						sum+=p.cnt;
					}
					queue.add(new Point(i, j, sum, dir));
					map[i][j].clear();
				}
			}
		}
		
	}

	static int[] dx = {0, -1, 1, 0, 0}; //상하좌우(1234)
	static int[] dy = {0, 0, 0, -1, 1};
	private static void go() {
		int size = queue.size();	//미생물 군집의 수
		Point current; int x, y, cnt, dir, nx, ny;
		while(size-->0) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			cnt = current.cnt;
			dir = current.dir;

			nx = x+dx[dir];
			ny = y+dy[dir];
			
			if(nx<1||ny<1||nx>N-2||ny>N-2) {	//약품처리된 곳으로 간다면
				switch(dir) {
				case 1: dir=2; break;
				case 2: dir=1; break;
				case 3: dir=4; break;
				case 4: dir=3; break;
				}
				
				cnt/=2;
			}
			
			if(cnt<1) continue;
			map[nx][ny].add(new Point(nx, ny, cnt, dir));
		}
	}

	static class Point {
		int x, y, cnt, dir;
		public Point(int x, int y, int cnt, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}
	}
}
