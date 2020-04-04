package study;

import java.util.LinkedList;

public class Solution_네트워크 {
	
	static int n = 3;
//	static int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
	static int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
	static boolean[] visited;
	public static void main(String[] args) {
		int answer = 0;
		visited = new boolean[n];
		queue = new LinkedList<Integer>();
		
		for(int i=0; i<n; i++) {
			if(!visited[i]) {
				queue.add(i);
				visited[i]=true;
				bfs();
				answer++;
			}
		}
		
		System.out.println(answer);
	}
	
	static LinkedList<Integer> queue;
	private static void bfs() {
		int current;
		while(!queue.isEmpty()) {
			current = queue.poll();
			
			for(int i=0; i<n; i++) {
				if(!visited[i] && computers[current][i]>0) {
					queue.add(i);
					visited[i]=true;
				}
			}
		}
	}

}
