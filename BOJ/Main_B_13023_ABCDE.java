package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 14336 kb
 * 204 ms
 * dfs + graph ?
 * @author soo-ni
 *
 */
public class Main_B_13023_ABCDE {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 사람 수
		int M = Integer.parseInt(st.nextToken());	// 친구 관계 수
		
		// 1. 읽어서 양방향으로 인접행렬이나 리스트 만들기
		// 2. 그래프 탐색으로 5개 내려가면 가넝
		ArrayList<Integer>[] map = new ArrayList[N];
		for(int i=0; i<N; i++) {
			map[i] = new ArrayList<>();
		}
		
		int u, v;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());	// u
			v = Integer.parseInt(st.nextToken());	// v
			map[u].add(v);
			map[v].add(u);	//양방향
		}
		
		flag=false;
		boolean[] visited = new boolean[N];
		for(int i=0; i<N; i++) {
			dfs(0, i, map, visited);
			if(flag) break;
		}
		if(flag) System.out.println(1);
		else System.out.println(0);
	}

	static boolean flag;
	private static void dfs(int idx, int next, ArrayList<Integer>[] map, boolean[] visited) {
		if(flag) return;
		if(idx==4) {
			flag=true;
			return;
		}
		
		visited[next]=true;
		for(int i=0; i<map[next].size(); i++) {
			if(visited[map[next].get(i)]) continue;
			dfs(idx+1, map[next].get(i), map, visited);
		}
		visited[next]=false;
	}
}
