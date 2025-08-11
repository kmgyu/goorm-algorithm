package groom.recursive;

import java.io.*;
import java.util.*;

public class Task1 {
    // static은 값 할당전에 선언해도 괜찮음.
    static final int MAXN = 5;
    static final long INF = Long.MAX_VALUE; // 파이썬에 float('inf')가 있으면 자바에 대응되는 건 이거다.

    static int N;
    static long K, ans;
    static int[] A = new int[MAXN];

    // A의 원소로 이루어져 있으며 K보다 큰, 최솟값 찾기
    static void solve(long now) {
        if (now > K) { // 현재 숫자가 K보다 크다면 더이상 진행할 이유가 없다. 정답을 갱신하고 중단
            ans = Math.min(ans, now);
            return;
        }
        for (int i = 0; i < N; i++) {
            long nxt = now * 10 + A[i]; // 다음 숫자를 구한다.
            if (nxt == 0) continue; // 다음 숫자가 0이 되는 경우 처리
            solve(nxt);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N 입력 받기
        N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(input[i]);
        }

        // K 입력 받기
        K = Long.parseLong(br.readLine());

        ans = INF; // 초기 정답 값은 무한대로 설정
        solve(0); // 재귀 함수 호출

        // 결과 출력
        System.out.println(ans);
    }
}