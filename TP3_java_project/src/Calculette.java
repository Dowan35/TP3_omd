
public class Calculette {

    public static int addition(int a, int b) {
        return a + b;
    }

    public static int soustraction(int a, int b) {
        return a - b;
    }

    // Ceci est une multiplication
    public static int multiplication(int a, int b) {
        return a * b;
    }

    // Cette fonction réalise la division de deux entiers
    public static int division_v2(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division par zéro !!");
        }
        return a / b;
    }

    public static void main(String[] args) {
        System.out.println("1 + 3 = " + addition(1, 3));
        System.out.println("Division : " + division_v2(10, 2));
        System.out.println("Multip : " + multiplication(10, 2));
    }
}
