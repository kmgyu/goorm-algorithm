package groom.graph.search_deep;

import java.io.*;
import java.util.*;

class Task3 {
    // bfs 활용하고, 날아다니는 케이스를 따로 둬서 에너지 절약 하는 것...을 생각했다. 충전 절약 어떻게하지 하고 있었는데 해설은 다르게 풀었음.
    // BFS + 이분탐색 + 3차원 이용하는 문제라고 함...
    // 더 상세하게는, 에너지 K의 범위를 0~N*M-1로 보고 BFS로 도달 상태를 확인한다. 즉, BFS를 활용한 매개변수 탐색임.
    // 공간 복잡도가 N**3인데 최댓값이 1000만 넘어가도 감당 안되는 문제아님? 근데 사실 가정은 의미없긴 하다..
    // 이런 건 문제 유형을 먼저 알아야 하는 맞아야 아는 문제같음... 이런 거 그냥 보고 푸는 사람 대단하다.

    static final int MAX = 50;
    static final int INF = 1_000_000_000;
    static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
    static int N, M, T;
    static int[][] S = new int[MAX][MAX];
    static int[][][] dist = new int[MAX][MAX][MAX * MAX]; // dist(i, j, k): (i,j) 위치에 에너지가 k만큼 남은 상태로 도달하기 위한 최단 거리
    static Queue<int[]> q = new LinkedList<>(); // Linked List 쓰던가, ArrayDeque 쓰던가...

    /*
    처음부터 계속 날면서 모든 칸을 방문하고 탈출구로 도달할 때의 필요한 에너지는 N*M-1이다.
    그럼 [0, N*M-1] 범위에서 K의 최솟값을 찾으면 된다.
    f(K)를 최대 에너지가 K일 때의 T초 이내에 도달 가능하면 1, 불가능하면 0인 함수라고 정의한다면, 이분 탐색을 쓸 수 있는 함수 꼴이 된다.
    그러므로 범위 내에서 이분 탐색을 이용해서 f(K)=1인 K 중에서 최솟값을 찾으면 된다.
    */
    public static boolean f(int K) {
        // dist 배열 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dist[i][j], INF);
            }
        }

        // BFS 시작
        q.clear();
        q.add(new int[]{0, 0, K});
        dist[0][0][K] = 0;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int i = current[0], j = current[1], k = current[2];

            // 에너지 충전
            if (k < K && S[i][j] == 2 && dist[i][j][k + 1] > dist[i][j][k] + 1) {
                dist[i][j][k + 1] = dist[i][j][k] + 1;
                q.add(new int[]{i, j, k + 1});
            }

            // 사방 탐색
            for (int[] d : dir) {
                int ni = i + d[0], nj = j + d[1];

                // 범위 밖 또는 벽인 경우 처리하지 않음
                if (ni < 0 || ni >= N || nj < 0 || nj >= M || S[ni][nj] == 0) continue;

                // run (두 칸 모두 달리기 가능)
                // 탐색 시 ni(next i)와 i를 비교해서 최솟값을 넣어준다. 그냥 bfs도 아님 다익스트라임.
                if (S[i][j] == 2 && S[ni][nj] == 2) {
                    if (dist[ni][nj][k] > dist[i][j][k] + 1) {
                        dist[ni][nj][k] = dist[i][j][k] + 1;
                        q.add(new int[]{ni, nj, k});
                    }
                }

                // fly (에너지를 사용해 이동)
                else {
                    if (k > 0 && dist[ni][nj][k - 1] > dist[i][j][k] + 1) {
                        dist[ni][nj][k - 1] = dist[i][j][k] + 1;
                        q.add(new int[]{ni, nj, k - 1});
                    }
                }
            }
        }

        // (N-1,M-1)에 T 이내로 도달한 상태가 있는지 확인
        for (int k = 0; k <= K; k++) {
            if (dist[N - 1][M - 1][k] <= T) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N, M, T 입력 받기
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        T = Integer.parseInt(input[2]);

        // S 배열 입력 받기
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                S[i][j] = Integer.parseInt(input[j]);
            }
        }

        // 이분 탐색으로 최적의 K 값을 찾기
        int st = 0, en = N * M - 1, ans = -1;
        while (st <= en) {
            int mid = (st + en) / 2;
            if (f(mid)) {
                ans = mid;
                en = mid - 1; // 더 작은 값으로 이동
            } else {
                st = mid + 1; // 더 큰 값으로 이동
            }
        }

        // 결과 출력
        System.out.println(ans);
    }
}

