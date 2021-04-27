import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int opcao=0;
		while (opcao!=3)
		{
			System.out.println("1- Cadastrar um médico");
			System.out.println("2- Consultar um médico");
			System.out.println("3- Sair");
			System.out.println("Digite o número da opção escolhida:");
			opcao=Integer.parseInt(teclado.nextLine());
			if (opcao==1)
			{
				Medico medico=new Medico();
				System.out.println("Informe o nome do médico:");
				medico.setNome(teclado.nextLine());
				System.out.println("Informe o CRM do médico:");
				medico.setCrm(teclado.nextLine());
				System.out.println("Informe a especialidade do médico:");
				medico.setEspecialidade(teclado.nextLine());
				MedicoDAO medicoDAO = new MedicoDAO();
				Medico cadastrado = medicoDAO.selecionarMedico(medico.getCrm());
				if (cadastrado != null)
					System.out.println("Já existe um médico cadastrado com este CRM.");
				else
				{
					int retorno=medicoDAO.cadastrarMedico(medico);
					if (retorno == 1)
						System.out.println("Médico cadastrado com sucesso!");
					else
						System.out.println("Ocorreu um erro ao tentar cadastrar um Médico!");
				}
				System.out.println("Digite uma tecla qualquer para continuar:");
				teclado.nextLine();
			}else if (opcao==2)
			{
				System.out.println("Informe o CRM do médico que deseja pesquisar os dados:");
				String crm = teclado.nextLine();
				MedicoDAO medicoDAO = new MedicoDAO();
				Medico medico = medicoDAO.selecionarMedico(crm);
				if (medico != null)
				{
					System.out.println("Informações do médico:");
					System.out.println("Nome: " + medico.getNome());
					System.out.println("CRM: " + medico.getCrm());
					System.out.println("Especialidade: " + medico.getEspecialidade());
				}else
					System.out.println("Médico não encontrado com esse CRM!");
				System.out.println("Digite uma tecla qualquer para continuar");
				teclado.nextLine();
			}else if (opcao!=3)
				System.out.println("Opção inválida");
			else
				teclado.close();
		}
	}
}
