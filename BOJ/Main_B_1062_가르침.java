/**
 * 14284 kb	
 * 540 ms
 * using DFS
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1062_가르침 {
	
	static int N, K, ans;
	static int[][] alphabet;
	static boolean[] selected;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 단어 수
		K = Integer.parseInt(st.nextToken())-5;	// 배울 수 있는 알파벳 수(a,n,t,i,c은 빼주기)
		alphabet = new int[N][26];	// 26개의 알파벳
		selected = new boolean[26];	// 26개 알파벳 선택 여부
		
		if(K<0) {	// 접두, 접미사 아예 읽을 수 없음 
			System.out.println(0);
			return;
		}
		
		String s; int len, temp;
		for(int i=0; i<N; i++) {
			s = br.readLine();
			len = s.length();
			
			for(int j=4; j<len-4; j++) {
				temp = s.charAt(j)-'a';
				if(temp==0 || temp==2 || temp==8 || temp==13 || temp==19) continue;
				alphabet[i][temp]++;
			}
		}
		
		ans=0;
		dfs(0, 0);	// idx
		System.out.println(ans);
	}
	
	private static void dfs(int pre, int idx) {
		if(idx==K) {
			int cnt = check();
			if(cnt>ans) ans=cnt;
			return;
		}
		
		for(int i=pre; i<26; i++) {	
			if(i==0 || i==2 || i==8 || i==13 || i==19) continue;
			if(!selected[i]) {
				selected[i]=true;
				dfs(i, idx+1);
				selected[i]=false;
			}
		}
	}

	private static int check() {
		int temp, cnt=0;
		for(int i=0; i<N; i++) {
			temp=0;
			
			for(int j=0; j<26; j++) {
				if(selected[j]) continue;
				temp += alphabet[i][j];
			}
			
			if(temp==0) cnt++;
		}
		
		return cnt;
	}

}
