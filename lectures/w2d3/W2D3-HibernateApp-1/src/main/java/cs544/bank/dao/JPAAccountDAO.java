package cs544.bank.dao;

import java.util.*;

import cs544.bank.domain.Account;
import cs544.bank.helper.EntityManagerHelper;
import jakarta.persistence.EntityManager;

public class JPAAccountDAO implements IAccountDAO {

	public void saveAccount(Account account) {
		EntityManager em = EntityManagerHelper.getCurrent();
		// System.out.println("AccountDAO: saving account with accountnr ="+account.getAccountnumber());
		em.persist(account); // add the new
	}

	public void updateAccount(Account account) {
		EntityManager em = EntityManagerHelper.getCurrent();
		// System.out.println("AccountDAO: update account with accountnr ="+account.getAccountnumber());
		Account accountexist = loadAccount(account.getAccountnumber());
		if (accountexist != null) {
			em.remove(accountexist); // remove the old
			em.persist(account); // add the new
		}

	}

	public Account loadAccount(long accountnumber) {
		// System.out.println("AccountDAO: loading account with accountnr ="+accountnumber);
		for (Account account : getAccounts()) {
			if (account.getAccountnumber() == accountnumber) {
				return account;
			}
		}
		return null;
	}

	public Collection<Account> getAccounts() {
		EntityManager em = EntityManagerHelper.getCurrent();
		return em.createQuery("From Account", Account.class).getResultList();
	}

}
