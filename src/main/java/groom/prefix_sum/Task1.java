package groom.prefix_sum;

import java.io.*;
import java.util.StringTokenizer;

class Task1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N =  Integer.parseInt(br.readLine());
        int[] F = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            F[i] = Integer.parseInt(st.nextToken());
        }


        // prefix sum
        for (int i = 0; i < N-1; i++) {
            F[i+1] += F[i];
        }


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int l =  Integer.parseInt(st.nextToken());
            int r =  Integer.parseInt(st.nextToken());
            if (l > 1) bw.write((F[r-1] - F[l-2])+"");
            else bw.write(F[r-1]+"");
            bw.newLine();
        }
        bw.flush();
    }
}