import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicoDAO {
	private String urlBanco = "jdbc:postgresql://localhost:5432/programacao_visual"; //indica o caminho do banco de dados
	private String usuarioBanco = "postgres"; // aqui vai o nome usuario que vc quer acessar
	private String senhaBanco = "docker"; // aqui a senha do seu banco
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
}