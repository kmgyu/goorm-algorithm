package groom.search.binarysearch;

import java.io.*;
import java.util.*;

public class Task1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); // 출력을 효율적으로 처리하기 위해 사용

        // N 입력 받기
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        // A 배열 입력 받기
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(input[i]);
        }

        // M 입력 받기
        int M = Integer.parseInt(br.readLine());
        int[] B = new int[M];

        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(br.readLine());
        }

        // A 배열을 정렬
        Arrays.sort(A);

        // B 배열의 각 원소에 대해 A에서 이분 탐색 수행
        for (int i = 0; i < M; i++) {
            int index = Arrays.binarySearch(A, B[i]);
            if (index >= 0) {
                sb.append(1).append('\n'); // B[i]가 A에 포함되어 있는 경우
            } else {
                sb.append(0).append('\n'); // B[i]가 A에 포함되어 있지 않은 경우
            }
        }

        // 결과 출력
        System.out.print(sb);
    }
}