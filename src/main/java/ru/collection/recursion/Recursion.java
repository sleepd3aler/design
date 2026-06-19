package ru.collection.recursion;

public class Recursion {
    public int loop(int summary, int index) {
        for (int i = index; i > 0; i--) {
            summary += i;
        }
        return summary;
    }

    public int sum(int summary, int index) {
        if (index > 0) {
            summary += index;
            index--;
            System.out.println(" ".repeat(index) + "before " + index);
            summary = sum(summary, index);
            System.out.println(" ".repeat(index) + "after " + index);
        }
        return summary;
    }

    public long factorialLoop(long f) {
        long res = 1;
        if (f > 0) {
            for (int i = 1; i <= f; i++) {
                res = res * i;
            }
        }
        return res;
    }

    public long factorialRecursion(long f) {
        long res = 1;
        if (f > 0) {
            res = res * f;
            f--;
            res *= factorialRecursion(f);
        }
        return res;
    }

    public long fibonacciLoop(int n) {
        long res = 0L;
        if (n == 1) {
            res = 1L;
        } else if (n > 1) {
            long f1 = 1L;
            long f2 = 1L;
            for (int i = 0; i < (n - 2); i++) {
                res = f2 + f1;
                f1 = f2;
                f2 = res;
            }
        }
        return res;
    }

    public long fibonacciRecursion(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        long f1 = fibonacciRecursion(n - 1);
        long f2 = fibonacciRecursion(n - 2);
        return f1 + f2;
    }

    public static void main(String[] args) {
        Recursion recursion = new Recursion();
        int resultLoop = recursion.loop(10, 5);
        int resultRec = recursion.sum(10, 5);
        System.out.println("result  of loop = " + resultLoop);
        System.out.println("result  of recursion = " + resultRec);
        int factorialLoop = (int) recursion.factorialLoop(6);
        System.out.println("factorial loop : " + factorialLoop);
        int factorialRec = (int) recursion.factorialRecursion(6);
        System.out.println("factorial rec : " + factorialRec);
        int fibLoop = (int) recursion.fibonacciLoop(7);
        System.out.println("Fibonacci loop : " + fibLoop);
        int fibRec = (int) recursion.fibonacciRecursion(7);
        System.out.println("Fibonacci rec : " + fibRec);
    }
}
