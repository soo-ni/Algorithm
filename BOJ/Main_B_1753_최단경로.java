/**
* 127580 kb
* 760 ms
* using Prim algorithm
*
*/

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_1753_최단경로 {
	
	static int V, E, K;
	static int[] distance;
	static boolean[] visited;
	static ArrayList<Node>[] linked;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		V = Integer.parseInt(st.nextToken());	// 정점 갯수
		E = Integer.parseInt(st.nextToken());	// 간선 갯수
		K = Integer.parseInt(br.readLine());	// 시작 정점 번호

		distance = new int[V+1];	// 최단거리
		visited = new boolean[V+1];	// 방문검사
		linked = new ArrayList[V+1];// 연결노드
		for(int i=0; i<V+1; i++) {
			linked[i] = new ArrayList<Node>();
		}
		
		int u, v, w;
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			linked[u].add(new Node(u, v, w));
		}
		
		Arrays.fill(distance, -1);
		distance[K] = 0;
		queue = new PriorityQueue<Node>();
		vertex = new LinkedList<Integer>();
		getPath();
		
		for(int i=1; i<V+1; i++) {
			if(distance[i]<0) {
				sb.append("INF").append('\n');
				continue;
			}
			sb.append(distance[i]).append('\n');;
		}
		
		System.out.println(sb.toString());
	}
	
	static PriorityQueue<Node> queue;
	static LinkedList<Integer> vertex;
	private static void getPath() {
		vertex.add(K);
		visited[K] = true;
		
		int start; Node current, n; int u, v, w;
		while(!vertex.isEmpty()) {
			start = vertex.poll();	// 현재 뽑힌 정점
			visited[start] = true;	// 정점 방문표시
			
			int len = linked[start].size();
			for(int i=0; i<len; i++) {
				n = linked[start].get(i);
				if(!visited[n.v]) {
					queue.add(new Node(n.u, n.v, n.w+distance[start]));	// 현재 뽑힌 정점과 연결된 모든 간선 저장 - 연결된 것 중 방문한 적 없는 정점만 저장
				}
			}
			
			while(!queue.isEmpty()) {
				current = queue.poll();	// start와 연결된 정점 중 가중치 값이 가장 작은 것 poll
				u = current.u;
				v = current.v;
				w = current.w;
				
				if(!visited[v]) {
					distance[v] = w;
					vertex.add(v);
					break;
				}
			}
		}	// vertex
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
}
