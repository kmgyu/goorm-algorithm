package groom.graph.theory;

import java.io.*;
import java.util.*;

class Task3 {
    public static int find(int n) {
        if (node[n] == n) return n;
        node[n] = find(node[n]);
        return node[n];
    }

    public static Integer node[];

    // N(N-1)/2 > 200000
    // 그러니까 HashMap 기반으로 해서 엣지 저장해야 한다. 인접 리스트 방식으로 잇기?
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        node = new Integer[N];
        Map<Integer, Set<Integer>> connection = new HashMap<>();

        Arrays.setAll(node, i -> i);

        for (int i = 0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());

            Integer parent = a-1;
            Integer child = b-1;

            if (!connection.containsKey(parent)) {
                Set<Integer> temp = new HashSet<Integer>();
                temp.add(child);
                connection.put(parent, temp);
            } else {
                connection.get(parent).add(child);
            }

            if (connection.containsKey(child)) {
                // child가 parent 가진 양방향
                if (connection.get(child).contains(parent)) {

                    if (parent > child) {
                        parent = parent^child;
                        child = parent^child;
                        parent = parent^child;
                    }
                    if (node[parent] != node[child]) node[find(child)] = find(node[parent]);

                }
            }

        }
        for (int i = 0; i<N; i++) { node[i] = find(node[i]); }

        Set<Integer> set = new HashSet<Integer>(Arrays.asList(node));

        System.out.println(set.size());
        // System.out.println(Arrays.asList(node));
    }
}