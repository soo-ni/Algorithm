/**
 * 15732 kb	
 * 128 ms
 * 조합으로 돌리기~~
 * 
 */


package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_B_3980_선발명단 {

	static int T, ans;
	static ArrayList<Player>[] position;
	static Player[] selected;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());	// test case
		
		for(int tc=0; tc<T; tc++) {
			visited = new boolean[11];		// 11 player selected check
			selected = new Player[11];		// 11 player selected order
			position = new ArrayList[11];	// 11 player position 
			for(int i=0; i<11; i++) {
				position[i] = new ArrayList<Player>();	// init
			}
			
			int temp;
			for(int i=0; i<11; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<11; j++) {
					temp = Integer.parseInt(st.nextToken());
					if(temp>0) {
						position[i].add(new Player(j, temp));	// 선수 번호와 능력치
					}
				}
			}
			
			ans = 0;
			dfs(0);	// idx: position
			
			System.out.println(ans);
		}	// test case end
	}

	private static void dfs(int idx) {
		if(idx==11) {
			int skill = checkSkill();
			if(skill>ans) {
				ans = skill;
			}
			return;
		}
		
		int len = position[idx].size();
		for(int i=0; i<len; i++) {
			Player cur = position[idx].get(i);
			if(!visited[cur.x]) {
				visited[cur.x]=true;
				selected[idx]=cur;
				dfs(idx+1);
				visited[cur.x]=false;
			}
		}
		
	}

	private static int checkSkill() {
		int sum=0;
		for(int i=0; i<11; i++) {
			sum += selected[i].y;
		}
		
		return sum;
	}

	static class Player{
		int x, y;	// 번호, 능력치
		public Player(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
