package dut.pd3.practics;

import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Total Balance: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Number of transactions for " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("\nTop 10 Expenses:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    // Visual report using stars (*)
    public static void printVisualReport(Map<String, Double> data, String title, double scale) {
        System.out.println("\n--- " + title + " (each '*' = " + scale + " UAH) ---");
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            int starsCount = (int) (entry.getValue() / scale);
            StringBuilder stars = new StringBuilder();
            for (int i = 0; i < starsCount; i++) {
                stars.append("*");
            }
            System.out.printf("%-20s : %.2f [%s]\n", entry.getKey(), entry.getValue(), stars.toString());
        }
    }
}