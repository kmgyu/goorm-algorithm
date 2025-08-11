package groom.sort;
import java.io.*;
import java.util.*;

class Task1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine(); // 사용하지 않음
        int N = Integer.parseInt(br.readLine());
        int island[][] = new int[N][3];

        for (int i = 0; i < N; i++) {
            String loc[] = br.readLine().split(" ");
            island[i][0] = Integer.parseInt(loc[0]);
            island[i][1] = Integer.parseInt(loc[1]);
            island[i][2] = Integer.parseInt(loc[2]);
        }

        Arrays.sort(island, new Comparator<int[]>() {
            @Override
            public int compare(int[] s1, int[] s2) {
                if (s1[0] != s2[0]) return s1[0] - s2[0];
                return s1[1] - s2[1];
            }
        });

        // 출력 확인용
        for (int[] row : island) {
            System.out.println(Arrays.toString(row));
        }
    }
}
