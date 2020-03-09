/**
* 111,164 kb
* 1,137 ms
*
*/

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D4_1251_하나로_Kruskal {
	
	static int T, N;
	static int[][] island;
	static int[] parent;
	static double E, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			PriorityQueue<Node> queue = new PriorityQueue<Node>();	// edge정보 담을 priority queue
			N = Integer.parseInt(br.readLine());	// 섬 갯수
			island = new int[N][2];	// 섬 좌표
			parent = new int[N];	// root 섬
			
			for(int i=0; i<2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					island[j][i] = Integer.parseInt(st.nextToken());
				}				
			}
			
			E = Double.parseDouble(br.readLine());	// 세율
			
			double distance;
			for(int i=0; i<N; i++) {
				for(int j=i+1; j<N; j++) {
					distance = Math.pow(island[i][0]-island[j][0], 2)+Math.pow(island[i][1]-island[j][1], 2);
					distance *= E;
					queue.offer(new Node(i, j, distance));
					queue.offer(new Node(j, i, distance));
				}
			}
			
			// root 초기화
			Arrays.fill(parent, -1);
			
			ans = 0;
			Node current;
			while(!queue.isEmpty()) {
				current = queue.poll();
				int a_root = findSet(current.start);
				int b_root = findSet(current.end);
				
				if(a_root==b_root) continue;
				
				unionSet(a_root, b_root);
				ans += current.edge;
			}
			
			ans = Math.round(ans);	// 반올림
			System.out.println("#"+(t+1)+" "+(long)ans);
			
		}	// test case
	}
	
	private static void unionSet(int a, int b) {
		int a_root = findSet(a);
		int b_root = findSet(b);
		if(a_root<b_root) parent[b_root] = a_root;
		else parent[a_root] = b_root;
	}

	private static int findSet(int a) {
		if(parent[a]<0) { // 자신이 루트일 경우
			return a;
		}
		return parent[a] = findSet(parent[a]); // path compression
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
