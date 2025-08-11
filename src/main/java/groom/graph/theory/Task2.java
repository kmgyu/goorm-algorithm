package groom.graph.theory;

import java.io.*;
import java.util.*;

class Task2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i<T; i++) {
            String input[] = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int M = Integer.parseInt(input[1]);
            for (int j=0; j<M; j++) {br.readLine();}
            System.out.println(N-1);
        }
    }
}