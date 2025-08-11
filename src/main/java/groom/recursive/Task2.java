package groom.recursive;

import java.io.*;
import java.util.*;
import java.util.stream.*;

class Task2 {
    static int K;
    static Stack<Integer> l1 = new Stack<>();
    static Stack<Integer> l2 = new Stack<>();
    static Stack<Integer> l3 = new Stack<>();
    static int cnt = 0;
    // def hanoi(N, start, to, via):
//     if N == 1:
//         move(1, start, to)
//     else:
//         hanoi(N-1, start, via, to)
//         move(N, start, to)
//         hanoi(N-1, via, to, start)
    public static int sumStack(Stack<Integer> stack) {
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }

    public static int move(Stack<Integer> start, Stack<Integer> to) {
        if (cnt == K) return 0;
        cnt += 1;
        to.push(start.pop());
        return 0;
    }
    public static int recursion(int n, Stack<Integer> start, Stack<Integer> to, Stack<Integer> via) {
        if (cnt == K) return 0;
        if (n == 1) {
            move(start, to);
        } else {
            recursion(n-1, start, via, to);
            move(start, to);
            recursion(n-1, via, to, start);
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        for(int i = 20; i > 0; i--) {
            l1.push(i);
        }
        recursion(20, l1, l3, l2);
        int l1s = sumStack(l1);
        int l2s = sumStack(l2);
        int l3s = sumStack(l3);
        System.out.println(l1s+" "+l2s+" "+l3s);
    }
}