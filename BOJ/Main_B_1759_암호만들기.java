/**
 * 13280 kb	
 * 80 ms
 * using DFS
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_1759_암호만들기 {
	static int L, C;
	static char[] map;
	static int[] selected; 
	static ArrayList<String> ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());	// 몇자리 암호
		C = Integer.parseInt(st.nextToken());	// 주어진 알파벳
		map = new char[C];
		selected = new int[L];
		ans = new ArrayList<String>();
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<C; i++) {
			map[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(map);
		
		dfs(0, 0);
		for(String str: ans) {
			System.out.println(str);
		}
	}
	private static void dfs(int pre, int idx) {
		if(idx==L) {
			int aNum=0, bNum=0; char c; String s = "";
			for(int i=0; i<L; i++) {
				c = map[selected[i]];
				switch(c) {
				case 'a': case 'e': case 'i': case 'o': case 'u': aNum++; break;
				default: bNum++; break;
				}
				s+=c;
			}
			
			if(aNum>0 && bNum>1) {
//				System.out.println(s);
				ans.add(s);
			}
			
			return;
		}
		
		for(int i=pre; i<C; i++) {
			selected[idx] = i;
			dfs(i+1, idx+1);
		}
	}
}
