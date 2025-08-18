package groom.graph.search;

import java.io.*;
import java.util.*;

public class Task4 {
    public static int natural; // 1번 국가 언어. 내츄럴, 두번째 언어는 메서드 내에서만 선언
    public static int[] country; // 국가별 언어
    public static Set<Integer> languagePool; // 언어 풀
    public static List<Integer>[] edges;
    public static boolean[] visited;

    public static int bfs(int subLan) {
        // choose subLanguage and move far as he or she can.
        // initialize visited in here so visited does not make duplicated case
        Arrays.fill(visited, false);
        int cnt = 0; // visited count
        Deque<Integer> q = new ArrayDeque<>();
        q.add(0); // append home country

        while(!q.isEmpty()){
            int cur = q.poll();
            if(visited[cur]) continue;
            if (country[cur] != subLan && country[cur] != natural) continue;
            visited[cur] = true;
            cnt++;
            for(Integer next: edges[cur]){
                q.add(next);
            }
        }

        // for (int i = 0; i < visited.length; i++) {
        //         if (visited[i]) cnt++;
        //     }
        return cnt;
    }

    public static void main(String[] args) throws Exception {

        // input setting
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // initialize iterable static variables
        // ahhhhh tomany!!!!!!
        edges = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
        }
        visited = new boolean[N];
        country = new int[N];
        languagePool = new HashSet<>();

        // inputs again
        st =  new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int lan = Integer.parseInt(st.nextToken());
            country[i] = lan;
            languagePool.add(lan);
        }

        // i hate correcting...
        for (int i = 0; i < M; i++) {
            st = new  StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken())-1;
            int q = Integer.parseInt(st.nextToken())-1;
            edges[p].add(q);
            edges[q].add(p);
        }

        natural = country[0];

        int ans = 0;
        for (int lan : languagePool) {
            ans = Math.max(ans, bfs(lan));
        }

        System.out.println(ans);

    }
}
