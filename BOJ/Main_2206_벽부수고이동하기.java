import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *  2023-02-22
 * 	164012kb
 * 	856ms
 * 	bfs
 * 	
 * 	retry
 */
public class Main_2206_벽부수고이동하기 {

    static int N, M, result;
    static int[][] map;
    static boolean[][][] visited;
    static boolean token;
    static LinkedList<Point> queue;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2];

        char[] charMap;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            charMap = st.nextToken().toCharArray();

            for (int j = 0; j < M; j++) {
                map[i][j] = charMap[j] - '0';
            }
        }

        result = Integer.MAX_VALUE;
        token = false;
        queue = new LinkedList<>();
        bfs();
        if (!token) {
            result = -1;
        }
        System.out.println(result);
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    public static void bfs() {
        queue.add(new Point(0, 0, 1, 1));
        visited[0][0][0] = true;
        visited[0][0][1] = true;

        Point current;
        int x, y, wall, cnt, nx, ny;

        while(!queue.isEmpty()) {
            current = queue.poll();
            x = current.x;
            y = current.y;
            wall = current.wall;
            cnt = current.cnt;

            if (x == N - 1 && y == M - 1) {
                result = Math.min(result, cnt);
                token = true;
            }

            for (int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx > N-1 || ny > M-1) continue;

                if (wall > 0) {
                    if (!visited[nx][ny][0] && map[nx][ny] == 0) {
                        queue.add(new Point(nx, ny, 1, cnt+1));
                        visited[nx][ny][0] = true;
                    }
                    if (!visited[nx][ny][0] && map[nx][ny] == 1) {
                        queue.add(new Point(nx, ny, 0, cnt+1));
                        visited[nx][ny][1] = true;
                    }
                } else {
                    if (!visited[nx][ny][1] && map[nx][ny] == 0) {
                        queue.add(new Point(nx, ny, 0, cnt+1));
                        visited[nx][ny][1] = true;
                    }
                }
            }
        }
    }

    static class Point {
        int x, y, wall, cnt;
        Point(int x, int y, int wall, int cnt) {
            this.x = x; this.y = y; this.wall = wall; this.cnt = cnt;
        }
    }
}
