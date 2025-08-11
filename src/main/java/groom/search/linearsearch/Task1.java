package groom.search.linearsearch;

import java.io.*;
import java.util.*;
class Task1 {
    public static int N;
    public static int M;
    public static int K;
    public static List<int[]> coords = new ArrayList<>();

    public static int calculate(int r, int c) {
        int result = 0;
        for (int i = 0; i<K; i++) {
            int[] coord = coords.get(i);
            result += (r-coord[0])*(r-coord[0]) + (c-coord[1])*(c-coord[1]);
        }
        return result;
    }

    public static boolean find(int r, int c) {
        for (int i = 0; i<K; i++) {
            int[] coord = coords.get(i);
            if (coord[0] == r & coord[1] == c) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        for(int i = 0; i<K; i++) {
            input = br.readLine().split(" ");
            coords.add(new int[]{Integer.parseInt(input[0]), Integer.parseInt(input[1])});
        }

        int result = Integer.MAX_VALUE;
        for(int i=1; i<N+1; i++) {
            for (int j=1; j<M+1; j++) {
                if (find(i, j)) continue;
                result = Math.min(calculate(i, j), result);
            }
        }
        System.out.println(result);
    }
}