package groom.dp;

import java.io.*;
import java.util.*;

public class Task2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] nums = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }

        long ans = 0L;
        ans = Math.max(nums[0], ans);

        for (int i = 1; i < N; i++) {
            nums[i] = Math.max(nums[i], nums[i-1]+nums[i]);
            ans = Math.max(ans, nums[i]);
        }
        System.out.println(ans);
    }
}
