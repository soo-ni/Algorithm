package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_3967_매직스타 {
	
	static ArrayList<Integer> map;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		map = new ArrayList<Integer>();
		visited = new boolean[13];
		
		String ans="";
		String s; char c;
		for(int i=0; i<5; i++) {
			s = br.readLine();
			ans+=s;
			for(int j=0; j<9; j++) {
				c = s.charAt(j);
				
				if(c=='.') continue;
				if(c=='x') {
					map.add(0);
				}else {
					map.add(c-'A'+1);
					visited[c-'A'+1]=true;
				}
			}
		}
		
		flag=false;
		dfs(0);
		int cnt=0; char cAns;
		for(int i=0; i<ans.length(); i++) {
			if(i!=0&&i%9==0) sb.append('\n');

			c = ans.charAt(i);
			if(c=='.') {
				sb.append('.');
			}else {
				cAns = (char) ('A'+map.get(cnt)-1);
				sb.append(cAns);
				cnt++;
			}
		}
		System.out.println(sb.toString());
	}
	
	static boolean flag;
	private static void dfs(int idx) {
		if(flag) return;
		if(idx==12) {
//			System.out.println(map.toString());
			if(check()) {
				flag=true;
			}
			return;
		}
		
		if(map.get(idx)!=0) {
			dfs(idx+1);
		}
		else {
			for(int j=1; j<13; j++) {
				if(visited[j]) continue;
				
				visited[j]=true;
				map.set(idx, j);
				dfs(idx+1);
				
				if(flag) return;
				map.set(idx, 0);
				visited[j]=false;
			}
		}
	}
	
	private static boolean check() {
		if((map.get(0)+map.get(2)+map.get(5)+map.get(7))!=26) return false;
		if((map.get(1)+map.get(2)+map.get(3)+map.get(4))!=26) return false;
		if((map.get(0)+map.get(3)+map.get(6)+map.get(10))!=26) return false;
		if((map.get(7)+map.get(8)+map.get(9)+map.get(10))!=26) return false;
		if((map.get(1)+map.get(5)+map.get(8)+map.get(11))!=26) return false;
		if((map.get(4)+map.get(6)+map.get(9)+map.get(11))!=26) return false;
		
		return true;
	}
}
