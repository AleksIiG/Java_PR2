package dut.pd3.practics;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;



public class Main {
    public static void main(String[] args) {
        
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        // Visualized reports (Independent work)
        Map<String, Double> expensesByCategory = TransactionAnalyzer.getTotalExpensesByCategory(transactions);
        TransactionReportGenerator.printVisualReport(expensesByCategory, "Expenses by Category", 1000.0);

        Map<String, Double> expensesByMonth = TransactionAnalyzer.getTotalExpensesByMonth(transactions);
        TransactionReportGenerator.printVisualReport(expensesByMonth, "Expenses by Month", 1000.0);
    }
}