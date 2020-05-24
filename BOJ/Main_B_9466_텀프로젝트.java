package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_9466_텀프로젝트 {
	
	static int T, N, ans, cnt;
	static int[] map;
	static boolean[] selected, visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());	//test case
		
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());	//학생 수
			map = new int[N];	//원하는 학생번호
			selected = new boolean[N];	//선택확인
			visited = new boolean[N];	//선택확인
			
			int temp;
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				temp = Integer.parseInt(st.nextToken())-1;	//1번부터 시작
				map[i] = temp;
			}
			
			ans=N;
			cnt=0;
			for(int i=0; i<N; i++) {
				if(!visited[i]) {
					dfs(i);	//처음시작, 다음번호
				}
			}
			
			sb.append(ans-cnt).append('\n');
		}	//test case end
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int current) {
		
		if(selected[current]) {	//이미 방문했던 곳=cycle
			visited[current]=true;
			cnt++;
			System.out.println("current: "+current+", cnt: "+cnt);
		}else {
			selected[current]=true;
		}
		
		int next=map[current];
		if(!visited[next]) {
			dfs(next);
		}
		
		System.out.println("next: "+next);
		selected[current]=false;
		visited[current]=true;
	}
	
	
//	static boolean token;
//	private static void dfs(int start, int next, int cnt) {
////		System.out.println("start: "+start+", next: "+next+", cnt: "+cnt);
//		selected[next]=true;
//		int temp=map[next];
//
//		if(!selected[temp]) {
//			dfs(start, temp, cnt+1);
//		}else if(start==temp) {
//			token=false;
//			ans = ans-cnt;
////			System.out.println("start: "+start+", next: "+next+", cnt: "+cnt+", ans: "+ans);
//			return;
//		}
//		
//		if(token) selected[next]=false;
//	}
}

