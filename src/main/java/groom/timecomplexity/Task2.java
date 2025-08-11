package groom.timecomplexity;
import java.io.*;
import java.util.*;

class Task2 {
    public static HashMap<Integer, Boolean> filter(int N) {
        Boolean prime[] = new Boolean[N+1];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
        HashMap<Integer, Boolean> primary = new HashMap<Integer, Boolean>();
        for(int i = 2; i <= (int)(Math.sqrt(N)+1); i++) {
            if (prime[i]) {
                for (int j = i*2; j<=N; j+=i) {
                    prime[j] = false;
                }
            }
        }
        for(int i = 2; i<=N; i++) {
            if (prime[i]) {
                primary.put(i, true);
            }
        }
        return primary;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        int maxima = 0;

        for(int i = 0; i<N; i++) {
            A[i] = Integer.parseInt(br.readLine());
            maxima = maxima > A[i] ? maxima : A[i];
        }

        HashMap<Integer, Boolean> primary = filter(maxima);

        for(int num : A) {
            System.out.println(primary.containsKey(num) ? "Yes" : "No");
        }

    }
}