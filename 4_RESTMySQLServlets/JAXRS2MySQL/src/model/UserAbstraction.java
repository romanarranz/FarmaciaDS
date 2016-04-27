package model;

public interface UserAbstraction {
	public boolean login(String email, String password);
	public boolean sendResetMail();
	public boolean sendVerificationMail();
}
