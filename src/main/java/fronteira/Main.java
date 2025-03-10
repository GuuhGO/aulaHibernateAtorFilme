package fronteira;


import java.util.List;
import java.util.Scanner;

import controller.AtorController;
import controller.FilmeController;
import dao.AtorDao;
import model.Ator;
import model.Filme;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
		FilmeController fControl = new FilmeController();
		AtorController aControl = new AtorController();
		int escolha = 0;

		do {
			Filme f1 = new Filme();
			Ator a1 = new Ator();
			System.out.println("Menu:");
			System.out.println("1. Inserir Filme");
			System.out.println("2. Pequisar um Filme");
			System.out.println("3. Listar Todos");
			System.out.println("4. Atualizar um Filme");
			System.out.println("5. Apagar um Filme");
			System.out.println("=======================");
			System.out.println("6. Inserir Ator");
			System.out.println("7. Pequisar um Ator");
			System.out.println("8. Apagar um Ator");
			System.out.println("9. Atualizar um Ator");
			System.out.println("=======================");
			System.out.println("10. Participação Ator no Filme");
			System.out.println("11. Sair");

			System.out.print("Escolha uma opção: ");
			try {
				escolha = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("ERRO: Deve informar um número");
			}
			
			switch (escolha) {
				case 1:
					insertFilm(scanner, fControl, f1);
					break;
				case 2:
					searchFilm(scanner, fControl);
					break;
				case 3:
					listFilm(fControl);
					break;
				case 4:
					updateFilm(scanner, fControl);
					break;
				case 5:
					deleteFilm(scanner, fControl);
					break;	
				case 6:
					insertActor(scanner, fControl, aControl, a1);
					break;
				case 7:
					searchActor(scanner, aControl);
					break;
				case 8:
					deleteActor(scanner, aControl);
					break;
				case 9:
					updateActor(scanner, aControl, fControl);
					break;
				case 10:
					System.out.println("");
					break;
				case 11:
					System.out.println("Saindo do menu.");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
			if (escolha != 11) {
				System.out.println("Pressione ENTER para continuar");
				scanner.nextLine();
			}
		} while (escolha != 11);

		scanner.close();
	}

	private static void updateActor(Scanner scanner, AtorController aControl, FilmeController fControl) {
		List<Ator> searchActorResult = searchActor(scanner, aControl);
		System.out.print("Selecionar o item: ");
		int ocorrencia = Integer.parseInt(scanner.nextLine());
		ocorrencia--;
		Ator a1 = searchActorResult.get(ocorrencia);
		System.out.print("Informe o novo nome: ");
		String novoNome = scanner.nextLine();
		System.out.print("Informe o novo país: ");
		String novoPais= scanner.nextLine();
		a1.setNome(novoNome);
		a1.setPais(novoPais);
		
		List<Filme> searchFilmResult = searchFilm(scanner, fControl);
		System.out.print("Selecionar o item: ");
		ocorrencia = Integer.parseInt(scanner.nextLine());
		ocorrencia--;
		Filme f1 = searchFilmResult.get(ocorrencia);
		a1.setFilme(f1);
		
		aControl.atualizar(a1);
		System.out.println("Ator atualizado: " + a1);
	}

	private static void deleteActor(Scanner scanner, AtorController aControl) {
		List<Ator> searchActorResult = searchActor(scanner, aControl);
		System.out.print("Selecionar o item: ");
		int ocorrencia = Integer.parseInt(scanner.nextLine());
		ocorrencia--;
		Ator a1 = searchActorResult.get(ocorrencia);
		aControl.apagar(a1.getId());
		System.out.println("Ator excluído: " + a1);
	}

	private static List<Ator> searchActor(Scanner scanner, AtorController aControl) {
		System.out.print("Pesquisar um ator: ");
		String nomeAtor = scanner.nextLine();
		List<Ator> atores = aControl.pesquisarUmAtor(nomeAtor);
		if (!(atores.isEmpty())) {
			int i = 1;
			for (Ator ator : atores ) {
				System.out.println(i + " - ID: " + ator.getId() + " Nome: " + ator.getNome() + " País: " + ator.getPais());
				i++;
			}
		}
		else System.out.println("Não encontrou o ator");
		return atores;
	}

	private static void insertActor(Scanner scanner, FilmeController fControl, AtorController aControl, Ator a1) {
		System.out.print("Nome do Ator: ");
		String nomeAtor = scanner.nextLine();
		System.out.print("Nome do País: ");
		String nomePais = scanner.nextLine();
		a1.setNome(nomeAtor);
		a1.setPais(nomePais);
		
		List<Filme> resPesquisa = searchFilm(scanner, fControl);
		System.out.print("Selecionar o item: ");
		int ocorrencia = Integer.parseInt(scanner.nextLine());
		ocorrencia--;
		Filme filme = resPesquisa.get(ocorrencia);
		a1.setFilme(filme);
		aControl.inserirAtor(a1);
		System.out.println("Ator cadastrado: " + a1.toString());
	}

	private static void deleteFilm(Scanner scanner, FilmeController fControl) {
		List<Filme> searchFilmResult = searchFilm(scanner, fControl);
		System.out.print("Selecionar o item: ");
		int ocorrencia = Integer.parseInt(scanner.nextLine());
		ocorrencia--;
		Filme f1 = searchFilmResult.get(ocorrencia);
		fControl.apagar(f1.getId());
		System.out.println("Filme excluído: " + f1);
	}

	private static void updateFilm(Scanner scanner, FilmeController fControl) {
		List<Filme> searchFilmResult = searchFilm(scanner, fControl);
		System.out.print("Selecionar o item: ");
		int ocorrencia = Integer.parseInt(scanner.nextLine());
		ocorrencia--;
		Filme f1 = searchFilmResult.get(ocorrencia);
		System.out.print("Informe o novo título: ");
		String novoTitulo = scanner.nextLine();
		System.out.print("Informe o novo diretor: ");
		String novoNomeDiretor= scanner.nextLine();
		f1.setTitulo(novoTitulo);
		f1.setNomeDiretor(novoNomeDiretor);
		fControl.atualizar(f1);
		System.out.println("Filme atualizado: " + f1);
	}

	private static void listFilm(FilmeController fControl) {
		System.out.println("Listando Todos os Filmes");
		List<Filme> filmes = fControl.pesquisarTodos();
		if (!(filmes.isEmpty())) {
			for (Filme filme : filmes ) {
				System.out.println("Id: " + filme.getId() + " Nome: " + filme.getTitulo() + " Diretor: " + filme.getNomeDiretor());
			}
		}
		else System.out.println("Não existe filme cadastrado");
	}

	private static List<Filme> searchFilm(Scanner scanner, FilmeController fControl) {
		System.out.print("Pesquisar um filme: ");
		String nomeFilme = scanner.nextLine();
		List<Filme> filmes = fControl.pesquisarUmFilme(nomeFilme);
		if (!(filmes.isEmpty())) {
			int i = 1;
			for (Filme filme : filmes ) {
				System.out.println(i + " - ID: " + filme.getId() + " Nome: " + filme.getTitulo() + " Diretor: " + filme.getNomeDiretor());
				i++;
			}
		}
		else System.out.println("Não encontrou o Filme");
		return filmes;
	}

	private static void insertFilm(Scanner scanner, FilmeController fControl, Filme f1) {
		System.out.print("Nome filme: ");
		String nomeFilme = scanner.nextLine();
		System.out.print("Nome do diretor: ");
		String nomeDiretor = scanner.nextLine();
		f1.setTitulo(nomeFilme);
		f1.setNomeDiretor(nomeDiretor);
		fControl.inserirFilme(f1);
		System.out.println(f1);
	}
}


