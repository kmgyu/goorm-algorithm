package groom.search.binarysearch;

import java.io.*;
import java.util.*;
class Task4 {
    public static int N;
    public static int Q;
    public static long[] X;

    public static long search(long target) {
        int left = 0, right = N-1;
        while (left < right) {
            int mid = (left+right)/2;
            if (X[mid] < target) {
                left = mid+1;
            } else {
                right = mid;
            }
        }

        long temp1 = (left > 0) ? Math.abs(target-X[left-1]) : Integer.MAX_VALUE;
        long temp2 = Math.abs(target-X[left]);

        return (temp1 <= temp2 && left > 0) ? X[left-1] : X[left];
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        Q = Integer.parseInt(input[1]);

        X = new long[N];
        input = br.readLine().split(" ");
        for (int i = 0; i<N; i++) {
            X[i] = Long.parseLong(input[i]);
        }

        Arrays.sort(X);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 아 저번에도 그럼 재귀안쓰고 while 루프 쓰면 됬었구나.
        for (int i = 0; i<Q; i++) {
            long target = Long.parseLong(br.readLine());
            long temp = search(target);
            bw.write(String.valueOf(temp) + "\n");
        }
        bw.flush();
    }
}
