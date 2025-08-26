package groom.dp;

import java.io.*;
class Task6 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int dp[][][] = new int[n+1][3][2]; // n:횟수, c 쓴 횟수, b쓴 유무
        int mod = 1_000_000;

        dp[1][0][0] = 1;
        dp[1][0][1] = 1;
        dp[1][1][0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                // c 사용 시
                if (j < 2) {
                    dp[i + 1][j + 1][0] = (dp[i + 1][j + 1][0] + dp[i][j][0]) % mod;
                    dp[i + 1][j + 1][1] = (dp[i + 1][j + 1][1] + dp[i][j][1]) % mod;
                }
                // A 사용 시
                dp[i+1][0][0] = (dp[i+1][0][0] + dp[i][j][0]) % mod;
                dp[i+1][0][1] = (dp[i+1][0][1] + dp[i][j][1]) % mod;

                // b 사용
                dp[i+1][0][1] = (dp[i+1][0][1] + dp[i][j][0]) % mod;
            }

        }
        int ans = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                ans = (ans + dp[n][i][j])%mod;
            }

        }
        System.out.println(ans);
    }
}