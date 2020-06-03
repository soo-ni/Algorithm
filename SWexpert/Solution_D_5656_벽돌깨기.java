/**
 * 62,032 kb
 * 288 ms
 * using DFS & BFS
 * 50m
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_D_5656_벽돌깨기 {
	
	static int T, N, H, W, ans;
	static int[] selected;
	static int[][] map, tempMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());	//test case
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	//구슬 갯수
			W = Integer.parseInt(st.nextToken());	//가로
			H = Integer.parseInt(st.nextToken());	//세로
			map = new int[H][W];	//map
			tempMap = new int[H][W];	//temp map
			queue = new LinkedList<Point>();	//bfs
			selected = new int[N];	//selected
			
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = Integer.MAX_VALUE;
			dfs(0);	//idx
			
			sb.append('#').append(t+1).append(' ').append(ans).append('\n');
		}	//test case end
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int idx) {
		if(ans==0) return;
		if(idx==N) {
//			for(int i: selected) System.out.print(i+" ");
//			System.out.println();
			
			initMap();
			for(int i:selected) {
				bfs(i);
				updateMap();
			}
			int sum = check();
			ans=Math.min(ans, sum);
			
			return;
		}
		
		for(int i=0; i<W; i++) {
			selected[idx] = i;
			dfs(idx+1);
		}
	}

	private static int check() {
		int sum=0;
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(tempMap[i][j]>0) sum++;
			}
		}
		return sum;
	}

	private static void updateMap() {
		for(int j=0; j<W; j++) {
			for(int i=H-1; i>-1; i--) {
				if(tempMap[i][j]==0) {
					for(int k=i; k>-1; k--) {
						if(tempMap[k][j]>0) {
							tempMap[i][j] = tempMap[k][j];
							tempMap[k][j] = 0;
							break;
						}
					}
				}
			}
		}
	}

	private static void initMap() {
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				tempMap[i][j] = map[i][j];
			}
		}
	}

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static LinkedList<Point> queue;
	private static void bfs(int idx) {
		for(int i=0; i<H; i++) {
			if(tempMap[i][idx]>0) {
				queue.add(new Point(i, idx, tempMap[i][idx]));
				tempMap[i][idx] = 0;
				break;
			}
		}
		
		Point current; int x, y, cnt, nx, ny;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			cnt = current.cnt;
			
			if(cnt<2) continue;
			
			for(int i=0; i<4; i++) {
				for(int k=1; k<cnt; k++) {
					nx = x+dx[i]*k;
					ny = y+dy[i]*k;
					
					if(nx<0||ny<0||nx>H-1||ny>W-1) continue;
					if(tempMap[nx][ny]>0) {
						queue.add(new Point(nx, ny, tempMap[nx][ny]));
						tempMap[nx][ny]=0;
					}
				}
			}
		}
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
