package groom.sort;

import java.io.*;
import java.util.*;

public class Task2 {
    // 이게 제일 성능이 좋다고 함. 뎃...
    public static int countChar(String str, char target) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == target) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int N = Integer.parseInt(nk[0]);
        int K = Integer.parseInt(nk[1]);

        String[] nums = br.readLine().split(" ");

        Arrays.sort(nums, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int is1 = Integer.parseInt(s1);
                int is2 = Integer.parseInt(s2);
                String bin_s1 = Integer.toBinaryString(is1);
                String bin_s2 = Integer.toBinaryString(is2);

                int s1_cnt = countChar(bin_s1, '1');
                int s2_cnt = countChar(bin_s2, '1');
                if (s1_cnt != s2_cnt) {
                    return s2_cnt-s1_cnt;
                }
                return is2 - is1;
            }
        });
        System.out.println(nums[K-1]);
    }
}