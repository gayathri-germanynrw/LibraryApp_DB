package com.cydeo.steps;

import com.cydeo.pages.DashBoardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class DashboardStepDefs
{
    String actualUserNumbers;
    String actualBookNumbers;
    String actualBorrowedBookNumbers;
    LoginPage loginPage=new LoginPage();
    DashBoardPage dashBoardPage=new DashBoardPage();


    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String user) {
        loginPage.login(user);
         BrowserUtil.waitFor(4);
    }
    @When("user gets all information from modules")
    public void user_gets_all_information_from_modules() {

        actualUserNumbers = dashBoardPage.usersNumber.getText();
        System.out.println("actualUserNumbers = " + actualUserNumbers);
        actualBookNumbers = dashBoardPage.booksNumber.getText();
        System.out.println("actualBookNumbers = " + actualBookNumbers);
        actualBorrowedBookNumbers = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("actualBorrowedBookNumbers = " + actualBorrowedBookNumbers);

    }

    @Then("the information should be same with database")
    public void the_information_should_be_same_with_database() {
        // This scenario Database result is expected

        // Connect the same database (library2)
      //  DB_Util.createConnection(); // Connection created by @db hooks

        //run the query for each of the result (3 differen queries )

        //BOOKS
         DB_Util.runQuery("select count(*) from books");
        String expectedBookNumbers = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedBookNumbers ---> = " + expectedBookNumbers);
        System.out.println("expectedBookNumbers = ---> =" + expectedBookNumbers);


        // compare results with UI results(Actual results)
        Assert.assertEquals(actualBookNumbers,expectedBookNumbers);


        // Actual Users
        DB_Util.runQuery("select count(*) from users");
        String expectedUserNumbers=DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedUserNumbers = inside user " + expectedUserNumbers);
        System.out.println("actualUserNumbers = inside user  " + actualUserNumbers);
        Assert.assertEquals(actualUserNumbers,expectedUserNumbers);

        // Borrowed Books
        DB_Util.runQuery("select count(*) from book_borrow\n" +
                "where is_returned=0");
        String expectedBorrowedBookNumbers=DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedBorrowedBookNumbers = inside borrowed " + expectedBorrowedBookNumbers);
        System.out.println("actualBorrowedBookNumbers = inside borrowed  " + actualBorrowedBookNumbers);
        Assert.assertEquals(actualBorrowedBookNumbers,expectedBorrowedBookNumbers);





        //close connection
        // DB_Util.destroy(); // Connection closed by @db hooks


    }
}

