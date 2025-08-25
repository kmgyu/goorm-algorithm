package groom.dp;

import java.io.*;

public class Task5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int mod = 100_000_007;
        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int [n+1][5];

        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[1][2] = 1;
        dp[1][3] = 1;
        dp[1][4] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                switch (j) {
                    case 0:
                        for (int k = 0; k < 5; k++) {
                            dp[i+1][k] = (dp[i+1][k]+dp[i][j])%mod;
                        }
                        break;
                    case 1:
                        dp[i+1][0] = (dp[i+1][0] +  dp[i][j])%mod;
                        dp[i+1][2] = (dp[i+1][2] +  dp[i][j])%mod;
                        dp[i+1][3] = (dp[i+1][3] +  dp[i][j])%mod;
                        break;
                    case 2:
                        dp[i+1][0] = (dp[i+1][0] +  dp[i][j])%mod;
                        dp[i+1][1] = (dp[i+1][1] +  dp[i][j])%mod;
                        dp[i+1][3] = (dp[i+1][3] +  dp[i][j])%mod;
                        dp[i+1][4] = (dp[i+1][4] +  dp[i][j])%mod;
                        break;
                    case 3:
                        dp[i+1][0] = (dp[i+1][0] +  dp[i][j])%mod;
                        dp[i+1][1] = (dp[i+1][1] +  dp[i][j])%mod;
                        dp[i+1][2] = (dp[i+1][2] +  dp[i][j])%mod;
                        break;
                    case 4:
                        dp[i+1][0] = (dp[i+1][0] +  dp[i][j])%mod;
                        dp[i+1][2] = (dp[i+1][2] +  dp[i][j])%mod;
                        break;
                }
            }
        }

        int ans = (dp[n][0] + dp[n][1] + dp[n][2] + dp[n][3] + dp[n][4]) % mod;
        System.out.println(ans);
    }
}
