package dut.pd3.practics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private String date;
    private double amount;
    private String description;
}