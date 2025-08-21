package groom.greedy;

import java.io.*;
import java.util.*;

public class Task2 {

    static final int MAXN = 100000;
    static long[] X = new long[MAXN];
    static long[] Y = new long[MAXN];
    static long[] L = new long[MAXN];
    static long[] R = new long[MAXN];

    // 유클리드 거리의 제곱의 두 배를 계산
    static long dist(long x, long y) {
        return (x * x + y * y) * 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 미사일 정보 입력
        // 해설에서는 10만을 굳이 long에 넣어줬다. 계산할 때 박싱이 문제되서 그런가?
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            X[i] = Long.parseLong(line[0]);
            Y[i] = Long.parseLong(line[1]);
            L[i] = Long.parseLong(line[2]);
        }

        // 각 미사일의 도착 시각 계산
        for (int i = 0; i < N; i++) {
            R[i] = L[i] + dist(X[i], Y[i]);
        }

		/* 미사일의 시작 시각과 도착 시각을 구간으로 나타내고
		   힙을 사용해 가장 많이 겹치는 구간을 찾음 */
        /* 일반 정렬이 아닌, 우선 순위를 명백하게 정의해야함.*/
        // 컴퍼레이터 람다식썼다!
        PriorityQueue<long[]> pq = new PriorityQueue<>(
                (a, b) -> {
                    if (a[0] != b[0]) {
                        return Long.compare(a[0], b[0]); // 시간 순서대로 정렬
                    } else {
                        return Long.compare(a[1], b[1]); // 시간이 같을 경우 도착 시각이 우선
                    }
                }
        );

        // 시작 시간(L[i])과 도착 시간(R[i])을 우선순위 큐에 넣기
        for (int i = 0; i < N; i++) {
            pq.add(new long[]{L[i], 1}); // 시작 시각은 1로 표시
            pq.add(new long[]{R[i], 0}); // 도착 시각은 0으로 표시
        }

        int ct = 0;
        int res = 0;

        // 힙에서 시작 시각과 도착 시각을 처리
        while (!pq.isEmpty()) {
            long[] current = pq.poll();
            long t = current[0];
            int on = (int) current[1];
            if (on == 1) { // 시작 시각
                ++ct;
            } else { // 도착 시각
                --ct;
            }
            res = Math.max(res, ct); // 현재 활성화 중인 미사일 개수 중 최대값
        }

        // 총 시간에서 겹치는 구간의 개수를 빼줌
        long ans = 0;
        for (int i = 0; i < N; i++) {
            ans += R[i] - L[i];
        }
        ans -= res;

        // 결과 출력
        System.out.print(ans);
    }
}