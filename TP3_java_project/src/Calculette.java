
public class Calculette {

    public static int addition(int a, int b) {
        return a + b;
    }

    public static int soustraction(int a, int b) {
        return a - b;
    }

    public static int multiplication(int a, int b) {
        return a * b;
    }

    // Cette fonction réalise la division de deux entiers
    public static int division(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division par zéro !");
        }
        return a / b;
    }

    public static void main(String[] args) {
        System.out.println("1 + 3 = " + addition(1, 3));
        System.out.println("Division : " + division(10, 2));
        System.out.println("Multip : " + multiplication(10, 2));
    }
}
