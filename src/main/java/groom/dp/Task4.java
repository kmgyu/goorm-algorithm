package groom.dp;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Task4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        BigInteger[][] dp = new BigInteger[K + 1][N + M + 1];
        for (int t = 0; t <= K; t++) Arrays.fill(dp[t], BigInteger.ZERO);
        dp[0][N] = BigInteger.ONE;

        for (int t = 0; t < K; t++) {
            for (int i = 1; i < N + M; i++) { // i+1 접근 안전하게
                BigInteger v = dp[t][i];
                if (v.signum() == 0) continue; // 미세 최적화
                dp[t + 1][i - 1] = dp[t + 1][i - 1].add(v);
                dp[t + 1][i    ] = dp[t + 1][i    ].add(v);
                dp[t + 1][i + 1] = dp[t + 1][i + 1].add(v);
            }
        }

        BigInteger ans = BigInteger.ZERO;
        for (int t = 1; t <= K; t++) {
            ans = ans.add(dp[t][0]).add(dp[t][N + M]);
        }
        System.out.println(ans.toString());
    }
}
