package dut.pd3.practics;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Calculate total balance
    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    // Count transactions by specific month (MM-YYYY)
    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            try {
                LocalDate date = LocalDate.parse(transaction.getDate(), DATE_FORMATTER);
                String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
                if (transactionMonthYear.equals(monthYear)) {
                    count++;
                }
            } catch (Exception ignored) {
            }
        }
        return count;
    }

    // Find top 10 expenses
    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    // Total expenses by category
    public static Map<String, Double> getTotalExpensesByCategory(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        Transaction::getDescription,
                        Collectors.summingDouble(t -> Math.abs(t.getAmount()))
                ));
    }

    // Total expenses by month
    public static Map<String, Double> getTotalExpensesByMonth(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        t -> {
                            LocalDate d = LocalDate.parse(t.getDate(), DATE_FORMATTER);
                            return d.format(DateTimeFormatter.ofPattern("MM-yyyy"));
                        },
                        Collectors.summingDouble(t -> Math.abs(t.getAmount()))
                ));
    }
}