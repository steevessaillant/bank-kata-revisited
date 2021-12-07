package com.alithya.qa.craftsmanship.katas.london.tdd.acceptance.stepDefinitions;

import com.alithya.qa.craftsmanship.katas.london.tdd.Account;
import com.alithya.qa.craftsmanship.katas.london.tdd.Amount;
import com.alithya.qa.craftsmanship.katas.london.tdd.Statement;
import com.alithya.qa.craftsmanship.katas.london.tdd.TransactionRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.alithya.qa.craftsmanship.katas.london.tdd.Amount.amountOf;
public class BankKataStepDefs {

    @Mock
    private PrintStream mockPrintStream;
    private Account sut;

    //private methods
    @SneakyThrows
    private void createNewDeposit(Integer amount, Integer day, Integer month, Integer year) {
        DateFormat DateFormatter
                = new SimpleDateFormat("dd/MM/yyyy");
        Date depositDate = DateFormatter.parse( day.toString() + "/" + month.toString() + "/" + year.toString());
        Amount depositAmount = amountOf(amount);
        this.sut.deposit(depositAmount,depositDate);
    }
    @SneakyThrows
    private void createNewWithdrawal(Integer amount, Integer day, Integer month, Integer year) {
        DateFormat DateFormatter
                = new SimpleDateFormat("dd/MM/yyyy");
        Date withdrawalDate = DateFormatter.parse( day.toString() + "/" + month.toString() + "/" + year.toString());
        Amount withdrawalAmount = amountOf(amount);
        this.sut.withdrawal(withdrawalAmount,withdrawalDate);
    }

    //public methods
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        TransactionRepository transactionRepository = new TransactionRepository();
        Statement statement = new Statement();
        sut = new Account("",statement,transactionRepository);
    }

    @Given("{string} makes a deposit of {int} on {int}-{int}-{int}")
    public void makes_a_deposit_of_on(String customerName, Integer amount, Integer day, Integer month, Integer year) {
        this.sut.accountID = customerName;
        createNewDeposit(amount, day, month, year);
    }



    @And("a deposit of {int} on {int}-{int}-{int}")
    public void a_deposit_of_on(Integer amount, Integer day, Integer month, Integer year) {
        createNewDeposit(amount, day, month, year);
    }
    @And("a withdrawal of {int} on {int}-{int}-{int}")
    public void a_withdrawal_of_on(Integer amount, Integer day, Integer month, Integer year) {
        createNewWithdrawal(amount, day, month, year);
    }



    @When("he prints his bank statement")
    public void he_prints_his_bank_statement() {
        this.sut.printStatement(mockPrintStream);
    }
    @Then("he would see")
    public void he_would_see(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<Map<String, String>> mappedList = dataTable.asMaps(String.class, String.class);

        InOrder inOrder = Mockito.inOrder(mockPrintStream);
        inOrder.verify(mockPrintStream).println("| date       | credit  | debit    | balance |");
        inOrder.verify(mockPrintStream).println("| 14/01/2012 |         | 500.00 | 2500.00 |");
        //inOrder.verify(mockPrintStream).println("| 13/01/2012 | 2000.00 |        | 3000.00 |");
        //inOrder.verify(mockPrintStream).println("| 10/01/2012 | 1000.00 |        | 1000.00 |");


    }

}
