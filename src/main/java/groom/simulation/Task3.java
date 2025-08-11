package groom.simulation;

import java.io.*;
import java.util.*;
class Task3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // String input = br.readLine();
        // System.out.println("Hello World! Your input is " + input);

        int N = Integer.parseInt(br.readLine());
        int[] H = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int b = 0; // silver bullet suppresor
        long result = 0;
        for(int h: H) {
            result += (h/10)*4; // 와 자동 몫연산
            h %= 10;
            while (h>0) {
                h -= b+1;
                b = (b+1)%4;
                result += 1;
            }
        }

        System.out.println(result);
    }
}