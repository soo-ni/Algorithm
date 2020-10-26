/**
 * 
 * 13228 kb
 * 100 ms
 * using simul
 * 
 */

package study_Sep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_14719_빗물 {
	
	static int H, W;
	static int[] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[W];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<W; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		int s=0, e=1, answer=0;
		for(int i=1; i<W-1; i++) {
			s = map[i];
			e = map[i];
			
			for(int j=i-1; j>-1; j--) {
				if(map[j]>map[i]) {
					s = Math.max(s, map[j]);
				}
			}
			
			for(int j=i+1; j<W; j++) {
				if(map[j]>map[i]) {
					e = Math.max(e, map[j]);
				}
			}
			
			int min = Math.min(s, e);
			if(min>map[i]) {
				answer += (min-map[i]);
			}
		}
		
		System.out.println(answer);
		
	}

}
