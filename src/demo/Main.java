package demo;

import demo.java.ATM;
import demo.java.Card;
import demo.java.FileManager;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new FileManager();
        List<Card> cards = fileManager.readDataFromFile();
        System.out.println(cards);
        cards.forEach(a -> System.out.println(a.getCardNumber() + " " + a.getPin() + " " + a.getBalance()));
        ATM atm = new ATM(cards);
        while (true) {
            System.out.print("Введите номер карты: ");
            String cardNumber = scanner.nextLine();
            System.out.print("Введите ПИН-код: ");
            int pin = scanner.nextInt();
            scanner.nextLine(); // Считываем символ новой строки после ввода ПИН-кода

            if (atm.authenticate(cardNumber, pin) != null) {
                showMenu();
                Card card = atm.authenticate(cardNumber, pin);

                int choice = scanner.nextInt();
                scanner.nextLine(); // Считываем символ новой строки после выбора пункта меню

                switch (choice) {
                    case 1:
                        atm.checkBalance(card);
                        break;
                    case 2:
                        atm.withdraw(scanner, card);
                        break;
                    case 3:
                        atm.deposit(scanner, card);
                        break;
                    case 4:
                        fileManager.saveDataToFile(cards);
                        System.out.println("Спасибо за использование нашего банкомата. До свидания!");
                        return;
                    default:
                        System.out.println("Неверный выбор.");
                }
            } else {
                System.out.println("Неверный номер карты или ПИН-код.");
            }
        }

        // Логика работы банкомата: авторизация, выбор действий и т.д.
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1. Проверить баланс");
        System.out.println("2. Снять средства");
        System.out.println("3. Пополнить баланс");
        System.out.println("4. Выйти");
    }
}
