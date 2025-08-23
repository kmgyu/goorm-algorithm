package groom.dp;

import java.io.*;

public class Task1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N =  Integer.parseInt(br.readLine());
        long[] arr = {1L, 1L, 2L};
        if (N <= 3) {
            System.out.println(arr[N-1]);
        }
        else {
            for(int i = 4; i < N+1; i++) {
                arr[2] = (arr[0] + arr[1]) % 1_000_000_007L;
                arr[0] = arr[1];
                arr[1] = arr[2];
            }
            System.out.println(arr[2]);
        }
    }
}
