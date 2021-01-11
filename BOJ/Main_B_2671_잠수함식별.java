// 유한오토마타

package study_2021_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_2671_잠수함식별 {
	
	static int[][] check = {	// 상태 전이
			{7, 1}, {2, 9}, {3, 9},
			{3, 4}, {7, 5}, {6, 5}, 
			{3, 8}, {9, 8}, {7, 1}, {9, 9}
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		char[] text = s.toCharArray();
		boolean flag = false;
		
		int temp=check[0][text[0]-'0'];
		for(int i=1; i<text.length; i++) {
			temp = check[temp][text[i]-'0'];
			if(temp==9) break;
		}
		
		if(temp==4||temp==5||temp==8) flag=true;
		
		if(flag) System.out.println("SUBMARINE");
		else System.out.println("NOISE");
		
	}

}
