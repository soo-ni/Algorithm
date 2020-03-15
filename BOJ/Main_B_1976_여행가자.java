/**
* 17928 kb
* 144 ms
* BFS
* 
*/

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_B_1976_여행가자 {
	
	static int N, M;
	static int[][] linked;
	static Set<Integer> set;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());	// 도시 수
		M = Integer.parseInt(br.readLine());	// 여행 계획에 속한 도시 수
		linked = new int[N][N];	// 인접행렬
		set = new HashSet<Integer>();	// 중복제거된 가고싶은 도시
		visited = new boolean[N];	// 방문 체크
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				linked[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int temp;	int start=0;
		for(int i=0; i<M; i++) {
			temp = Integer.parseInt(st.nextToken())-1;
			set.add(temp);
			
			if(i>0) continue; 
			start = temp;
		}
		
		visited[start]=true;
		queue.add(start);
		getPath();
		
		if(set.isEmpty()) System.out.println("YES");
		else System.out.println("NO");
	}

	static LinkedList<Integer> queue = new LinkedList<Integer>();
	private static void getPath() {
		int current;
		while(!queue.isEmpty()) {
			current = queue.poll();
			
			for(int i=0; i<N; i++) {
				if(linked[current][i]>0 && !visited[i]) {
					queue.add(i);
					visited[i] = true;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			if(visited[i]) {
				if(set.contains(i)) {
					set.remove(i);
				}
			}
		}
	}

}
