/**
* 34,008 kb
* 158 ms
*
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2105_디저트카페 {

	static int T, N, ans;
	static int[][] map;
	static boolean[][] visited_map;
	static boolean[] visited = new boolean[101];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];	// 디저트
			Arrays.fill(visited, true);	// 디저트 번호 체크
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
						
			ans = -1;
			for(int i=0; i<N-2; i++) {
				for(int j=1; j<N-1; j++) {
					getDessert(i, j);			
				}
			}
			
			sb.append('#').append(t+1).append(' ').append(ans).append('\n');
		}	// test case;
		
		System.out.println(sb.toString());
	}
	
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {-1, 1, 1, -1};
	private static void getDesert(int row, int col) {
		
		for(int lLen=1; lLen<=col; lLen++) {
			for(int rLen=1; rLen<N-col; rLen++) {
				boolean token = true;
				Arrays.fill(visited, true);	// 디저트 번호 체크 초기화
				int x=row, y=col, sum=1;
				visited[map[x][y]]=false;
				
				L: for(int dir=0; dir<4; dir++) {
					if(dir%2==0) {	// 왼쪽 변
						for(int i=1; i<=lLen; i++) {
							x += dx[dir];
							y += dy[dir];
							
							if(x<0||y<0||x>=N||y>=N) {
								token=false;
								break L;
							}
							
							if(visited[map[x][y]]) {
								sum++;
								visited[map[x][y]]=false;						
							}else {
								token=false;
								break L;
							}
						}					
					}else {			// 오른쪽 변
						for(int i=1; i<=rLen; i++) {
							x += dx[dir];
							y += dy[dir];
							
							if(x<0||y<0||x>=N||y>=N) {
								token=false;
								break L;
							}
														
							if(visited[map[x][y]]) {
								sum++;
								visited[map[x][y]]=false;						
							}else {
								token=false;
								if(x==row&&y==col) {
									token=true;
								}
								break L;
							}
						}
					}
				}
				
				if(!token) continue;
				
				if(sum>ans) {
					ans=sum;
				}
			}
		}		
	}
}
