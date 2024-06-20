package recruitment.glue;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import recruitment.account.Account;

import static org.junit.Assert.assertTrue;

/**
 * The AccountSteps class you've provided is written in the Cucumber BDD (Behavior-Driven Development) style
 * using Cucumber's annotations (@Given, @And, @When, @Then).
 * This class defines steps that interact with an Account class to simulate behavior and verify statements.
 */

public class AccountSteps {
    private Account account;
    private String statement;

    // Step: Account creation
    @Given("Account exists for Acc No. {string} with Name {string}")
    public void accountExistsForAccNoWithName(String accountNumber, String accountHolderName) {
        account = new Account(accountNumber, accountHolderName);
    }

    // Step: Deposits made
    @And("deposits are made")
    public void depositsAreMade(DataTable dataTable) {
        dataTable.asLists().forEach(row -> {
            String description = row.get(0);
            double amount = Double.parseDouble(row.get(1));
            account.deposit(description, amount);
        });
    }

    // Step: Withdrawals made
    @And("withDrawls are made")
    public void withDrawlsAreMade(DataTable dataTable) {
        dataTable.asLists().forEach(row -> {
            String description = row.get(0);
            double amount = Double.parseDouble(row.get(1));
            account.withdraw(description, amount);
        });
    }

    // Step: Produce statement
    @When("statement is produced")
    public void statementIsProduced() {
        statement = account.produceStatement();
    }

    // Step: Verify statement content
    @Then("statement includes {string}")
    public void statementIncludes(String expectedContent) {
        assertTrue(statement.contains(expectedContent));
    }
}
