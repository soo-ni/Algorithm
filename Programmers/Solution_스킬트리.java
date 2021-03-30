class Solution {
    public int solution(String skill, String[] skill_trees) {
        int N = skill.length();
        long[] alpha = new long[26];
        
        char c;
        for(int i=N-1; i>-1; i--) {
            c = skill.charAt(i);
            for(int j=0; j<i; j++) {
                alpha[c-'A'] |= (1 << (skill.charAt(j)-'A'));   // 앞에 나와야하는 알파벳 bitmasking
            }
        }
        
        //show(alpha);
        int answer = 0;
        
        int len;        // skill_trees에 있는 skill의 길이
        boolean flag;   // 스킬셋이 제대로 됐는지 확인
        long temp;      // 스킬셋 넣기위해서
        for(String s: skill_trees) {
            len = s.length();
            flag=true;
            temp=0;
            
            for(int j=0; j<len; j++) {
                c = s.charAt(j);
                temp |= (1<<(c-'A'));   // 해당 값 밀어 넣기
                
                if(alpha[c-'A']>0) {    // 뭔가 필요한애라면
                    if((alpha[c-'A'] & temp) == alpha[c-'A']) {
                        continue;
                    }else {
                        flag=false;
                        break;
                    }
                }
            }
            
            if(flag) answer++;
        }
        
        return answer;
    }
    
    public void show(long[] alpha) {
        System.out.println();
        
        for(long s: alpha)
            System.out.print(s+" ");
    }
}
