/**
* 26,400 kb
* 124 ms
*
*/

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_3378_스타일리쉬들여쓰기 {
	
	static int T, p, q;
	static int[] RCS;
	static int[][] master, mine; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			p = Integer.parseInt(st.nextToken());	// 마스터 코드
			q = Integer.parseInt(st.nextToken());	// 내 코드
			master = new int[p][7];
			mine = new int[q][7];
			RCS = new int[3];
			
			String s; char c; 
			boolean token;	// 처음 온점확인
			for(int i=0; i<p; i++) {	// master 코드 읽기
				token=true;
				s = br.readLine();
				int len = s.length();
				for(int j=0; j<len; j++) {
					c = s.charAt(j);
					if(token && c=='.') {
						master[i][0]++;
					}else {
						token=false;
					}
					
					switch(c) {
						case '(': master[i][1]++; break;
						case ')': master[i][2]++; break;
						case '{': master[i][3]++; break;
						case '}': master[i][4]++; break;
						case '[': master[i][5]++; break;
						case ']': master[i][6]++; break;
					}
				}
				
				if(i<1) continue;
				for(int j=1; j<7; j++) {
					master[i][j] += master[i-1][j];
				}
			}

			for(int i=0; i<q; i++) {
				token=true;
				s = br.readLine();
				int len = s.length();
				for(int j=0; j<len; j++) {	// 내 코드 읽기
					c = s.charAt(j);					
					switch(c) {
						case '(': mine[i][1]++; break;
						case ')': mine[i][2]++; break;
						case '{': mine[i][3]++; break;
						case '}': mine[i][4]++; break;
						case '[': mine[i][5]++; break;
						case ']': mine[i][6]++; break;
					}
				}
				if(i<1) continue;
				for(int j=1; j<7; j++) {
					mine[i][j] += mine[i-1][j];
				}
			}

			check=false;
			getRCS(0);	// idx

			sb.append('#').append(t+1).append(' ').append(0).append(' ');
			for(int i=1; i<q; i++) {
				sb.append(mine[i][0]).append(' ');
			}
			sb.append('\n'); 
		}	// test case
		
		System.out.println(sb.toString());
	}


	static boolean check;
	private static void getRCS(int idx) {
		if(idx==3) {
			// master코드의 2번째 줄부터 돌면서 확인
			int temp;
			for(int i=1; i<p; i++) {
				temp = RCS[0]*(master[i-1][1]-master[i-1][2])
						+ RCS[1]*(master[i-1][3]-master[i-1][4])
						+ RCS[2]*(master[i-1][5]-master[i-1][6]);
				
				if(temp!=master[i][0]) {
					return;
				}
			}
			
			if(!check) {
				for(int i=1; i<q; i++) {
					mine[i][0] = RCS[0]*(mine[i-1][1]-mine[i-1][2])
							+ RCS[1]*(mine[i-1][3]-mine[i-1][4])
							+ RCS[2]*(mine[i-1][5]-mine[i-1][6]);
				}
			}else {
				for(int i=1; i<q; i++) {
					if(mine[i][0]==-1) continue;
					temp = RCS[0]*(mine[i-1][1]-mine[i-1][2])
							+ RCS[1]*(mine[i-1][3]-mine[i-1][4])
							+ RCS[2]*(mine[i-1][5]-mine[i-1][6]);
					
					if(temp!=mine[i][0]) mine[i][0]=-1;
				}
			}
			
			check=true;
			return;
		}
		
		for(int i=1; i<21; i++) {
			RCS[idx]=i;
			getRCS(idx+1);
		}
	}
}
