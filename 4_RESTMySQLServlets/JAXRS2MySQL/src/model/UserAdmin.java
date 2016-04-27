package model;

public class UserAdmin implements UserImplementator {

	@Override
	public boolean login(String email, String password) {
		// comprobar email y contraseña con la bd, ademas comprobar que su campo de user admin esta a 1
		return false;
	}

	@Override
	public boolean sendResetMail() {
		// enviar un mail a su email indicandole que no debe de olvidarse de esta contraseña NUNCA MAS
		return false;
	}

	@Override
	public boolean sendVerificationMail() {
		// enviar un mail a su correo indicandole ademas la direccion de gestion del servicio web
		return false;
	}

}
