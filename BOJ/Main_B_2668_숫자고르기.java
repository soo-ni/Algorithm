/**
 * 13036 kb
 * 72 ms
 * using DFS & Graph
 * 텀프로젝트와 같음 -> cycle찾기
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main_B_2668_숫자고르기 {

	static int N, ans;
	static int[] map;
	static ArrayList<Integer> node;
	static boolean[] visited, done;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	//숫자 갯수
		map = new int[N+1];	//map
		visited = new boolean[N+1];	//확인
		done = new boolean[N+1];	//확인
		
		for(int i=0; i<N; i++) {
			map[i+1] = Integer.parseInt(br.readLine());
		}
		
		node = new ArrayList<Integer>();
		for(int i=1; i<N+1; i++) {
			if(!done[i]) {
				dfs(i);
			}
		}

		System.out.println(ans);
		Collections.sort(node, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
		});
		for(int n: node) System.out.println(n);
	}
	
	private static void dfs(int idx) {

		if(visited[idx]) {
			done[idx]=true;
			node.add(idx);
			ans++;
			visited[idx]=true;
		}else {
			visited[idx]=true;
		}
		
		int next = map[idx];
		if(!done[next]) dfs(next);
		
		visited[idx]=false;
		done[idx]=true;
		
	}
}
