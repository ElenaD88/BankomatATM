package demo.java;

import java.util.List;
import java.util.Scanner;

public class BankomatService {

    public void useBankomat() {
        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new FileManager();

        List<Card> cards = fileManager.readDataFromFile();

        ATM atm = new ATM(cards);
        while (true) {
            System.out.print("Введите номер карты: ");
            String cardNumber = scanner.nextLine();
            System.out.print("Введите ПИН-код: ");
            String pin = scanner.nextLine();
//            scanner.nextLine(); // Считываем символ новой строки после ввода ПИН-кода

            if (checkNumber(cardNumber) && checkPinCode(pin) && atm.authenticate(cardNumber, pin) != null) {
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

    private static boolean checkNumber(String number) {
        return number.matches("\\b\\d{4}-\\d{4}-\\d{4}-\\d{4}\\b");
    }

    private static boolean checkPinCode(String code) {
        return code.matches("\\b\\d{4}\\b");
    }
}

