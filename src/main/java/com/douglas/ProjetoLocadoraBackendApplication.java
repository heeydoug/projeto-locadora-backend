package com.douglas;

import com.douglas.model.Ator;
import com.douglas.model.Classe;
import com.douglas.model.Diretor;
import com.douglas.repository.AtorRepository;
import com.douglas.repository.ClasseRepository;
import com.douglas.repository.DiretorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class ProjetoLocadoraBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoLocadoraBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(AtorRepository atorRepository, DiretorRepository diretorRepository, ClasseRepository classeRepository) {
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


			atorRepository.save(a);
			atorRepository.save(b);

			diretorRepository.save(c);
			diretorRepository.save(d);

			classeRepository.save(e);
			classeRepository.save(f);

		};
	}
}
