package groom.search.linearsearch;

import java.io.*;
class Task3 {
    public static int N;
    public static int K;
    public static int mat[][];

    public static int find(int x, int y) {
        int cnt = 0;
        for (int i=-1; i<2; i++) {
            for (int j=-1; j<2; j++) {
                if (0<=x+i && x+i<N && 0<=y+j && y+j<N) {
                    cnt += mat[x+i][y+j];
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        mat = new int[N][];
        for (int i = 0; i < N; i++) {
            String temp[] = br.readLine().split(" ");
            int row[] = new int[N];
            for (int j = 0; j < N; j++) {
                row[j] = Integer.parseInt(temp[j]);
            }
            mat[i] = row;
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (mat[i][j]==0 && find(i,j)==K) {
                    cnt +=1;
                }
            }
        }
        System.out.println(cnt);
    }
}