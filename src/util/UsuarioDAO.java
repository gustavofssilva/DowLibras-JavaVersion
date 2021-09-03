package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import entities.Usuario;

public class UsuarioDAO implements DAO<Usuario>{
	
	private PreparedStatement stmt;
	
	private ResultSet rs;
	
	private Usuario usuario;
	
	@Override
	public boolean insere(Usuario obj) {
        String sql = "INSERT INTO usuario (id_usuario, nome_usuario, senha_usuario) "
                + "VALUES (?, ?, ?)"; //o ? indica um parametro
        int res = 0;
        //abre a conexao como banco
        try {
			Banco.conectar();
        //prepara o comando pst
        stmt = Banco.obterConexao().prepareStatement(sql);
        //associa os dados do objeto Aluno com o comando INSERT
        stmt.setInt(1, obj.getIdUsuario());
        stmt.setString(2, obj.getNomeUsuario());
        stmt.setString(3, obj.getSenhaUsuario());
        //executa o comando
        res = stmt.executeUpdate(); //atualiza o banco (usado p/ insert,delete e update)
        System.out.println("Oi");
        //fecha o banco
        Banco.desconectar();
        	
		} catch (UsuarioDataNotEnoughException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(res);
        return res != 0;
	}

	@Override
	public boolean remove(Usuario obj) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ? "; //o ? indica um parametro
        int res = 0;
        //abre a conexao como banco
        try {
			Banco.conectar();
        //prepara o comando pst
        stmt = Banco.obterConexao().prepareStatement(sql);
        //associa os dados do objeto Genero com o comando DELETE
        stmt.setInt(1, obj.getIdUsuario());
        //executa o comando
        res = stmt.executeUpdate();
        //fecha o banco
        Banco.desconectar();
		} catch (UsuarioNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return res != 0;
	}

	@Override
	public boolean altera(Usuario obj) {
        String sql = "UPDATE usuario SET senha_usuario = ? WHERE id_usuario = ? "; 
        //o ? indica um parametro
        int res = 0;
        //abre a conexao como banco
        try {
			Banco.conectar();
        //prepara o comando pst
        stmt = Banco.obterConexao().prepareStatement(sql);
        stmt.setString(1, obj.getSenhaUsuario());
        stmt.setInt(2, obj.getIdUsuario());
        //executa o comando
        res = stmt.executeUpdate();
        //fecha o banco
        Banco.desconectar();
		} catch (UsuarioNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return res != 0;
	}

	@Override
	public Usuario buscaID(Usuario obj) {
        String sql = "SELECT * FROM usuario "
                + "WHERE id_usuario = ?"; //o ? indica um parametro
        
        //abre a conexao como banco
        try {
			Banco.conectar();
		
        //prepara o comando pst
        stmt = Banco.obterConexao().prepareStatement(sql);
        //associa os dados do objeto Genero com o comando SELECT
        stmt.setInt(1, obj.getIdUsuario());
        //executa o comando
        rs = stmt.executeQuery();
        //verificar se trouxe algum registro
        //rs.next() faz a leitura do próximo registro, se existir devolve true
        //se nao devolve false
        if(rs.next()) {
            //instancia o objeto para retorno dos dados
            usuario = new Usuario();
            //jogar os dados do banco para o objeto
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setNomeUsuario(rs.getString("nome_usuario"));
            usuario.setSenhaUsuario(rs.getString("senha_usuario"));
        } 
        else {
            //não encontrou nada!!!
            usuario = null;
        }
        //fecha o banco
        Banco.desconectar();
		} catch (UsuarioNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //devolve o objeto com os dados do banco ou não
        return usuario;
	}

	@Override
	public Collection<Usuario> lista(String criterio) {
        //cria a coleção para os dados
        Collection<Usuario> retorno = new ArrayList<>();
        
        String sql = "SELECT * FROM usuario ";
        
        //verifica se tem filtro a fazer
        if(criterio.length() > 0) {
            sql += "WHERE " + criterio;
        }
    
        //abre a conexao como banco
        try {
			Banco.conectar();
        //prepara o comando pst
        stmt = Banco.obterConexao().prepareStatement(sql);
        //executa o comando
        rs = stmt.executeQuery();
        //verificar se trouxe algum registro
        //rs.next() faz a leitura do próximo registro, se existir devolve true
        //se nao devolve false
        //enquanto existir registros na tabela, vai lendo até o fim
        while(rs.next()) {
            //instancia o objeto para retorno dos dados
            usuario = new Usuario();
            //jogar os dados do banco para o objeto
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setNomeUsuario(rs.getString("nome_usuario"));
            usuario.setSenhaUsuario(rs.getString("senha_usuario"));
            
            //adiciona o registro dentro da Coleção
            retorno.add(usuario);
        } 
        //fecha o banco
        Banco.desconectar();
		} catch (UsuarioNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //devolve a lista com todos os registros
        return retorno;
	}

}
