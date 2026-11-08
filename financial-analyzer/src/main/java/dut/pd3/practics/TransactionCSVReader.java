package dut.pd3.practics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVReader {

    public static List<Transaction> readTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            URL url = new URL(filePath);
            // Використовуємо Cp1251 для коректного зчитування оригінального CSV з сервера
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(url.openStream(), "UTF-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length >= 3) {
                        Transaction transaction = new Transaction(
                                values[0].trim(),
                                Double.parseDouble(values[1].trim()),
                                values[2].trim()
                        );
                        transactions.add(transaction);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}