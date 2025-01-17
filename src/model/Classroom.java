package model;

import java.time.LocalDate;
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
	
	public void createAccount(String un, String pw, String pp, String gender, String career, LocalDate bday,
			String browser) {
		UserAccount userAcc= new UserAccount(un, pw, pp, gender, career, bday, browser);
		accounts.add(userAcc);
		
	}
		
	public UserAccount searchAccount(String un, String pw) {
		boolean found=false;
		UserAccount ua=null;
		for(int i=0; i<accounts.size() && !found;i++ ) {
			if(accounts.get(i).getUserName().equals(un) && accounts.get(i).getPassword().equals(pw)) {
				ua=accounts.get(i);
				found=true;						
			}
		}
		return ua;
	}
	
	public boolean findAccount(String un, String pw) {
		UserAccount ua=searchAccount(un, pw);
		boolean found=false;
		if(ua!=null) {
			found=true;
		}
						
		return found;
	}
	
	public String profilePhotoResource(String un, String pw) {
		UserAccount ua=searchAccount(un, pw);
		
		return ua.getProfilePhoto();
	}

	

}
