package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_1504_특정한최단경로_다익스트라 {

	static final int INF = Integer.MAX_VALUE; 
	static int N, E, V1, V2;
	static ArrayList<Node>[] map;
	static int[][] distance;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 정점
		E = Integer.parseInt(st.nextToken());	// 간선
		map = new ArrayList[N+1];	// adj list
		for(int i=0; i<N+1; i++) {
			map[i] = new ArrayList<Node>();
		}
		distance = new int[3][N+1];	// distance
		for(int i=0; i<3; i++) Arrays.fill(distance[i], INF);
		
		int u, v, w;
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			map[u].add(new Node(u, v, w));
			map[v].add(new Node(v, u, w));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		V1 = Integer.parseInt(st.nextToken());
		V2 = Integer.parseInt(st.nextToken());
		
		queue = new PriorityQueue<Node>();
		dij(0, 1);
		dij(1, V1);	// V1에서 시작
		dij(2, V2);	// V2에서 시작
		
		if(distance[0][N]==INF) System.out.println(-1);
		else System.out.println(Math.min(distance[0][V1]+distance[1][V2]+distance[2][N], 
				distance[0][V2]+distance[2][V1]+distance[1][N]));
	}
	
	static PriorityQueue<Node> queue;
	private static void dij(int idx, int go) {
		distance[idx][go] = 0;
		queue.add(new Node(1, go, 0));
		
		Node current; int v;
		while(!queue.isEmpty()) {
			current = queue.poll();	// 가중치 가작 작은 node poll
			v = current.v;	// 현재 위치 (u는 이전 위치)
			
			for(Node node: map[v]) {	// 현재 위치와 인접한 노드
				if(distance[idx][node.v]>distance[idx][v]+node.w) {	// 인접한 노드의 현재 거리>현재 노드 거리+현재 노드 가중치
					distance[idx][node.v] = distance[idx][v]+node.w;	// 갱신
					queue.add(node);	// pq에 넣어주기
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
}

