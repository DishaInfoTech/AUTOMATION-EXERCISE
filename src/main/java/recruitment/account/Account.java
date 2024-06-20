package recruitment.account;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The Account class manages a bank account's state, including balance and transaction history.
 * It supports deposit and withdrawal operations, updating the balance and recording each transaction.
 * It can produce a formatted statement of the account's transactions and current balance.
 * The Transaction class is used internally by Account to encapsulate details of individual transactions.
 */

public class Account {

    //instance variables
    private String accountNumber; //A string representing the unique account number.
    private String accountHolderName; //A string representing the name of the account holder.
    private double balance; //A double representing the current balance of the account.
    private List<Transaction> transactions; //A list of Transaction objects representing the transaction history.

    //The constructor initializes the account with an account number, account holder name, a balance of 0, and an empty list of transactions.
    public Account(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    //The deposit method with description and an amount as parameters.
    public void deposit(String description, double amount) {
        transactions.add(new Transaction(description, amount)); //Creates a new Transaction object with the description and amount.
        //Adds the transaction to the transactions list.
        //Increases the balance by the deposit amount.
        balance += amount;
    }

    //The with draw method with description and an amount as parameters.
    public void withdraw(String description, double amount) {
        transactions.add(new Transaction(description, -amount)); //Creates a new Transaction object with the description and a negative amount (to indicate a withdrawal).
        //Adds the transaction to the transactions list.
        //Decreases the balance by the withdrawal amount.
        balance -= amount;
    }

    //The produceStatement method:
    public String produceStatement() {
        StringBuilder statement = new StringBuilder();  //Creates a StringBuilder to construct the statement.
        DecimalFormat df = new DecimalFormat("0.00"); //Uses DecimalFormat to format the balance and transaction amounts to two decimal places.

        //Appends the account holder's name and account number to the statement.
        statement.append("Name: ").append(accountHolderName).append("\n");
        statement.append("Account: ").append(accountNumber).append("\n");
        statement.append("Transactions:\n");

        //Iterates over the transactions list and appends each transaction's description and amount to the statement.
        for (Transaction transaction : transactions) {
            statement.append(transaction.getDescription())
                    .append(": ")
                    .append(df.format(transaction.getAmount()))
                    .append("\n");
        }
        statement.append("Balance: ").append(df.format(balance)).append("\n"); //Appends the current balance to the statement.

        return statement.toString(); //Returns the complete statement as a string.
    }

    //The Transaction class represents a single transaction with:
    private static class Transaction {
        private String description;  //description: A string describing the transaction.
        private double amount; //amount: A double representing the transaction amount (positive for deposits, negative for withdrawals).

        public Transaction(String description, double amount) {
            this.description = description;
            this.amount = amount;
        }

        public String getDescription() {
            return description;
        }

        public double getAmount() {
            return amount;
        }
    }
}
