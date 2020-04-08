/**
 * 53748 kb	
 * 512 ms
 * using Prim
 * 
 * 인접 리스트 만들 때 양방향으로 넣기!!
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_1922_네트워크연결 {
	
	static int N, M, ans;
	static ArrayList<Point>[] linked;
	static boolean[] visited;
	static LinkedList<Integer> node_queue;
	static PriorityQueue<Point> edge_queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 컴퓨터 수
		M = Integer.parseInt(br.readLine());	// 간선 수
		linked = new ArrayList[N];	// 인접 list
		visited = new boolean[N];	// 방문 체크

		for(int i=0; i<N; i++) {
			linked[i] = new ArrayList<Point>();
		}

		int u, v, w;
		for(int i=0; i<M; i++) {	// init
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken())-1;	// N이 1부터 시작
			v = Integer.parseInt(st.nextToken())-1; // N이 1부터 시작
			w = Integer.parseInt(st.nextToken());
			
			linked[u].add(new Point(u, v, w));	// **양방향 주의**
			linked[v].add(new Point(v, u, w));	// **양방향 주의**
		}

		node_queue = new LinkedList<Integer>();
		edge_queue = new PriorityQueue<Point>();
		
		ans = 0;
		prim();
		
		System.out.println(ans);
		
	}
	
	private static void prim() {
		node_queue.add(0);
		
		int cur_node, len; Point adj, cur_edge;
		while(!node_queue.isEmpty()) {
			cur_node = node_queue.poll();
			visited[cur_node] = true;
			
			len = linked[cur_node].size();
			for(int i=0; i<len; i++) {
				adj = linked[cur_node].get(i);
				edge_queue.add(adj);
			}
			
			while(!edge_queue.isEmpty()) {
				cur_edge = edge_queue.poll();	// 가장 작은 간선 꺼내기
				
				if(!visited[cur_edge.v]) {	// 방문하지 않았으면 넣어주기
					node_queue.add(cur_edge.v);	// 다음 출발 노드 정해주기
					ans+=cur_edge.w;	// 간선크기 더하기
					break;
				}
			}
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
