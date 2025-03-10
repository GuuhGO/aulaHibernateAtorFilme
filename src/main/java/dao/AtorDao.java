package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Ator;

public class AtorDao implements IAtorDao {
	EntityManagerFactory mf = Persistence.createEntityManagerFactory("HibJPA");

	@Override
	public Ator pesquisar(String ator) {
		return null;
	}

	@Override
	public void inserir(Ator ator) {
		EntityManager em = mf.createEntityManager();
		em.getTransaction().begin();
		em.persist(ator);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void remover(int id) {
		EntityManager em = mf.createEntityManager();
		em.getTransaction().begin();
		Ator ator = em.find(Ator.class, id);
		if (ator != null) {
			em.remove(ator);
		}
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Ator> lista() {
		EntityManager em = mf.createEntityManager();
		List<Ator> atores= em.createQuery("SELECT a from Ator a", Ator.class).getResultList();
		em.close();
		return atores;
	}

	@Override
	public List<Ator> apenasUmAtor(String nomeAtor) {
		EntityManager em = mf.createEntityManager();
		List<Ator> atores= em.createQuery("SELECT a from Ator a WHERE a.nome LIKE :nomeAtor", Ator.class)
				.setParameter("nomeAtor", "%" + nomeAtor + "%")
				.getResultList();
		em.close();
		return atores;
	}

	@Override
	public void atualizar(Ator ator) {
		EntityManager em = mf.createEntityManager();
		em.getTransaction().begin();
		em.merge(ator);
		em.getTransaction().commit();
		em.close();
	}
	
	

}
