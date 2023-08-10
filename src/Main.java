

import java.util.Scanner;

/*
Банкомат
На эту детскую ШЛЯПУ убил 20 часов жизни...
 */

public class Main {
    private final static String START = """
            \nВведите необходимую операцию:
            \t* выберите 1 для операции "снять"
            \t* выберите 2 для операции "пополнить"
            \t* выберите 3 для операции "баланс"
            \t* выберите 4 для операции "сумма"
            \t* выберите 5 для операции "выход" """;
    private final static String EXIT = "Рабочий сеанс окончен.";

    public static void main(String[] args) {
        //загружаю банкноты в банкомат
        ATM.loadingNotes();
        //задаю первоначальный баланс счета на карте
        Card.setBalance(270);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(START);
            int selection = sc.nextInt();

            switch (selection) {
                case 1 -> {
                    ATM.getCash();
                    Card.viewBalance();
                    ATM.viewAmountNotes();
                }
                case 2 -> {
                    ATM.setCash();
                    Card.viewBalance();
                    ATM.viewAmountNotes();
                }
                case 3 -> Card.viewBalance();
                case 4 -> ATM.viewBalance();
                case 5 -> {System.out.println(EXIT); System.exit(0);}
            }
        }
    }
}