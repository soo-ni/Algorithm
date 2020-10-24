/**
 * 
 * 13576 kb	
 * 112 ms
 * using Two Pointers
 * 
 */

package study_Sep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_B_1484_다이어트 {
	
	static int G;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		ArrayList<Integer> answer = new ArrayList<>();
		
		int s=1, e=1; long sub;
		while(true) {
			sub = (long) Math.pow(e, 2) - (long) Math.pow(s, 2);
			if(G == sub) {
				answer.add(e);
			}
			
			if(G > sub) {
				e++;
			}else {
				if(s < e) s++;
				else e++;
			}
			
			if(e==100001) break;
		}
		
		for(int i=s; i<100000; i++) {
			sub = (long) Math.pow(100000, 2) - (long) Math.pow(i, 2);
			if(G == sub) {
				answer.add(100000);
				break;
			}
		}
		
		if(answer.size()==0) {
			System.out.println(-1);
		}else {
			for(int i: answer) {
				System.out.println(i);
			}
		}
	}

}
