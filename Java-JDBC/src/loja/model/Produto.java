package loja.model;

public class Produto {
	Integer id;
	String nome;
	String desc;

	public Produto(String nome, String desc) {
		this.nome = nome;
		this.desc = desc;
	}

	public String getNome() {
		return nome;
	}

	public String getDesc() {
		return desc;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
