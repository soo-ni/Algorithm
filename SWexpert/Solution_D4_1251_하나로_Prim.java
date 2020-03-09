/**
* 141,328 kb
* 759 ms
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

public class Solution_D4_1251_하나로_Prim {
	
	static int T, N;
	static double E, ans;
	static Point[] island;
	static boolean[] visited;
	static ArrayList<Node>[] prim;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());	// 섬 갯수
			island = new Point[N];	// 섬 좌표
			visited = new boolean[N];	// visited 체크
			prim = new ArrayList[N];
			
			for(int i=0; i<N; i++) {
				prim[i] = new ArrayList<Node>();
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			int num;
			for(int i=0; i<N; i++) {
				num = Integer.parseInt(st.nextToken());
				island[i] = new Point(num, 0);
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				num = Integer.parseInt(st.nextToken());
				island[i].y = num;
			}
			
			E = Double.parseDouble(br.readLine());	// 세율

			double distance;
			for(int i=0; i<N; i++) {
				for(int j=i+1; j<N; j++) {
					distance = Math.pow(island[i].x-island[j].x, 2)+Math.pow(island[i].y-island[j].y, 2);
					distance *= E;
					prim[i].add(new Node(i, j, distance));
					prim[j].add(new Node(j, i, distance));
				}
			}
			
			ans = 0;
			node_queue = new LinkedList<Integer>();
			node_queue.add(0);
			visited[0] = true;	// start: 0
			queue = new PriorityQueue<Node>();
			getIsland();
			
			ans = Math.round(ans);	// 반올림
			System.out.println("#"+(t+1)+" "+(long)ans);
		}	// test case
	}
	
	static PriorityQueue<Node> queue;
	static LinkedList<Integer> node_queue;
	private static void getIsland() {
		Node current;
		int current_node;
		while(!node_queue.isEmpty()) {
			current_node = node_queue.poll();
			visited[current_node] = true;
			
			int len = prim[current_node].size();
			for(int i=0; i<len; i++) {
				if(!visited[prim[current_node].get(i).end]) {
					queue.offer(new Node(current_node, 
							prim[current_node].get(i).end, prim[current_node].get(i).edge));
				}
			}
			
			while(!queue.isEmpty()) {
				current = queue.poll();
				
				if(!visited[current.end]) {
					node_queue.add(current.end);	// 현재 노드 갱신
					visited[current.end] = true;	// visited true
					ans += current.edge;	// ans 갱신
					
					break;
				}
			}
		}
	}

	static class Point {
		int x, y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static class Node implements Comparable<Node> {
		int start, end; double edge;
		public Node(int start, int end, double edge) {
			super();
			this.start = start;
			this.end = end;
			this.edge = edge;
		}
		@Override
		public int compareTo(Node o) {
			return this.edge>o.edge? 1:-1;
		}
	}
}
