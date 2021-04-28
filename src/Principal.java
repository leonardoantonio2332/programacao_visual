import java.util.List;
import java.util.Scanner;


public class Principal {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int opcao=0;
		while (opcao!=6)
		{
			System.out.println("1- Cadastrar um medico");
			System.out.println("2- Consultar um medico");
			System.out.println("3- Retornar os dados de todos os medicos cadastrados");
			System.out.println("4- Excluir um medico");
			System.out.println("5- Alterar os dados de um medico");
			System.out.println("6- Sair");
			System.out.println("Digite o numero da opcao escolhida");
			try
			{
				opcao=Integer.parseInt(teclado.nextLine());
				if (opcao==1)
				{
					Medico medico=new Medico();
					System.out.println("Informe o nome do medico:");
					medico.setNome(teclado.nextLine());
					System.out.println("Informe o CRM do medico:");
					medico.setCrm(teclado.nextLine());
					System.out.println("Informe a especialidade do medico:");
					medico.setEspecialidade(teclado.nextLine());
					MedicoDAO medicoDAO = new MedicoDAO();
					Medico cadastrado = medicoDAO.selecionarMedico(medico.getCrm());
					if (cadastrado != null)
						System.out.println("Ja existe um medico cadastrado com este CRM.");
					else
					{
						int retorno=medicoDAO.cadastrarMedico(medico);
						if (retorno == 1)
							System.out.println("Medico cadastrado com sucesso!");
						else
							System.out.println("Ocorreu um erro ao tentar cadastrar um Medico!");
					}
					System.out.println("Digite uma tecla qualquer para continuar:");
					teclado.nextLine();
				}else if (opcao==2)
				{
					System.out.println("Informe o CRM do medico que deseja pesquisar os dados:");
					String crm = teclado.nextLine();
					MedicoDAO medicoDAO = new MedicoDAO();
					Medico medico = medicoDAO.selecionarMedico(crm);
					if (medico != null)
					{
						System.out.println("Informacoes do medico:");
						System.out.println("Nome: " + medico.getNome());
						System.out.println("CRM: " + medico.getCrm());
						System.out.println("Especialidade: " + medico.getEspecialidade());
					}else
						System.out.println("Medico nao encontrado com esse CRM!");
					System.out.println("Digite uma tecla qualquer para continuar");
					teclado.nextLine();
				}else if (opcao==3)
				{
					MedicoDAO medicoDAO = new MedicoDAO();
					List<Medico> medicos = medicoDAO.selecionarTodosMedicos();
					if (!medicos.isEmpty())
					{
						for (Medico c:medicos)
						{
							System.out.println("______________________________");
							System.out.println("Nome: " + c.getNome());
							System.out.println("CRM: " + c.getCrm());
							System.out.println("Especialidade: " + c.getEspecialidade());
						}
					}else
						System.out.println("Nenhum medico cadastrado no momento!");
					System.out.println("Digite uma tecla qualquer para continuar");
					teclado.nextLine();
				}else if (opcao==4)
				{
					System.out.println("Informe o CRM do medico que deseja excluir");
					String crm = teclado.nextLine();
					MedicoDAO medicoDAO = new MedicoDAO();
					boolean medicoDeletado = medicoDAO.deletarMedico(crm);
					if (medicoDeletado == true)
						System.out.println("Medico deletado com sucesso!");
					else
						System.out.println("Nenhum medico encontrado com este CRM!");
					System.out.println("Digite uma tecla qualquer para continuar");
					teclado.nextLine();
				}else if (opcao==5)
				{
					Medico medico=new Medico();
					System.out.println("Informe o CRM do cliente que deseja alterar os dados");
					medico.setCrm(teclado.nextLine());
					System.out.println("Informe o nome correto do medico");
					medico.setNome(teclado.nextLine());
					System.out.println("Informe a especialidade correta do medico");
					medico.setEspecialidade(teclado.nextLine());
						MedicoDAO medicoDAO = new MedicoDAO();
						boolean medicoAtualizado = medicoDAO.atualizarMedico(medico);
						if (medicoAtualizado == true)
							System.out.println("Medico atualizado com sucesso!");
						else
							System.out.println("Nenhum Medico encontrado com este CRM!");
				}else if (opcao!=6)
					System.out.println("Opcao invalida");
				else
					teclado.close();
			}catch (Exception erro)
			{
				System.out.println("Opcao invalida");
				System.out.println("Digite uma tecla qualquer para continuar");
				teclado.nextLine();
			}
		}
	}
}