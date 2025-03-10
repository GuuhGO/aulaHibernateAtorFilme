package dao;

import java.util.List;

import model.Filme;

public interface IFilmeDao {
	public void inserir(Filme filme);
	public void remover(long id);
	public void atualizar(Filme filme);
	public List<Filme> lista(); 
	public List<Filme> apenasUmFilme (String titulo);
}
