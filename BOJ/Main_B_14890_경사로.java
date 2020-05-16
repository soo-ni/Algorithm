/**
 * 14576 kb	
 * 112 ms
 * 1h
 * 그냥 시뮬..?ㅠ.ㅠ
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_14890_경사로 {
	
	static int N, L, ans;
	static int[][] map;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 맵 크기
		L = Integer.parseInt(st.nextToken());	// 접해야하는 면의 수
		map = new int[N][N];	// map
		visited = new boolean[N];	// L check
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		go();
		
		System.out.println(ans);
	}
	
	private static void go() {
		int start, row=0, check=0; boolean token=true;
		while(row<N) {
			token=true;
			check=0;
			start = map[row][0];	// 시작
			Arrays.fill(visited, false);
			
			for(int j=1; j<N; j++) {
				if(Math.abs(start-map[row][j])>1) { // 경사 성립 X
					token=false;
					break;
				}
				
				if(start==map[row][j]) {
					if(check>0) token=false;
					continue;	// 경사 필요 X
				}
				
				if(start-map[row][j]==1) {	// 내려갈 때
					check++;	// 겹치는 경사면
					if(check==L) {
						check=0;
						start=map[row][j];
						for(int i=0; i<L; i++) {
							visited[j-i]=true;
						}
					}
				}
				
				if(start-map[row][j]==-1) {	// 올라갈 때
					for(int i=0; i<L+1; i++) {	// 이미 경사로 있으면 X
						if(j-i<0 || visited[j-i]) {
							token=false;
							continue;
						}
					}
					
					if(!token) continue;
					check=0;
					for(int i=1; i<L+1; i++) {	
						visited[j-i] = true;
					}
					start=map[row][j];
				}
			}
			
			if(token && check<1) {
//				System.out.println("row: "+row);
				ans++;
			}
			row++;
		}
		
		int col=0;
		while(col<N) {
			token=true;
			check=0;
			start = map[0][col];	// 시작
			Arrays.fill(visited, false);
			
			for(int j=1; j<N; j++) {
				if(Math.abs(start-map[j][col])>1) { // 경사 성립 X
					token=false;
					break;
				}
				
				if(start==map[j][col]) {
					if(check>0) token=false;
					continue;	// 경사 필요 X
				}
				
				if(start-map[j][col]==1) {	// 내려갈 때
					check++;	// 겹치는 경사면
					if(check==L) {
						check=0;
						start=map[j][col];
						for(int i=0; i<L; i++) {
							visited[j-i]=true;
						}
					}
				}
				
				if(start-map[j][col]==-1) {	// 올라갈 때
					for(int i=0; i<L+1; i++) {	// 이미 경사로 있으면 X
						if(j-i<0 || visited[j-i]) {
							token=false;
							continue;
						}
					}
					
					if(!token) continue;
					check=0;
					for(int i=1; i<L+1; i++) {	
						visited[j-i] = true;
					}
					start=map[j][col];
				}
			}
			
			if(token && check<1) {
//				System.out.println("col: "+col);
				ans++;
			}
			col++;
		}
	}
}
