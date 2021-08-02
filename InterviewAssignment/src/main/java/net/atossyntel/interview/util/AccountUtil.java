package net.atossyntel.interview.util;

import org.springframework.stereotype.Component;

import net.atossyntel.interview.dao.AccountDao;
import net.atossyntel.interview.model.Account;

@Component
public class AccountUtil {

	public static Account getAccountForId(int accountId) {
		// TODO Auto-generated method stub
		return AccountDao.getAccountList().stream().filter(acc -> acc.getId() == accountId).findFirst().get();

	}

	public static boolean isAccountIDExisting(int id) {
		// TODO Auto-generated method stub
		return AccountDao.getAccountList().stream().anyMatch(acc -> acc.getId() == id);
	}

}
