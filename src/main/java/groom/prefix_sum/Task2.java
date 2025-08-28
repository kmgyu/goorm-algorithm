package groom.prefix_sum;

import java.io.*;
import java.util.*;

public class Task2 {
    private static final int MAXN = 100_000;
    private static int[][] cnt = new int[MAXN][26];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(st.nextToken());
        String chars = st.nextToken();

        for (int i = 0; i < N; i++) {
            int index = chars.charAt(i)-'a';
            cnt[i][index] ++;
        }

        for (int i = 0; i < N-1; i++) {
            for (int j = 0; j < 26; j++) {
                cnt[i+1][j] += cnt[i][j];
            }
        }

        int Q =  Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st =  new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken()); // 안쓰임...
            char[] T = st.nextToken().toCharArray();

            int[] temper = new int[26];

            for (char c: T) {
                temper[c-'a']++;
            }

            int[] getter = new int[26];
            for (int j = 0; j < 26; j++) {
                if (l>1) getter[j] = cnt[r-1][j] - cnt[l-2][j];
                else getter[j] = cnt[r-1][j];
            }

//            System.out.println(Arrays.toString(temper));
//            System.out.println(Arrays.toString(getter));
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < 26; j++) {
                if (temper[j] == 0) continue;
                min = Math.min(min, getter[j]/temper[j]);
            }
            bw.write(String.valueOf(min));
            bw.newLine();

        }
        bw.flush();
    }
}
