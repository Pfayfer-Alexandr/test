import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите первое число ");
        int num1 = scanner.nextInt();
        System.out.println("Введите второе число ");
        int num2 = scanner.nextInt();
        System.out.println("Введите действие (+, -, *, /)");
        String action = scanner.next();

        calculate(num1, num2, action);
    }

    public static void calculate(int num1, int num2, String action) {
        int result;
        switch(action) {
            case "+":
                result = num1 + num2;
                System.out.println("Результат " + result);
                break;
            case "-":
                result = num1 - num2;
                System.out.println("Результат " + result);
                break;
            case "*":
                result = num1 * num2;
                System.out.println("Результат " + result);
                break;
            case "/":

                if (num2 == 0)
                    System.out.println("Делить на ноль нельзя");
                else {
                    result = num1 / num2;

                System.out.println("Результат " + result);}
            default:
                System.out.println("Вы ввели неверное действие");
        }
    }
}