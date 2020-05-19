/**
 * 46840 kb	
 * 416 ms
 * using Dijkstra
 * ..문제 잘읽자
 * 2h
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_1916_최소비용구하기 {
	
	static final int INF =  Integer.MAX_VALUE;
	static int N, M, start, end;
	static ArrayList<Point>[] map;
	static boolean[] visited;
	static int[] dijkstra;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 정점 갯수
		M = Integer.parseInt(br.readLine());	// 가중치 갯수
		map = new ArrayList[N+1];	// adj list
		visited = new boolean[N+1];	// visited check
		dijkstra = new int[N+1];	// 가중치
		Arrays.fill(dijkstra, INF);
		
		for(int i=0; i<N+1; i++) {
			map[i] = new ArrayList<Point>();	// init
		}
		
		int u, v, w;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			map[u].add(new Point(u, v, w));
//			map[v].add(new Point(v, u, w));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		dijkstra[start]=0;
		queue = new PriorityQueue<Point>();
		visited[start]=true;
		for(Point p: map[start]) {
			dijkstra[p.v] = Math.min(dijkstra[p.v], p.w);
			queue.add(p);
		}
		go();
		
		System.out.println(dijkstra[end]);
	}

	static PriorityQueue<Point> queue;
	private static void go() {
		
		Point current; int u, v, w, len, to, toW;
		while(!queue.isEmpty()) {
			current = queue.poll();
			u = current.u;
			v = current.v;
			w = current.w;
			
//			System.out.println("u:"+u+", v:"+v+", w:"+w);
			
			for(Point p: map[v]) {
				to = p.v;
				toW = p.w;
				
				if(dijkstra[v]+toW<dijkstra[to] && !visited[to]) {
					dijkstra[to]=dijkstra[v]+toW;
					queue.add(new Point(v, to, dijkstra[to]));
				}
			}
			
			visited[v]=true;
		}
	}

	static class Point implements Comparable<Point> {
		int u, v, w;
		public Point(int u, int v, int w) {
			super();
			this.u = u;
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Point o) {
			return this.w-o.w;
		}
	}
}