/*
GPT의 최적화 버전
1. 탐색 범위 최적화 (K 상한 축소)
원래는 초기 에너지 K의 범위를 0 ~ N*M-1로 잡았어.
그런데 실제로는 T초 안에 움직이니까, 충전/이동을 합쳐도 한 번에 최대 1씩밖에 에너지 변동을 못 함.
따라서 실질적으로 필요한 K는 min(N*M-1, T)를 넘을 수 없어.
이렇게 하면 이분 탐색의 upper bound가 확 줄어들고, logK 단계 수도 작아짐.
-> upper bound가 결국 min(T, K)니까... 당연하지만 해설에선 이걸 설명을 안해준다!!

2. BFS 상태 가지치기
큐에서 뽑은 (x, y, k) 상태에 대한 도달 시간 t가 T를 초과하면 더 볼 필요가 없음. → 바로 continue.
따라서 불필요한 상태 확장을 안 하니까 시간 크게 줄어듦.
-> BFS 프루닝도 생각해볼 수가 있는 것 같다. 그에엑...

3. 도착 즉시 종료
(N-1, M-1)에 도착하는 순간 바로 true 리턴.
원래 구현은 끝까지 다 돌아보고 마지막에만 확인했는데, 이건 시간 낭비.
목표 지점 도착이 BFS 상 최소 시간임이 보장되니까 조기 종료가 안전함.

4. dist 배열 메모리 절약
원래 코드에서는 dist[MAX][MAX][MAX*MAX]라는 고정 크기 50×50×2500짜리 배열을 통째로 쓰고 매번 초기화했음.
최적화판에서는 dist[N][M][K+1]만큼만 만들고 초기화.
이렇게 하면 불필요한 공간 사용 줄고, 초기화 비용도 줄어듦.
(필요하면 visited stamp 기법으로 더 최적화 가능)
-> 동적이면 공간 복잡도 최적화하는데 해설에선 일부러 MAX 기준으로 설명함. 사실 상관없긴 한데 최적화해주는 게 좋다!!

5. BFS 구조 단순화
모든 전이(run/fly/충전)가 비용 1이라 다익스트라가 아니라 순수 BFS로 충분.
충전도 큐에 (x,y,k+1) 넣는 방식으로 통일해서, 복잡도 그대로 유지하면서 코드가 간결해짐.

속도는 유의미하진 않았는데, N이 50이라 그런 듯... 여기서 더 늘어나면 Fast I/O부터 시작해서 생각이 많아질 것 같긴 함.
 */

//import java.io.*;
//import java.util.*;
//
//public class Main {
//    static final int WALL = 0;
//    static final int GROUND = 1;
//    static final int CHARGE = 2;
//    static final int INF = 1_000_000_000;
//
//    static int N, M, T;
//    static int[][] S;
//    static final int[] dx = {-1, 1, 0, 0};
//    static final int[] dy = {0, 0, -1, 1};
//
//    static boolean in(int x, int y) { return 0 <= x && x < N && 0 <= y && y < M; }
//    static boolean canRun(int x, int y, int nx, int ny) {
//        // 양쪽이 2일 때만 달리기(에너지 유지) 허용
//        return S[x][y] == CHARGE && S[nx][ny] == CHARGE;
//    }
//
//    static boolean feasible(int K) {
//        // dist[x][y][k] = 남은 에너지 k로 (x,y)에 도달하는 최소 시간
//        int[][][] dist = new int[N][M][K + 1];
//        for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) Arrays.fill(dist[i][j], INF);
//
//        ArrayDeque<int[]> q = new ArrayDeque<>();
//        dist[0][0][K] = 0;
//        q.offer(new int[]{0, 0, K});
//
//        while (!q.isEmpty()) {
//            int[] cur = q.poll();
//            int x = cur[0], y = cur[1], k = cur[2];
//            int t = dist[x][y][k];
//
//            if (t > T) continue;                  // 시간 제한 초과 가지치기
//            if (x == N - 1 && y == M - 1) return true;  // 도착 즉시 성공
//
//            // 충전: 1초 소비, 에너지 +1 (단, 초기 상한 K를 넘지 못함)
//            if (S[x][y] == CHARGE && k < K && dist[x][y][k + 1] > t + 1) {
//                dist[x][y][k + 1] = t + 1;
//                q.offer(new int[]{x, y, k + 1});
//            }
//
//            // 이동
//            for (int d = 0; d < 4; d++) {
//                int nx = x + dx[d], ny = y + dy[d];
//                if (!in(nx, ny) || S[nx][ny] == WALL) continue;
//
//                if (canRun(x, y, nx, ny)) {
//                    // run: 에너지 소모 없이 1초 이동
//                    if (dist[nx][ny][k] > t + 1) {
//                        dist[nx][ny][k] = t + 1;
//                        q.offer(new int[]{nx, ny, k});
//                    }
//                } else {
//                    // fly: 에너지 1 소모 + 1초
//                    if (k > 0 && dist[nx][ny][k - 1] > t + 1) {
//                        dist[nx][ny][k - 1] = t + 1;
//                        q.offer(new int[]{nx, ny, k - 1});
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//    static int solve() {
//        int hi = Math.min(N * M - 1, T);  // 초기 에너지 상한 축소
//        int lo = 0, ans = -1;
//        while (lo <= hi) {
//            int mid = (lo + hi) >>> 1;
//            if (feasible(mid)) {
//                ans = mid;
//                hi = mid - 1;
//            } else {
//                lo = mid + 1;
//            }
//        }
//        return ans;
//    }
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        T = Integer.parseInt(st.nextToken());
//
//        S = new int[N][M];
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < M; j++) S[i][j] = Integer.parseInt(st.nextToken());
//        }
//
//        System.out.println(solve());
//    }
//}