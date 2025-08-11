package groom.search.binarysearch;

import java.io.*;
import java.util.*;

class Task2 {
    public static int N;
    public static long[] S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        S = new long[N];

        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            S[i] = Long.parseLong(temp[i]);
        }

        Arrays.sort(S);

        long cnt = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                long key = S[i]+ S[j];
                int idx = Arrays.binarySearch(S, j+1, N, key);

                // 인덱싱 너무 더럽
                if (idx < 0) {idx = -idx-1;}
                else {idx++;}
                if (idx > j+1 ) {cnt += idx - j - 1;}
            }
        }

        System.out.println(cnt);
    }
}
