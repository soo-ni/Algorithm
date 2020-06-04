/**
 * 98,396 kb
 * 323 ms
 * using BFS
 * 2h 30m
 * 흠.. 정신차리자ㅠ.ㅠ
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D_5653_줄기세포배양 {
	
	static int T, N, M, K, ans;
	static int[][] map;
	static ArrayList<Point> line;
	static PriorityQueue<Point> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());	//test case
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	//세로
			M = Integer.parseInt(st.nextToken());	//가로
			K = Integer.parseInt(st.nextToken());	//시간
			map = new int[350][350];
			line = new ArrayList<Point>();
			queue = new PriorityQueue<Point>();
			active = new ArrayList<Point>(); 
			
			int temp;
			for(int i=150; i<150+N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=150; j<150+M; j++) {
					temp = Integer.parseInt(st.nextToken());
					map[i][j] = temp;
					if(temp>0) {
						line.add(new Point(i, j, temp, 0));	//x, y, 생명력, 만들어진 시간or활성화된 시간
					}
				}
			}
			
			int time=0, life, create;
			ArrayList<Point> tempLine;
			while(time++<K) {
				bfs(time);

				tempLine = new ArrayList<Point>();
				tempLine.clear();
				for(Point cur: line) {
					life = cur.life;	//생명력
					create = cur.create;	//만들어진 시간
					
					if(time-create==life) {
						queue.add(new Point(cur.x, cur.y, life, time));	//활성화 queue에 넣음
						active.add(new Point(cur.x, cur.y, life, time));
					}else {
						tempLine.add(cur);
					}
				}
				line.clear();
				line.addAll(tempLine);
			}
			
			ans = 0;
			for(Point cur: active) {
				if(K-cur.create<cur.life) ans++;
			}
			
			ans += line.size();
			sb.append('#').append(t+1).append(' ').append(ans).append(' ').append('\n');
		}	//test case end

		System.out.println(sb.toString());
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static ArrayList<Point> active;
	private static void bfs(int time) {
		Point current; int x, y, nx, ny, life;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			life = current.life;
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				if(map[nx][ny]>0) continue;
				
				line.add(new Point(nx, ny, life, time));	//만들어진 시간
				map[nx][ny]=life;
			}
		}
	}

	static class Point implements Comparable<Point> {
		int x, y, life, create;
		public Point(int x, int y, int life, int create) {
			super();
			this.x = x;
			this.y = y;
			this.life = life;
			this.create = create;
		}
		@Override
		public int compareTo(Point o) {
			return o.life-this.life;
		}
	}
}
