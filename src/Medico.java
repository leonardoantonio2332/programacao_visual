public class Medico {
	private String nome,crm,especialidade;
	public Medico(String nome,String crm, String especialidade)
	{
		this.nome=nome;
		this.crm=crm;
		this.especialidade=especialidade;
	}
	public Medico()
	{
		
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
}