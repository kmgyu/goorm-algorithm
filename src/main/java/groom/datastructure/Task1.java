package groom.datastructure;

import java.io.*;
import java.util.*;
import java.util.stream.*;
class Task1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String input[] = br.readLine().split(" ");
        long H[] = Arrays.stream(input).mapToLong(Long::parseLong).toArray();
        int ans[] = new int[N];

        int sl = 0; //stack length
        Stack<Long> stack = new Stack<>();
        for(int i = 0; i<N; i++) {
            ans[i] = sl;
            while(!stack.empty() && stack.peek() <= H[i]) {
                stack.pop();
                sl -= 1;
            }
            stack.push(H[i]);
            sl += 1;

        }

        System.out.println(Arrays.stream(ans)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));
    }
}
