package study;

public class Solution_문자열압축 {
	
	static int N, answer;
	public static void main(String[] args) {
		String s = "abcabcdede";
		int len = s.length();
		int cutLen = s.length()/2;
		
		answer = Integer.MAX_VALUE;
		
		String sub="", pre="", result=""; int start, flag=1;
		for(int i=1; i<=cutLen; i++) {	//i: cut 길이
			sub=""; pre=""; result="";
			for(int j=0; j<=len/i; j++) {
				start = i*j;	// start index
				
				pre = sub;
				if(start+i>len) {
					sub = s.substring(start, len);
				}else {
					sub = s.substring(start, start+i);
				}
				
				if(pre.equals(sub)) {
					flag++;
				}else {
					if(flag>1) result = result + String.valueOf(flag) + sub;
					else result = result + sub;
					flag=1;
				}
			}
//			System.out.println(result);
			answer = Math.min(answer, result.length());
		}
		
		if(len==1) answer=1;
		System.out.println(answer);
	}

}
