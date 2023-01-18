import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023-01-18
 * B1 쉽게 푸는 문제
 * https://www.acmicpc.net/problem/1292
 */
public class Main_1292_쉽게푸는문제 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] arr = new int[1000];

        int N = 1;
        int idx = 0;

        while (idx < 1000) {
            for (int j = 0; j < N; j++) {
                if (idx == 1000) {
                    break;
                }
                arr[idx] = N;
                idx++;
            }
            N++;
        }

        int sum = 0;
        for (int i = A - 1; i < B; i++) {
            sum += arr[i];
        }

        System.out.println(sum);
    }
}


//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class Main_1292_쉽게푸는문제 {
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int A = Integer.parseInt(st.nextToken());
//        int B = Integer.parseInt(st.nextToken());
//
//        /**
//         * 결국에는 위치가 수열의 첫 숫자가 될거고
//         * 그 중에서 몇번째인지
//         * 그다음부터는 곱하기로 가면될듯 ?
//         *
//         * 1        => 1
//         * 22       => 2
//         * 333      => 3
//         * 4444     => 4
//         * 55555    => 5
//         * 666666   => 6
//         * 5 => 3
//         * 10 => 4
//         * 11 => 5
//         * 15 => 5
//         * 16 => 6
//         * 21 => 6
//         *
//         * n(n+1) / 2
//         */
//
//        int start = getStart(A);
//        int end = getStart(B);
//
//        int N = getPosition(A, start);
//        int M = getPosition(B, end);
//        // 위치 구하기
//
//        // 위치 구하기
//
//        int sum = 0;
//        for (int i = start + 1; i < end; i++) {
//            sum += i * i;
//        }
//        sum += start * N;
//        sum += end * M;
//
//        System.out.println(sum);
//    }
//
//    public static int getStart(int n) {
//        int first = 0;
//        for (int i = 1; i < 100; i++) {
//            if (i * (i + 1) >= 2 * n) {
//                first = i;
//                break;
//            }
//        }
//
//        return first;
//    }
//
//    public static int getPosition(int n, int start) {
//        int first = start * (start + 1) / 2;
//        return first - n + 1;
//    }
//}
