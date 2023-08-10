

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;

public class ATM {
    private final static String SUCCESSFULLY = "Операция прошла успешно.";
    private static Map<Integer, Integer> notes = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    private static Map<Integer, Integer> getNotes() {
        return notes;
    }

    //загрузка банкнот
    public static void loadingNotes() {
        getNotes().put(10, 3);
        getNotes().put(50, 3);
        getNotes().put(100, 1);
    }

    //снятие наличности
    public static void getCash() throws MyException {
        System.out.println("В наличии купюры номиналом: 10, 50 и 100 рублей.\nВведите необходимую сумму: ");
        int cash = sc.nextInt();
        if (cash <= getSum() && cash % 10 == 0 && cash <= Card.getBalance()) {
            Card.setBalance(Card.getBalance() - cash);
            extractNotes(cash);
            System.out.println(SUCCESSFULLY);
        } else if (cash > Card.getBalance()) {
            throw new MyException("На Вашем счете недостаточно средств.");
        } else if (cash % 10 != 0) {
            throw new MyException("В банкомате отсутствуют нужные купюры.");
        } else if (cash > getSum()) {
            throw new MyException("В банкомате недостаточно средств.");
        }
    }

    //выбор купюр для выдачи и списание в АТМ
    private static void extractNotes(int cash) {
        int haveNote_100 = notes.get(100), haveNote_50 = notes.get(50), haveNote_10 = notes.get(10);//количество банкнот в банкомате
        int note_100, note_50, note_10; //количество банкнот к выдаче

        int n_100 = cash / 100;

        if (haveNote_100 > 0 && haveNote_100 >= n_100 || n_100 == 0) {
            note_100 = n_100;
        } else {
            note_100 = haveNote_100;
        }

        int n_50 = (cash - note_100 * 100) / 50;

        if (haveNote_50 > 0 && haveNote_50 >= n_50 || n_50 == 0) {
            note_50 = n_50;
        } else {
            note_50 = haveNote_50;
        }

        int n_10 = (cash - note_100 * 100 - note_50 * 50) / 10;

        if (haveNote_10 > 0 && haveNote_10 >= n_10 || n_10 == 0) {
            note_10 = n_10;
        } else {
            note_10 = haveNote_10;
        }

        if ((note_100 * 100 + note_50 * 50 + note_10 * 10) == cash) {
            getNotes(10, note_10);
            getNotes(50, note_50);
            getNotes(100, note_100);
            System.out.println("Купюры номиналом 100 = " + note_100 + " Купюры номиналом 50 = " + note_50 + " Купюры номиналом 10 = " + note_10);
        } else throw new UncheckedIOException(
                "В банкомате отсутствуют нужные купюры.",
                new IOException());
    }

    private static void getNotes(int nom, int sum) {
        int noteAmount = notes.get(nom);//количество банкнот данного номинала до начала операции
        notes.put(nom, noteAmount - sum);
    }

    //пополнение счета
    public static void setCash() {
        System.out.println("Введите номинал купюры и их количество: ");
        int nom = sc.nextInt(), sum = sc.nextInt();
        if ((nom == 10 || nom == 50 || nom == 100) && sum >= 0) {
            setNotes(nom, sum);
            Card.setBalance(nom, sum);
            System.out.println(SUCCESSFULLY);
        } else System.out.println("Ошибка. Номинал купюры должен быть 10, 50 или 100 руб.");
    }

    private static void setNotes(int nom, int sum) {
        int noteSum = notes.get(nom);//количество банкнот данного номинала до начала операции
        notes.put(nom, sum + noteSum);
    }

    //просмотр остатка средств в банкомате
    public static void viewBalance() {
        System.out.println("В банкомате осталось денежных средств на сумму: " + getSum() + " руб.");
        viewAmountNotes();
    }

    //просмотр остатка банкнот в банкомате
    public static void viewAmountNotes() {
        System.out.println("Имеются купюры номиналом:" +
                "\n10 руб - " + notes.get(10) +
                "\n50 руб - " + notes.get(50) +
                "\n100 руб - " + notes.get(100));
    }

    //расчет средств в банкомате
    private static int getSum() {
        return notes.get(10) * 10 + notes.get(50) * 50 + notes.get(100) * 100;
    }
}