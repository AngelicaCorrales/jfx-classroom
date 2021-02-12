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
	
	public boolean findAccount(String un, String pw) {
		boolean found=false;
		for(int i=0; i<accounts.size() && !found;i++ ) {
			if(accounts.get(i).getUserName().equals(un) && accounts.get(i).getPassword().equals(pw)) {
				
				found=true;						
			}
		}
		
				
		return found;
	}

}
