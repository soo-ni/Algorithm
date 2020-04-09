/**
 * 50464 kb	
 * 628 ms -> 608ms
 * using Kruskal
 * using Integer.compare(,); 쓰면 오버플로우 안남!
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_1197_최소스패닝트리 {
	
	static int V, E;
	static int[] parents;
	static Point[] edges;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());	// 정점
		E = Integer.parseInt(st.nextToken());	// 간선
		edges = new Point[E];	// edges
		parents = new int[V];	// set
		Arrays.fill(parents, -1);
		
		int u, v, w;
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken())-1;	// 1부터 시작
			v = Integer.parseInt(st.nextToken())-1;	// 1부터 시작
			w = Integer.parseInt(st.nextToken());
			
			edges[i] = new Point(u, v, w);
		}
		
		Arrays.sort(edges);
		
		Point current; long sum=0;
		for(int i=0; i<E; i++) {
			current = edges[i];
			if(UnionFind(current.u, current.v)) sum+=current.w;
		}
		
		System.out.println(sum);
	}
	
	private static boolean UnionFind(int a, int b) {
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
			// 1. 음수 음수
			// 2. 음수 양수
			// 3. 양수 양수
			// 4. 양수 음수
//			int result=0;
//			if(this.w<0 && o.w<0) result=(Math.abs(this.w)-Math.abs(o.w))*-1;
//			if(this.w<0 && o.w>0) result=-1;
//			if(this.w>0 && o.w>0) result=this.w-o.w;
//			if(this.w>0 && o.w<0) result=1;
//			if(this.w==0) result=-1*o.w;
//			if(o.w==0) result=this.w;
//			return result;
			return Integer.compare(this.w, o.w);
		}
	}
}
