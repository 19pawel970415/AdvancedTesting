package pl.sda.parametrized;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) throws IllegalArgumentException {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot be divided by 0");
        } else {
            return (double) a / (double) b;
        }
    }

    public boolean isPositive(int a) {
        if (a > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNegative(int a) {
        if (a < 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOdd(int a) {
        if (a % 2 != 0) {
            return true;
        } else {
            return false;
        }
    }

    public int min(int a, int b) {
        return Math.min(a, b);
    }

    public int max(int a, int b) {
        return Math.max(a, b);
    }

    public double average(int a, int b) {
        return ((float) (a + b)) / 2;
    }

    public double power(int a, int x) {
        return Math.pow(a, x);
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public int subtract(int a, int b, int c) {
        return a - b - c;
    }

    public int multiply(int a, int b, int c) {
        return a * b * c;
    }

    public double divide(int a, int b, int c) throws IllegalArgumentException {
        if (b == 0 || c == 0) {
            throw new IllegalArgumentException("Cannot be divided by 0");
        } else {
            return (double) a / (double) b / (double) c;
        }
    }

    public int min(int a, int b, int c) {
        int num = Math.min(a, b);
        return Math.min(num, c);
    }

    public int max(int a, int b, int c) {
        int num = Math.max(a, b);
        return Math.max(num, c);
    }

    public double average(int a, int b, int c) {
        return ((float) (a + b + c)) / 3;
    }
}

