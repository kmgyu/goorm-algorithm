package groom.greedy;

import java.io.*;
import java.util.*;

public class Task1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A, B, C, N;
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine());

        int[][] charger = new int [N][2];

        for (int i = 0; i < N; i++) {
            st =  new StringTokenizer(br.readLine());
            charger[i][0] = Integer.parseInt(st.nextToken()); // price
            charger[i][1] = Integer.parseInt(st.nextToken()); // type
        }

        Arrays.sort(charger, Comparator.comparingInt(a -> a[0]));
        int cnt = 0;
        long sum = 0;

        for (int[] cur : charger) {
            int price = cur[0];
            int type = cur[1];
            if (type == 0) {
                if (A > 0) {
                    A--;
                    cnt ++;
                    sum += price;
                }
                else if (C > 0) {
                    C--;
                    cnt ++;
                    sum += price;
                }
            }
            else {
                if (B > 0) {
                    B--;
                    cnt ++;
                    sum += price;
                }
                else if (C > 0) {
                    C--;
                    cnt ++;
                    sum += price;
                }
            }
        }


        System.out.println(cnt + " " + sum);
    }
}
