/**
 * 14816 kb, 1064 ms
 * 13720 kb, 936 ms
 * using DFS => true/false해주는거 잊지말기 ㅠ!
 * using bitmasking
 * 굳이 map 갱신 안해줘도됨 (알파벳이 중복되지 않으면 되니까~!)
 * 25m
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1987_알파벳 {
	
	static int R, C, ans;
	static char[][] map;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());	//행
		C = Integer.parseInt(st.nextToken());	//열
		map = new char[R][C];	//맵
		visited = new boolean[26];	//알파벳
		
		String s;
		for(int i=0; i<R; i++) {
			s = br.readLine();
			map[i] = s.toCharArray();
		}
		
		ans=0;
		int mask=1<<(map[0][0]-'A');
//		dfs(0, 0, 0);
		dfs(0, 0, mask, 0);	//using bitmasking
		
		System.out.println(ans+1);
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void dfs(int x, int y, int cnt) {
		ans=Math.max(cnt, ans);
		
		char temp = map[x][y];
		visited[map[x][y]-'A']=true;
		map[x][y]='.';
		
		int nx, ny;
		for(int i=0; i<4; i++) {
			nx = x+dx[i];
			ny = y+dy[i];
			
			if(nx<0||ny<0||nx>R-1||ny>C-1) continue;
			if(map[nx][ny]>='A' && map[nx][ny]<='Z' && !visited[map[nx][ny]-'A']) {
				dfs(nx, ny, cnt+1);
			}
		}
		
		map[x][y]=temp;
		visited[map[x][y]-'A']=false;
	}
	
	//using bitmasking
	private static void dfs(int x, int y, int mask, int cnt) {
		ans=Math.max(cnt, ans);
		
		char temp = map[x][y];
		mask |= 1<<(temp-'A');
//		map[x][y]='.';
		
		int nx, ny;
		for(int i=0; i<4; i++) {
			nx = x+dx[i];
			ny = y+dy[i];
			
			if(nx<0||ny<0||nx>R-1||ny>C-1) continue;
//			if(map[nx][ny]>='A' && map[nx][ny]<='Z' && (mask&1<<(map[nx][ny]-'A'))==0) {
			if((mask&1<<(map[nx][ny]-'A'))==0) {
				dfs(nx, ny, mask|1<<(map[nx][ny]-'A'), cnt+1);
			}
		}
		
//		map[x][y]=temp;
		mask ^= 1<<(temp-'A');
	}
}
