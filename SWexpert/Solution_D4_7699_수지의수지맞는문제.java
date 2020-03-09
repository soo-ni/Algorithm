/**
* 26,556 kb
* 135 ms
*
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
 
public class Solution_D4_7699_수지의수지맞는문제 {
     
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;
    static boolean[] visited;
    static boolean[][] visitedMap;
    static int H, W, ans;
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int T = Integer.parseInt(st.nextToken());
         
        for(int t=0; t<T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];   // 명물 
            visitedMap = new boolean[H][W]; // 명물 방문 확인
            visited = new boolean[26]; // 명물확인
             
            for(int i=0; i<H; i++)
                Arrays.fill(visitedMap[i], true);
            Arrays.fill(visited, true);
             
            String s;
            for(int i=0; i<H; i++) {
                s = br.readLine();
                map[i] = s.toCharArray();
            }
             
            ans=0;
            getPath(0, 0, 1);
            System.out.println("#"+(t+1)+" "+ans);
        }
    }
     
    private static void getPath(int row, int col, int score) {
                 
        if(score>ans) {
            ans = score;
        }
         
        if(ans==26) return;
         
        visited[map[row][col]-'A'] = false;
        visitedMap[row][col] = false;
         
        int x, y;
        for(int i=0; i<4; i++) {
            x = row+dx[i];
            y = col+dy[i];
             
            if(x<0 || y<0 || x>=H || y>=W) {
                continue;
            }
             
            if(visited[map[x][y]-'A'] && visitedMap[x][y]) {
                getPath(x, y, score+1);
            }
        }
         
        visited[map[row][col]-'A'] = true;
        visitedMap[row][col] = true;
         
    }
 
 
}
