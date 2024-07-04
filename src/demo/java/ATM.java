package demo.java;

import java.util.List;
import java.util.Scanner;

public class ATM {
    private final List<Card> cards;

    public ATM(List<Card> cards) {
        this.cards = cards;
    }

    public Card authenticate(String cardNumber, int pin) {
        // Метод для авторизации
        for(Card card : cards){
            if(card.getCardNumber().equals(cardNumber) && card.getPin() == pin){
                return card;
            }
        }
        return null;
    }

    public void checkBalance(Card card) {
        // Метод для проверки баланса
        System.out.println("Ваш текущий баланс: " + card.getBalance());
    }

    public void withdraw(Scanner scanner, Card card) {
        // Метод для снятия средств
        System.out.print("Введите сумму для снятия: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        if (amount > card.getBalance()) {
            System.out.println("Недостаточно средств на счете.");
        } else {
            card.setBalance(card.getBalance() - amount);
            System.out.println("Вы сняли " + amount + ". Ваш текущий баланс: " + card.getBalance());
        }
    }

    public void deposit(Scanner scanner, Card card) {
        // Метод для пополнения баланса
        System.out.print("Введите сумму для пополнения: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Считываем символ новой строки после ввода суммы

        if (amount > 1000) {
            System.out.println("Сумма пополнения не должна превышать 1 000.");
        } else {
            card.setBalance(card.getBalance() + amount);
            System.out.println("Вы пополнили баланс на " + amount + ". Ваш текущий баланс: " + card.getBalance());
        }
    }
    }
