package groom.datastructure;

import java.io.*;
import java.util.*;
class Task3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        Set<String> A = new HashSet<String>(Arrays.asList(br.readLine().split(" ")));
        Set<String> B = new HashSet<String>(Arrays.asList(br.readLine().split(" ")));

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            String at = input[0], bt = input[1];
            if (A.contains(at) && B.contains(bt)){
                A.remove(at);
                A.add(bt);
                B.remove(bt);
                B.add(at);
            }
        }

        input = A.toArray(new String[0]);
        Arrays.sort(input);
        StringBuilder sb = new StringBuilder();
        for (String str: input) {
            sb.append(str+" ");
        }
        System.out.println(sb);
    }
}