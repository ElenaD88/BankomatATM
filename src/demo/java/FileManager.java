package demo.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private List<Card> cards = new ArrayList<>();

    public List<Card> readDataFromFile() {
        // Метод для чтения данных из файла и инициализации списка карт

        try (BufferedReader br = new BufferedReader(new FileReader("src/demo/resources/credit_cards.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(" ");
                Card card = new Card(data[0], Integer.parseInt(data[1]), Double.parseDouble(data[2]));
                cards.add(card);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cards;
    }

    public void saveDataToFile(List<Card> cards) {
        // Метод для сохранения данных в файл
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/demo/resources/credit_cards.txt"))) {
            for(Card card : cards) {
                bw.write(card.getCardNumber() + " " + card.getPin() + " " + card.getBalance());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
