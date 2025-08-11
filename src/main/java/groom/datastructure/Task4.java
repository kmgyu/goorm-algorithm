package groom.datastructure;

import java.io.*;
import java.util.*;
class Task4 {
    public static int find(int n) {
        if (node[n] == n) return n;
        node[n] = find(node[n]);
        return node[n];
    }

    public static Integer node[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        node = new Integer[N];

        Arrays.setAll(node, i -> i);

        for (int i = 0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());

            int parent = a-1;
            int child = b-1;
            if (parent > child) {
                parent = parent^child;
                child = parent^child;
                parent = parent^child;
            }

            if (node[parent] != node[child]) node[find(child)] = find(node[parent]);
        }
        for (int i = 0; i<N; i++) { node[i] = find(node[i]); }

        Set<Integer> set = new HashSet<Integer>(Arrays.asList(node));

        System.out.println(set.size());
        // System.out.println(Arrays.asList(node));
    }
}