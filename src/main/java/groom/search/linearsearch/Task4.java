package groom.search.linearsearch;

import java.io.*;
import java.util.*;

class Task4 {
    // 에라토스테네스 in java
    // 구름 RPG1 재활용
    public static int N;
    public static int[] armors;

    public static Boolean[] filter(int N) {
        Boolean prime[] = new Boolean[N+1];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
        // HashMap<Integer, Boolean> primary = new HashMap<Integer, Boolean>();
        for(int i = 2; i <= (int)(Math.sqrt(N)+1); i++) {
            if (prime[i]) {
                for (int j = i*2; j<=N; j+=i) {
                    prime[j] = false;
                }
            }
        }
        return prime;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        armors = new int[N];
        int M = 0;

        for (int i=0; i<N; i++) {
            armors[i] = Integer.parseInt(br.readLine());
            M = Math.max(armors[i], M);
        }

        Boolean[] primary = filter(M);
        for (int armor : armors) {
            int current = armor;
            while (true) {
                if (primary[current]) {
                    System.out.println(armor-current);
                    break;
                }
                current-=1;
            }
        }
    }
}