package util;

import java.sql.SQLException;

public class UsuarioDataNotEnoughException extends SQLException{
	
	private static final long serialVersionUID = 4878763079435232286L;

	public UsuarioDataNotEnoughException(String msg) {
		super(msg);
	}
	
	public static void main(String[] args) throws SQLException {
		throw new SQLException();
	}
	
    public String toString() {
        return "O Usuario não pôde ser criado";
    }

    public String getMessage() {
        return "Dados insuficientes";
    }
}
