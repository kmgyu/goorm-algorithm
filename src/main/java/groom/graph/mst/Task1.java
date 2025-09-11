package groom.graph.mst;

import java.io.*;
import java.util.*;

public class Task1 {
    static int N, M, K;
    static Integer[] nodes;
    static List<int[]> edges = new ArrayList<>();

    static int find(int n) {
        if (n != nodes[n]) nodes[n] = find(nodes[n]);
        return nodes[n];
    }

    static boolean union(int a, int b) {
        int parent = find(a);
        int child = find(b);

        if (parent > child) {
            parent = parent ^ child;
            child = parent ^ child;
            parent = parent ^ child;
        }
        if (parent != child) {
            nodes[child] = parent;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        // IO logic
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N =  Integer.parseInt(br.readLine());
        M =  Integer.parseInt(br.readLine());

        nodes = new Integer[N+1];
        for (int i = 1; i < N+1; i++) {
            nodes[i] = i;
        }
//        IntStream.range(0, nodes.length)
//                .forEach(i -> nodes[i] = i);

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges.add(new int[] {a, b, 0});
        }

        K =  Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new int[] {a, b, w});
        }

        long result = 0;
        // solve logic
        edges.sort(Comparator.comparingInt((int[] a) -> a[2]));
        for(int[] edge : edges) {
            if (union(edge[0], edge[1])) {
                result += edge[2];
            }
        }

        boolean flag = false;
        for (int i = 1; i < N; i++) {
            if (find(nodes[i]) != 1) {
                flag = true;
                break;
            }
        }

        if (flag) System.out.println(-1);
        else System.out.println(result);
    }
}
