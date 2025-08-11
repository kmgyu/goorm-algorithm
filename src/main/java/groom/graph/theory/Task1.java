package groom.graph.theory;

import java.io.*;
import java.util.*;

public class Task1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N과 M 입력 받기
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        // 배열 초기화
        int[] A = new int[M];
        int[] B = new int[M];
        int[] ind = new int[N];  // 각 정점의 차수를 저장하는 배열

        // 각 간선의 정보와 차수를 입력 받기
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            A[i] = Integer.parseInt(input[0]) - 1;  // 0-based index로 변환
            B[i] = Integer.parseInt(input[1]) - 1;  // 0-based index로 변환
            ind[A[i]]++;  // 정점 A의 차수 증가
            ind[B[i]]++;  // 정점 B의 차수 증가
        }

        // 간선의 양 끝 정점의 차수가 하나라도 1이면 그 간선은 끊지 못한다.
        boolean least_one = false;

        for (int i = 0; i < M; i++) {
            // 두 끝점 중 어느 한쪽이라도 차수가 1인 경우 간선을 끊을 수 없음
            if (ind[A[i]] == 1 || ind[B[i]] == 1) {
                continue;
            }
            // 끊을 수 있는 간선이 존재함을 표시하고, 그 간선의 번호 출력
            least_one = true;
            System.out.print((i + 1) + " ");  // 간선 번호는 1-based로 출력
        }

        // 적어도 하나의 간선을 출력하지 않은 경우 -1 출력
        if (!least_one) {
            System.out.print("-1");
        }

        br.close();
    }
}