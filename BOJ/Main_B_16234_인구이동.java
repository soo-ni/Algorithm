/**
 * 207804 kb	
 * 580 ms
 * using BFS
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_16234_인구이동 {
	static int N, L, R, ans, cnt;
	static int[][] map;
	static boolean[][] visited;
	static LinkedList<Point> queue;
	static HashMap<Integer, ArrayList<Point>> union;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// row, col
		L = Integer.parseInt(st.nextToken());	// L <=
		R = Integer.parseInt(st.nextToken());	// R >=
		map = new int[N][N];	// map
		visited = new boolean[N][N];	// visit check
		union = new HashMap<Integer, ArrayList<Point>>();
		
		int temp;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
			}
		}
		
		cnt = 0; ans = 0;
		queue = new LinkedList<Point>();
		while(true) {
			cnt = 0;
			// 1. 돌면서 연합
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j]) {
						visited[i][j] = true;
						queue.add(new Point(i, j));
						if(bfs()) cnt++;
					}
				}
			}

			if(cnt<1) break;	
			ans++;
			
			// 2. 돌면서 인구이동
			updateMap();
			
			// 3. 초기화
			for(int i=0; i<N; i++)
				Arrays.fill(visited[i], false);
			union.clear();
		}
		
		System.out.println(ans);
	}
	
	private static void updateMap() {
		Point current;
		int sum, len, people; 
		for(int i=0; i<cnt; i++) {
			sum = 0;
			len = union.get(i).size();
			
			for(int j=0; j<len; j++) {
				current = union.get(i).get(j);
				sum += map[current.x][current.y];
			}
			
			people = sum/len;
			
			for(int j=0; j<len; j++) {
				current = union.get(i).get(j);
				map[current.x][current.y] = people;
			}
		}
	}

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static boolean bfs() {
		boolean token = false;
		
		Point current; int x, y, nx, ny, temp;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>N-1||ny>N-1) continue;
				
				temp = Math.abs(map[x][y]-map[nx][ny]);
				if(!visited[nx][ny] && temp>=L && temp<=R) {	// 차이가 L보다 크고 R보다 작으면
					visited[nx][ny] = true;
					queue.add(new Point(nx, ny));
					
					if(union.get(cnt)==null) {
						union.put(cnt, new ArrayList<Point>());	// 생성되는지 확인해보기
						union.get(cnt).add(new Point(x, y));
					}
					
					union.get(cnt).add(new Point(nx, ny));
					token=true;
				}
			}
		}
		
		return token;
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
