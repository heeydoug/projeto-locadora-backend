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

			Ator a = new Ator();
			a.setNome("João Felipe");
			Ator b = new Ator();
			b.setNome("Douglas");

			Diretor c = new Diretor();
			c.setNome("Clarisse Diniz");
			Diretor d = new Diretor();
			d.setNome("Filipe Mattos");

			Classe e = new Classe();
			e.setNome("Classe1");
			e.setData(LocalDate.now());
			e.setValor(26);

			Classe f = new Classe();
			f.setNome("Classe2");
			f.setData(LocalDate.now());
			f.setValor(27);


			//Aqui Titulo

			Titulo tit = new Titulo();
			tit.setNome("Titulo Teste");
			tit.setAno(LocalDate.now());
			tit.setSinopse("Sinopse Teste");
			tit.setCategoria("Categoria teste");
			tit.setClasse(e);
			tit.setDiretor(c);

			List<Ator> listaAtor = new ArrayList<Ator>();

			listaAtor.add(a);
			listaAtor.add(b);

			tit.setAtores(listaAtor);

			//Item

			Item item = new Item();
			item.setNumserie(69);
			item.setDtaquisicao(LocalDate.now());
			item.setTipoItem("Tipo de item teste");
			item.setTitulo(tit);

			//Cliente

			Cliente cliente = new Cliente();
			cliente.setNumeroInscricao(432432);
			cliente.setNome("Márcia Cristina");
			cliente.setDataNascimento(LocalDate.now());
			cliente.setSexo("Feminino");
			cliente.setEstahAtivo("Sim");

			//Socio

			Socio socio = new Socio();
			socio.setNumeroInscricao(543435435);
			socio.setNome("Jocinei Alemida");
			socio.setCpf("165.373.887-16");
			socio.setDataNascimento(LocalDate.now());
			socio.setEndereco("Rua das Flores");
			socio.setTelefone("27-9999-9999");
			socio.setSexo("Masculino");

			List<Cliente> listaDependentes = new ArrayList<Cliente>();

			listaDependentes.add(cliente);

			socio.setDependentes(listaDependentes);

			socio.setEstahAtivo("Sim");


			atorRepository.save(a);
			atorRepository.save(b);

			diretorRepository.save(c);
			diretorRepository.save(d);

			classeRepository.save(e);
			classeRepository.save(f);

			tituloRepository.save(tit);

			itemRepository.save(item);

			clienteRepository.save(cliente);

			socioRepository.save(socio);

		};
	}
}
