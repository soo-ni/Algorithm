/**
 * 18304 kb	
 * 252 ms
 * using Bellman-Ford
 * 1h30m
 * 다시보기!!!!!!!!!!!!!
 * overflow: long 주의********
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

public class Main_B_11657_타임머신 {
	static final int INF = Integer.MAX_VALUE;
	static int N, M;
	static long[] dij;
	static boolean[] visited;
	static boolean token;
	static ArrayList<Point> map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());	//도시의 개수
		M = Integer.parseInt(st.nextToken());	//버스 노선의 정보
		dij = new long[N+1];	//최단거리
		map = new ArrayList<Point>();	//인접리스트
		
		int u, v, w;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			map.add(new Point(u, v, w));
		}
		
		Arrays.fill(dij, INF);
		dij[1]=0;
		
		token=false;
		go();
		
		if(token) {
			System.out.println(-1);
		}else {
			for(int i=2; i<N+1; i++) {
				if(dij[i]==INF) sb.append(-1).append('\n');
				else			sb.append(dij[i]).append('\n');
			}
			
			System.out.println(sb.toString());
		}
		
	}
	
	private static void go() {
		Point current; int from, to, w;
		for(int i=1; i<N; i++) {
			for(int j=0; j<M; j++) {
				current = map.get(j);
				from = current.u;
				to = current.v;
				w = current.w;
				if(dij[from]==INF) continue;
				if(dij[from]+w<dij[to]) {
					dij[to]=dij[from]+w;
				}
			}
		}
		
		for(int j=0; j<M; j++) {
			current = map.get(j);
			from = current.u;
			to = current.v;
			w = current.w;
			if(dij[from]==INF) continue;
			if(dij[from]+w<dij[to]) {
				dij[to]=dij[from]+w;
				token=true; 
			}
		}
		
	}

	static PriorityQueue<Point> queue;
	static class Point {
		int u, v, w;
		public Point(int u, int v, int w) {
			super();
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
}
