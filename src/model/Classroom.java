package model;

import java.util.ArrayList;

public class Classroom {
	
	private ArrayList<UserAccount> accounts;
	
	public Classroom() {
		setAccounts(new ArrayList<UserAccount>());
	}

	public ArrayList<UserAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<UserAccount> accounts) {
		this.accounts = accounts;
	}

}
