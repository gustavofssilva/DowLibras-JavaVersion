package util;

import java.sql.SQLException;

public class UsuarioNotFoundException extends SQLException {
	
	
	private static final long serialVersionUID = 2053520001545055990L;

	public UsuarioNotFoundException(String msg) {
		super(msg);
	}
	
	public static void main(String[] args) throws SQLException {
		throw new SQLException();
	}
	
    public String toString() {
        return "Usuário não encontrado";
    }

    public String getMessage() {
        return "Dados de Usuário incorretos";
    }
}
