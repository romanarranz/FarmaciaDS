package model;

public class UserNormal implements UserImplementator{

	@Override
	public boolean login(String email, String password) {
		// comprobar que exista el email y la contraseña proporcionada sea la correcta
		return false;
	}

	@Override
	public boolean sendResetMail() {
		// enviar un mail generando un nuevo hash de reseteo a su email, comprobar el hash de la url con el de la bd
		return false;
	}

	@Override
	public boolean sendVerificationMail() {
		// enviar un mail a su email para que cuando acepte se active su cuenta
		return false;
	}

}
