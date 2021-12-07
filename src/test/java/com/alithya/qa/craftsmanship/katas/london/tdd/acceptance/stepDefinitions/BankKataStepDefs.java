package com.alithya.qa.craftsmanship.katas.london.tdd.acceptance.stepDefinitions;

import com.alithya.qa.craftsmanship.katas.london.tdd.unit.domain.Account;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.mockito.Mock;

import java.io.PrintStream;
public class BankKataStepDefs {

    @Mock
    private PrintStream mockPrintStream;
    private Account sut;

    //private methods
    @SneakyThrows
    private void createNewDeposit(Integer amount, Integer day, Integer month, Integer year) {
        throw new PendingException();
    }
    @SneakyThrows
    private void createNewWithdrawal(Integer amount, Integer day, Integer month, Integer year) {
        throw new PendingException();
    }

    //public methods
    @Before
    public void setup(){
        throw new PendingException();
    }

    @Given("{string} makes a deposit of {int} on {int}-{int}-{int}")
    public void makes_a_deposit_of_on(String customerName, Integer amount, Integer day, Integer month, Integer year) {
        throw new PendingException();
    }



    @And("a deposit of {int} on {int}-{int}-{int}")
    public void a_deposit_of_on(Integer amount, Integer day, Integer month, Integer year) {
        throw new PendingException();
    }
    @And("a withdrawal of {int} on {int}-{int}-{int}")
    public void a_withdrawal_of_on(Integer amount, Integer day, Integer month, Integer year) {
        throw new PendingException();
    }



    @When("he prints his bank statement")
    public void he_prints_his_bank_statement() {

        throw new PendingException();
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
        throw new PendingException();

    }

}
