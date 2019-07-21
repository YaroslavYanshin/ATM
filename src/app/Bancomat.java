package app;

import java.util.regex.*;
import java.util.Scanner;
import java.util.*;

/**
 * Bancomat
 */

public class Bancomat {

    List<Card> cards;
    LoadSave ls;

    Scanner scanner = new Scanner(System.in);
    Pattern patern = Pattern.compile("^[-A-Z-a-z-0-9]{4}-[-A-Z-a-z-0-9]{4}-[-A-Z-a-z-0-9]{4}-[-A-Z-a-z-0-9]{4}");

    private boolean power = true;

    public Bancomat() {
        this.ls = new LoadSave();
        this.cards = ls.getData();
    }

    void start() {
        while (power) {
            String numer_card;
            welcomeWindow();
            numer_card = getCardNumber();
            if (numer_card.equals("None")) { 
                continue;
            }

            Card our_card = getCard(numer_card);
            int attempts = 3;
            if (!checkPinCodeCard(our_card, attempts)) { //
                continue;
            }

            Account our_acc = our_card.getAccount();
            functionsWindow(our_acc);
            while (callFunctions(our_acc));
        }
        ls.saveData();
    }

    boolean checkPinCodeCard(Card card, int attempts)
    {
        if(attempts <= 0)
        {
            card.block();
            System.out.println("Карта была заблокирована.");
            return false;
        }

        System.out.print("Введите пин код банковской карты: ");
        
        String pin_code = scanner.next();
        if(card.checkCode(pin_code))
        {
            System.out.print("Код введен верно.");
            return true;
        }
        else{
            System.out.print("Код введен неверно, повторите попыдку.");
            System.out.print("У вас осталось " + attempts + " попыток.");
            return checkPinCodeCard(card, attempts - 1);
        }
    }

    Card getCard(String nr_card) {
        for (Card card : this.cards) {
            if (card.checkCardNumber(nr_card)) {
                return card;
            }
        }
        return null;
    }

    String getCardNumber() {
        String numer_card = "None";
        do {
            System.out.print("Номер карты: ");
            numer_card = scanner.next();
            if (checkCardNumbernFormat(numer_card)) {
                break;
            } else {
                System.out.println("Неверно введен номер карты, попробуйте еще раз.");
                numer_card = "None";
            }
        } while (true);
        return numer_card;
    }

    boolean checkCardNumbernFormat(String card_number) {
        return patern.matcher(card_number).matches();
    }

    void headMessage() {
        System.out.println("--------------------------------------------------------------------\n"
                         + "------------------------ Bankomat Company --------------------------\n"
                         + "--------------------------------------------------------------------");
    }

    void welcomeWindow() {
        headMessage();
        System.out.println("Доброго Вам времени суток ");
    }

    void functionsWindow(Account acc ) {
        System.out.println("Добро пожаловать " + acc.name+" в наш прекрасный банкомат!\n"
                + "Что вы хотите сделать?\n" 
                + "1. Показать баланс\n" 
                + "2. Пополнить счет\n" 
                + "3. Снять со счета\n"
                + "4. Выйти\n" 
                + "5. Power OFF");

        
    }

    boolean callFunctions(Account account) {
        System.out.print("Выберите функцию: ");
        int nr_funk = scanner.nextInt();
        switch (nr_funk) {
        case 1:
            showSaldo(account);
            break;
        case 2:
            incSaldo(account);
            break;
        case 3:
            decSaldo(account);
            break;
        case 4:
            return false;
        case 5:
            this.power = false;
            return false;
        default:
            System.out.println("Неверный номер функции.");
            break;
        }

        return true;
    }

    void showSaldo(Account account) {
        System.out.print("Ваш баланс состовляет: ");
        System.out.println(account.getInvoiceAmount());
    }

    void incSaldo(Account account) {
        System.out.print("Введите сумму для пополнения: ");
        float sum = scanner.nextInt();
        account.Put(sum);
    }

    void decSaldo(Account account) {
        System.out.print("Введите сумму, которую вы бы хотели снять: ");
        float sum = scanner.nextFloat();
        account.Withdraw(sum);
    }

}