/**
 * 13180 kb	
 * 76 ms
 * 문제 제대로 읽기 ㅎㅎ..
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_17472_다리만들기2 {

	static int N, M, cnt, ans;
	static int[][] map;
	static int[] parents;
	static LinkedList<Node> bridge;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// row
		M = Integer.parseInt(st.nextToken());	// col
		map = new int[N][M];
		
		int temp;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
			}
		}
		
		cnt=0;	// 섬 갯수
		queue = new LinkedList<Point>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==1) {
					queue.add(new Point(i, j));
					map[i][j] = cnt+2;
					bfs();
					cnt++;
				}
			}
		}
		
		parents = new int[cnt];
		Arrays.fill(parents, -1);
		bridge = new LinkedList<Node>();
		int u;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				u = map[i][j];
				if(u>0) {
					connectLeft(i, j, u);	// 왼
					connectRight(i, j, u);	// 오
					connectDown(i, j, u);	// 밑
				}
			}
		}
		
		Collections.sort(bridge);
		Node current;
		while(!bridge.isEmpty()) {
			current = bridge.poll();
			if(UnionSet(current.u, current.v)) {
				ans+=current.w;
			}
		}
		
		temp=0;
		for(int i=0; i<cnt; i++) {
			if(parents[i]<0) temp++;
		}
		if(temp>1) System.out.println(-1);
		else System.out.println(ans);
		
	}
	
	private static boolean UnionSet(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot!=bRoot) {
			parents[bRoot]=aRoot;
			return true;
		}
		
		return false;
	}
	
	private static int findSet(int a) {
		if(parents[a]<0) return a;
		return parents[a] = findSet(parents[a]);
	}

	private static void connectDown(int row, int col, int u) {
		int nx=row+1, ny=col, v=u;
		int temp=0;

		while(true) {	// down
			if(nx<0||ny<0||nx>N-1||ny>M-1) break;
			if(map[nx][ny]==0) {
				temp++;
				nx++;
			}else if(map[nx][ny]!=u) {
				v=map[nx][ny];
				break;
			}else {
				break;
			}
		}
		
		if(temp>1) {
			bridge.add(new Node(u-2, v-2, temp));
		}
	}

	private static void connectRight(int row, int col, int u) {
		int nx=row, ny=col+1, v=u;
		int temp=0;

		while(true) {	// right
			if(nx<0||ny<0||nx>N-1||ny>M-1) break;
			if(map[nx][ny]==0) {
				temp++;
				ny++;
			}else if(map[nx][ny]!=u) {
				v=map[nx][ny];
				break;
			}else {
				break;
			}
		}
		
		if(temp>1) {
			bridge.add(new Node(u-2, v-2, temp));
		}
	}

	private static void connectLeft(int row, int col, int u) {
		
		int nx=row, ny=col-1, v=u;
		int temp=0;

		while(true) {	// left
			if(nx<0||ny<0||nx>N-1||ny>M-1) break;
			if(map[nx][ny]==0) {
				temp++;
				ny--;
			}else if(map[nx][ny]!=u) {
				v=map[nx][ny];
				break;
			}else {
				break;
			}
		}
		
		if(temp>1) {
			bridge.add(new Node(u-2, v-2, temp));
		}
	}

	static LinkedList<Point> queue;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void bfs() {
		Point current; int nx, ny;
		while(!queue.isEmpty()) {
			current = queue.poll();
			
			for(int i=0; i<4; i++) {
				nx = current.x+dx[i];
				ny = current.y+dy[i];
				
				if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
				
				if(map[nx][ny]==1) {
					queue.add(new Point(nx, ny));
					map[nx][ny] = cnt+2;
				}
			}
		}
	}

	static class Node implements Comparable<Node> {
		int u, v, w;
		public Node(int u, int v, int w) {
			super();
			this.u = u;
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return this.w-o.w;
		}
	}
	
	static class Point implements Comparable<Point> {
		int x, y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			int result = this.x-o.x;
			if(result==0) result=this.y-o.y;
			return result;
		}
	}
}

