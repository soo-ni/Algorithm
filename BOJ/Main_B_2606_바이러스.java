/**
 * 13140 kb	
 * 76 ms
 * using bfs
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_2606_바이러스 {
	
	static int N, M;
	static int[][] map;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());	// 컴퓨터 수
		M = Integer.parseInt(br.readLine());	// 간선 수
		map = new int[N+1][N+1];	// 인접 행렬
		visited = new boolean[N+1];	// 컴퓨터 방문 체크
		
		int x, y;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			map[x][y] = 1;
			map[y][x] = 1;
		}
		
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		visited[1] = true;
		int current, ans=0;
		while(!queue.isEmpty()) {
			current = queue.poll();
			
			for(int i=1; i<N+1; i++) {
				if(map[current][i]>0 && !visited[i]) {
					queue.add(i);
					visited[i]=true;
					ans++;
				}
			}
		}
		
		System.out.println(ans);
	}

}
