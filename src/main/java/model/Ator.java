package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Ator {
	@Id
	@GeneratedValue
	private int id;
	private String nome;
	private String pais;
	@OneToOne
	@JoinColumn(name="filme_id")
	private Filme filme;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public Filme getFilme() {
		return filme;
	}
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	@Override
	public String toString() {
		return "Ator: " + nome + " | País: " + pais + " | Filme: " + filme;
	}
	
	
}
