package dut.pd3.practics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() {
        Transaction t1 = new Transaction("01-01-2023", 100.0, "Income");
        Transaction t2 = new Transaction("02-01-2023", -50.0, "Expense");
        Transaction t3 = new Transaction("03-01-2023", 150.0, "Income");
        List<Transaction> transactions = Arrays.asList(t1, t2, t3);

        double result = TransactionAnalyzer.calculateTotalBalance(transactions);
        Assertions.assertEquals(200.0, result, "Total balance calculation is incorrect");
    }

    @Test
    public void testCountTransactionsByMonth() {
        Transaction t1 = new Transaction("01-02-2023", 50.0, "Income");
        Transaction t2 = new Transaction("15-02-2023", -20.0, "Expense");
        Transaction t3 = new Transaction("05-03-2023", 100.0, "Income");
        List<Transaction> transactions = Arrays.asList(t1, t2, t3);

        int countFeb = TransactionAnalyzer.countTransactionsByMonth(transactions, "02-2023");
        int countMar = TransactionAnalyzer.countTransactionsByMonth(transactions, "03-2023");

        Assertions.assertEquals(2, countFeb, "Transaction count for February is incorrect");
        Assertions.assertEquals(1, countMar, "Transaction count for March is incorrect");
    }

    @Test
    public void testCSVReaderAndTopExpenses() {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);
        
        Assertions.assertNotNull(transactions);
        Assertions.assertFalse(transactions.isEmpty(), "Transactions list should not be empty");

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        Assertions.assertTrue(topExpenses.size() <= 10, "Number of top expenses should not exceed 10");
    }
}