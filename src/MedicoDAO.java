import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {
//	private String DRIVER = "org.postgresql.Driver";
	private String urlBanco = "jdbc:postgresql://motty.db.elephantsql.com:5432/jiknpecj"; 
	private String usuarioBanco = "jiknpecj"; 
	private String senhaBanco = "WrUJLat69yzU9Kuq-lYhWL1oJ_G_cGxT"; 
	private Connection getConnection() {
		Connection connection = null;
		try
		{
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(urlBanco,usuarioBanco,senhaBanco);
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return connection;
	}
	protected int cadastrarMedico(Medico medico)
	{
		int cadastrado = 0;
		try
		{
			Connection conexao = getConnection();
			if (conexao != null)
			{
				PreparedStatement cadastrar = conexao.prepareStatement("INSERT INTO medico(nome,crm,especialidade) VALUES (?,?,?)");
				cadastrar.setString(1,medico.getNome());
				cadastrar.setString(2,medico.getCrm());
				cadastrar.setString(3,medico.getEspecialidade());
				cadastrar.executeUpdate();
				cadastrado = 1;
				cadastrar.close();
				conexao.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return cadastrado;
	}
	protected Medico selecionarMedico(String crm) {
		Medico medico=null;
		try
		{
			Connection conexao = getConnection();
			if (conexao != null)
			{
				PreparedStatement consultar = conexao.prepareStatement("SELECT * FROM MEDICO WHERE CRM = '" + crm + "'");
				ResultSet rs = consultar.executeQuery();
				while(rs.next())
				{
					String nome = rs.getString("nome");
					String especialidade = rs.getString("especialidade");
					medico = new Medico(nome,crm,especialidade);
				}
				rs.close();
				consultar.close();
				conexao.close();
			}
		}catch (SQLException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return medico;
	}

	protected List<Medico> selecionarTodosMedicos() {
		List<Medico> medicos = new ArrayList<>();
		try
		{
			Connection conexao = getConnection();
			if (conexao != null)
			{
				PreparedStatement consultarMedicos = conexao.prepareStatement("SELECT * FROM medico");
				ResultSet rs = consultarMedicos.executeQuery();
				while (rs.next()) 
				{
					String nome = rs.getString("nome");
					String cpf = rs.getString("crm");
					String especialidade = rs.getString("especialidade");
					medicos.add(new Medico(nome,cpf,especialidade));
				}	
				rs.close();
				consultarMedicos.close();
				conexao.close();
			}
		}catch (SQLException e)
		{
			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return medicos;
	}
	protected boolean deletarMedico(String crm)
	{
		boolean medicoDeletado=false;
		try
		{
			Connection conexao = getConnection();
			if (conexao != null)
			{
				PreparedStatement deletar = conexao.prepareStatement("DELETE FROM medico WHERE crm = '" + crm + "'");
				medicoDeletado = deletar.executeUpdate() > 0;
				deletar.close();
				conexao.close();
			}
		}catch (SQLException e)
		{
			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return medicoDeletado;
	}
	protected boolean atualizarMedico(Medico medico)
	{
		boolean medicoAtualizado=false;
		try
		{
			Connection conexao = getConnection();
			if (conexao != null)
			{
				PreparedStatement atualizar = conexao.prepareStatement("UPDATE medico set nome=?,crm=?,especialidade=?");
				atualizar.setString(1,medico.getNome());
				atualizar.setString(2,medico.getCrm());
				atualizar.setString(3,medico.getEspecialidade());
				medicoAtualizado = atualizar.executeUpdate() > 0;
				atualizar.close();
				conexao.close();
			}
		}catch (SQLException e)
		{
			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return medicoAtualizado;
	}
}
