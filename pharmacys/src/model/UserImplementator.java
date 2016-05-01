package model;

public interface UserImplementator {
	public abstract boolean login(String email, String password);
	public abstract boolean sendResetMail();
	public abstract boolean sendVerificationMail();
}
