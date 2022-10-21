package com.douglas;

import com.douglas.model.Ator;
import com.douglas.model.Classe;
import com.douglas.model.Cliente;
import com.douglas.model.Diretor;
import com.douglas.model.Item;
import com.douglas.model.Socio;
import com.douglas.model.Titulo;
import com.douglas.repository.AtorRepository;
import com.douglas.repository.ClasseRepository;
import com.douglas.repository.ClienteRepository;
import com.douglas.repository.DiretorRepository;
import com.douglas.repository.ItemRepository;
import com.douglas.repository.SocioRepository;
import com.douglas.repository.TituloRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProjetoLocadoraBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoLocadoraBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(AtorRepository atorRepository,
								   DiretorRepository diretorRepository,
								   ClasseRepository classeRepository,
								   TituloRepository tituloRepository,
								   ItemRepository itemRepository,
								   ClienteRepository clienteRepository,
								   SocioRepository socioRepository) {
		return args -> {

			//--------------------------------------------------------
			//Ator

			Ator ator1 = new Ator();
			ator1.setNome("João Felipe");

			Ator ator2 = new Ator();
			ator2.setNome("Douglas Almeida");

			Ator ator3 = new Ator();
			ator3.setNome("Josué Lobo");

			Ator ator4 = new Ator();
			ator4.setNome("Layla Maria");

			Ator ator5 = new Ator();
			ator5.setNome("Edson Lima");


			//--------------------------------------------------------
			//Diretor

			Diretor diretor1 = new Diretor();
			diretor1.setNome("Clarisse Diniz");

			Diretor diretor2 = new Diretor();
			diretor2.setNome("Filipe Mattos");

			Diretor diretor3 = new Diretor();
			diretor3.setNome("Matheus Martins");

			Diretor diretor4 = new Diretor();
			diretor4.setNome("João Kleber");

			Diretor diretor5 = new Diretor();
			diretor5.setNome("Isabella Corrêa");


			//--------------------------------------------------------
			//Classes

			Classe classe1 = new Classe();
			classe1.setNome("Bronze");
			classe1.setData(LocalDate.now());
			classe1.setValor(15);

			Classe classe2 = new Classe();
			classe2.setNome("Ferro");
			classe2.setData(LocalDate.now());
			classe2.setValor(20);

			Classe classe3 = new Classe();
			classe3.setNome("Ouro");
			classe3.setData(LocalDate.now());
			classe3.setValor(25);

			Classe classe4 = new Classe();
			classe4.setNome("Platina");
			classe4.setData(LocalDate.now());
			classe4.setValor(35);

			Classe classe5 = new Classe();
			classe5.setNome("Diamante");
			classe5.setData(LocalDate.now());
			classe5.setValor(40);


			//--------------------------------------------------------
			//Titulo

			Titulo titulo1 = new Titulo();
			titulo1.setNome("A viagem de Chihiro");
			titulo1.setAno(LocalDate.now());
			titulo1.setSinopse("Sinopse Sinopse");
			titulo1.setCategoria("Animação");
			titulo1.setClasse(classe5);
			titulo1.setDiretor(diretor5);

			List<Ator> listaAtor1 = new ArrayList<>();
			listaAtor1.add(ator1);
			listaAtor1.add(ator2);
			listaAtor1.add(ator3);

			titulo1.setAtores(listaAtor1);

			Titulo titulo2 = new Titulo();
			titulo2.setNome("O Castelo Animado");
			titulo2.setAno(LocalDate.now());
			titulo2.setSinopse("Sinopse Sinopse");
			titulo2.setCategoria("Animação");
			titulo2.setClasse(classe4);
			titulo2.setDiretor(diretor1);

			List<Ator> listaAtor2 = new ArrayList<>();
			listaAtor2.add(ator4);
			listaAtor2.add(ator5);

			titulo2.setAtores(listaAtor2);



			//--------------------------------------------------------
			//Item

			Item item1 = new Item();
			item1.setNumserie(453324);
			item1.setDtaquisicao(LocalDate.now());
			item1.setTipoItem("Item 1 Teste");
			item1.setTitulo(titulo1);

			Item item2 = new Item();
			item2.setNumserie(537823);
			item2.setDtaquisicao(LocalDate.now());
			item2.setTipoItem("Item 2 Teste");
			item2.setTitulo(titulo2);


			//--------------------------------------------------------
			//Cliente

			Cliente cliente1 = new Cliente();
			cliente1.setNumeroInscricao(4324342);
			cliente1.setNome("Márcia Cristina");
			cliente1.setDataNascimento(LocalDate.now());
			cliente1.setSexo("Feminino");
			cliente1.setEstahAtivo("Sim");

			Cliente cliente2 = new Cliente();
			cliente2.setNumeroInscricao(6537432);
			cliente2.setNome("Jonatha Silva");
			cliente2.setDataNascimento(LocalDate.now());
			cliente2.setSexo("Masculino");
			cliente2.setEstahAtivo("Sim");


			//--------------------------------------------------------
			//Socio

			Socio socio1 = new Socio();
			socio1.setNumeroInscricao(543435435);
			socio1.setNome("Jocinei Almeida");
			socio1.setCpf("165.373.887-16");
			socio1.setDataNascimento(LocalDate.now());
			socio1.setEndereco("Rua das Flores");
			socio1.setTelefone("27-9999-9999");
			socio1.setSexo("Masculino");

			List<Cliente> listaDependentes1 = new ArrayList<>();

			listaDependentes1.add(cliente2);

			socio1.setDependentes(listaDependentes1);
			socio1.setEstahAtivo("Sim");



			//--------------------------------------------------------
			//Ator
			atorRepository.save(ator1);
			atorRepository.save(ator2);
			atorRepository.save(ator3);
			atorRepository.save(ator4);
			atorRepository.save(ator5);

			//--------------------------------------------------------
			//Diretor
			diretorRepository.save(diretor1);
			diretorRepository.save(diretor2);
			diretorRepository.save(diretor3);
			diretorRepository.save(diretor4);
			diretorRepository.save(diretor5);

			//--------------------------------------------------------
			//Classes
			classeRepository.save(classe1);
			classeRepository.save(classe2);
			classeRepository.save(classe3);
			classeRepository.save(classe4);
			classeRepository.save(classe5);


			//--------------------------------------------------------
			//Título
			tituloRepository.save(titulo1);
			tituloRepository.save(titulo2);


			//--------------------------------------------------------
			//Item
			itemRepository.save(item1);
			itemRepository.save(item2);


			//--------------------------------------------------------
			//Cliente
			clienteRepository.save(cliente1);
			clienteRepository.save(cliente2);


			//--------------------------------------------------------
			//Sócio
			socioRepository.save(socio1);


		};
	}
}
