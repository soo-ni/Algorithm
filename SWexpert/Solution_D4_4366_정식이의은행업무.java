/**
 * 26,380 kb
 * 105 ms
 * ..흠...
 * 
 */

package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Solution_D4_4366_정식이의은행업무 {
	static int T;
	static int[] num2, num3;
	static long ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			String s = br.readLine();
			int len = s.length();
			num2 = new int[len];
			for(int i=0; i<len; i++) {
				num2[i] = s.charAt(i) - '0';
			}
			
			s = br.readLine();
			len = s.length();
			num3 = new int[len];
			for(int i=0; i<len; i++) {
				num3[i] = s.charAt(i) - '0';
			}
			
			set = new HashSet<Long>();
			ans=0;
			getAns();
			
			sb.append("#"+(t+1)+" "+ans+"\n");
		}	// test case end
		
		System.out.println(sb.toString());
	}
	
	static Set<Long> set;
	private static void getAns() {
		int len=num2.length, num; long temp;
		for(int i=0; i<len; i++) {
			temp=0;
			for(int j=0; j<len; j++) {
				num = num2[j];
				if(i==j) {
					num = num<1? 1:0;
				}
				if(num<1) continue;
				temp += (long) Math.pow(2, len-j-1)*num;
			}
			set.add(temp);
		}
		
		len=num3.length; long temp2; boolean token;
		for(int i=0; i<len; i++) {
			temp=0; temp2=0;
			for(int j=0; j<len; j++) {
				token=false;
				num = num3[j];
				if(i==j) {
					switch(num) {
					case 0: 
						token=true;
						temp+=(long) Math.pow(3, len-j-1)*1;
						temp2+=(long) Math.pow(3, len-j-1)*2;
						break;
					case 1:
						token=true;
						temp2+=(long) Math.pow(3, len-j-1)*2;
						break;
					case 2:
						token=true;
						temp2+=(long) Math.pow(3, len-j-1)*1;
						break;
					}
				}
				if(num<1 || token) continue;
				temp += (long) Math.pow(3, len-j-1)*num;
				temp2 += (long) Math.pow(3, len-j-1)*num;
			}
			
			if(!set.add(temp)) {
				ans = temp; return;
			}
			set.remove(temp); 

			if(!set.add(temp2)) {
				ans = temp2; return;
			}
			set.remove(temp2);
		}
		
	}

}
