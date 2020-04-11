/**
 * 96092 kb	
 * 472 ms
 * using KMP
 * 
 * 다시 풀어보기 ㅠ.ㅠ!!
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_B_1786_찾기 {

	static char[] pt, text;
	static int[] pi;
	static int len;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		text = br.readLine().toCharArray();	// text
		pt = br.readLine().toCharArray();	// pattern
		pi = new int[pt.length];	// for pattern idx
		len = pt.length;			// pattern length
		
		getPi();	// get pi
		
		int ans=0;	// 빈도수
		ArrayList<Integer> ans_arr = new ArrayList<Integer>();	// P가 나타나는 위치
		
		int text_len = text.length;
		int j = 0;
		for(int i=0; i<text_len; i++) {
			while(j>0 && text[i]!=pt[j]) {
				j=pi[j-1];
			}
			if(text[i]==pt[j]) {
				if(j==len-1) {
					j = pi[j];
					ans++;					// 빈도수
					ans_arr.add(i-len+2);	// P가 나타나는 위치
				}else {
					j++;
				}
			}
		}
		
		sb.append(ans).append('\n');
		for(int i=0; i<ans_arr.size(); i++) {
			sb.append(ans_arr.get(i)).append(' ');
		}
		System.out.println(sb.toString());
		
	}
	private static void getPi() {
		int j = 0;	
		for(int i=1; i<len; i++) {
			while(j>0 && pt[i]!=pt[j]) {
				j = pi[j-1];
			}
			
			if(pt[i]==pt[j]) {
				pi[i] = ++j;
			}
		}
	}
}
