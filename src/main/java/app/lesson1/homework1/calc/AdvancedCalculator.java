package app.lesson1.homework1.calc;

public class AdvancedCalculator implements ICalculator{

    @Override
    public int sum(int a, int b) {
        long res = (long) a + b;
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE)
            throw new RuntimeException("OverFlow");
        return a + b;
    }

    @Override
    public int diff(int a, int b) {
        long res = (long) a - b;
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE)
            throw new RuntimeException("OverFlow");
        return a - b;
    }

    @Override
    public int mult(int a, int b) {
        long res = (long) a * b;
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE)
            throw new RuntimeException("OverFlow");
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        if (b == 0)
            throw new ArithmeticException("На ноль делить нельзя");
        return a / b;
    }
}
