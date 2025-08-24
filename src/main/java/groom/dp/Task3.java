package groom.dp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Task3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[][] mat = new long[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(mat[i], 0);
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;

            mat[r][c] = -1;
        }

        mat[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (mat[i][j] == -1) continue;
                for (int k = i-6; k < i; k++) {
                    if(k < 0 || mat[k][j] == -1) continue;
                    mat[i][j] += mat[k][j];
                }
                for (int k = j-6; k < j; k++) {
                    if(k < 0 || mat[i][k] == -1) continue;
                    mat[i][j] += mat[i][k];
                }
                mat[i][j] %= 1_000_000_007;
            }

        }
        System.out.println(mat[N-1][M-1]);
    }
}