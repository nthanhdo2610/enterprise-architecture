package cs544.bank.service;

import cs544.bank.dao.IAccountDAO;
import cs544.bank.dao.JPAAccountDAO;
import cs544.bank.domain.Account;
import cs544.bank.domain.Customer;
import cs544.bank.helper.EntityManagerHelper;
import cs544.bank.jms.IJMSSender;
import cs544.bank.jms.JMSSender;
import cs544.bank.logging.ILogger;
import cs544.bank.logging.Logger;
import jakarta.persistence.EntityManager;

import java.util.Collection;


public class AccountService implements IAccountService {
    private final IAccountDAO accountDAO;
    private final ICurrencyConverter currencyConverter;
    private final IJMSSender jmsSender;
    private final ILogger logger;

    public AccountService() {
        accountDAO = new JPAAccountDAO();
        currencyConverter = new CurrencyConverter();
        jmsSender = new JMSSender();
        logger = new Logger();
    }

    public Account createAccount(long accountNumber, String customerName) {
        EntityManager em = EntityManagerHelper.getCurrent();
        em.getTransaction().begin();
        Account account = new Account(accountNumber);
        Customer customer = new Customer(customerName);
        account.setCustomer(customer);
        accountDAO.saveAccount(account);
        logger.log("createAccount with parameters accountNumber= " + accountNumber + " , customerName= " + customerName);
        em.getTransaction().commit();
        return account;
    }

    public void deposit(long accountNumber, double amount) {
        EntityManager em = EntityManagerHelper.getCurrent();
        em.getTransaction().begin();
        Account account = accountDAO.loadAccount(accountNumber);
        account.deposit(amount);
        accountDAO.updateAccount(account);
        logger.log("deposit with parameters accountNumber= " + accountNumber + " , amount= " + amount);
        if (amount > 10000) {
            jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
        }
        em.getTransaction().commit();
    }

    public Account getAccount(long accountNumber) {
        return accountDAO.loadAccount(accountNumber);
    }

    public Collection<Account> getAllAccounts() {
        return accountDAO.getAccounts();
    }

    public void withdraw(long accountNumber, double amount) {
        EntityManager em = EntityManagerHelper.getCurrent();
        em.getTransaction().begin();
        Account account = accountDAO.loadAccount(accountNumber);
        account.withdraw(amount);
        accountDAO.updateAccount(account);
        logger.log("withdraw with parameters accountNumber= " + accountNumber + " , amount= " + amount);
        em.getTransaction().commit();
    }

    public void depositEuros(long accountNumber, double amount) {
        EntityManager em = EntityManagerHelper.getCurrent();
        em.getTransaction().begin();
        Account account = accountDAO.loadAccount(accountNumber);
        double amountDollars = currencyConverter.euroToDollars(amount);
        account.deposit(amountDollars);
        accountDAO.updateAccount(account);
        logger.log("depositEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
        if (amountDollars > 10000) {
            jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
        }
        em.getTransaction().commit();
    }

    public void withdrawEuros(long accountNumber, double amount) {
        EntityManager em = EntityManagerHelper.getCurrent();
        em.getTransaction().begin();
        Account account = accountDAO.loadAccount(accountNumber);
        double amountDollars = currencyConverter.euroToDollars(amount);
        account.withdraw(amountDollars);
        accountDAO.updateAccount(account);
        logger.log("withdrawEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
        em.getTransaction().commit();
    }

    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
        EntityManager em = EntityManagerHelper.getCurrent();
        em.getTransaction().begin();
        Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
        Account toAccount = accountDAO.loadAccount(toAccountNumber);
        fromAccount.transferFunds(toAccount, amount, description);
        accountDAO.updateAccount(fromAccount);
        accountDAO.updateAccount(toAccount);
        logger.log("transferFunds with parameters fromAccountNumber= " + fromAccountNumber + " , toAccountNumber= " + toAccountNumber + " , amount= " + amount + " , description= " + description);
        if (amount > 10000) {
            jmsSender.sendJMSMessage("TransferFunds of $ " + amount + " from account with accountNumber= " + fromAccount + " to account with accountNumber= " + toAccount);
        }
        em.getTransaction().commit();
    }
}
