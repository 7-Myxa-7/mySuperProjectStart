

public class Card {
    private static int balance;

    public static int getBalance() {
        return balance;
    }

    public static void setBalance(int balance) { Card.balance = balance; }

    //пополнение средств на счет
    public static void setBalance(int nom, int sum) {
            balance += nom * sum;
    }
    //просмотр остатка средств на счете
    public static void viewBalance() {
        System.out.println("Баланс вашего счета равен: " + balance + " руб.");
    }
}