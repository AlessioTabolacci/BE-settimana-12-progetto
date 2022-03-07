package it.be.progettosettimana12.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.be.progettosettimana12.model.Autore;
import it.be.progettosettimana12.model.Categoria;
import it.be.progettosettimana12.model.Libro;
import it.be.progettosettimana12.repository.AutoreRepository;
import it.be.progettosettimana12.repository.CategoriaRepository;
import it.be.progettosettimana12.repository.LibroRepository;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class ApplicationStartupRunner implements CommandLineRunner {
	
	
	@Autowired
	private LibroRepository libroRepository;
	@Autowired
	private AutoreRepository autoreRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		Autore autore = new Autore();
		autore.setNome("Alessandro");
		autore.setCognome("Manzoni");
		autoreRepository.save(autore);
		
		Autore autore2 = new Autore();
		autore2.setNome("Fedor");
		autore2.setCognome("Dostoevskij");
		autoreRepository.save(autore2);
		
		Categoria categoria = new Categoria();
		categoria.setNome("Romanzo");
		categoriaRepository.save(categoria);
		
		Categoria categoria2 = new Categoria();
		categoria2.setNome("Narrativa");
		categoriaRepository.save(categoria2);
		
		Libro libro = new Libro();
		libro.setTitolo("I promessi sposi");
		libro.setAnnoPubb(1827);
		libro.getAutori().add(autore);
		libro.getCategorie().add(categoria);
		libro.setPrezzo(18.50);
		libroRepository.save(libro);
		
		Libro libro2 = new Libro();
		libro2.setTitolo("Le notti bianche");
		libro2.setAnnoPubb(1848);
		libro2.getAutori().add(autore2);
		libro2.getCategorie().add(categoria2);
		libro2.setPrezzo(15.00);
		libroRepository.save(libro2);
		
		Autore autore3 = new Autore();
		autore3.setNome("George");
		autore3.setCognome("Orwell");
		autoreRepository.save(autore3);
		
		Categoria categoria3 = new Categoria();
		categoria3.setNome("Fantascienza");
		categoriaRepository.save(categoria3);
		
		Libro libro3 = new Libro();
		libro3.setTitolo("1984");
		libro3.setAnnoPubb(1949);
		libro3.getAutori().add(autore3);
		libro3.getCategorie().add(categoria3);
		libro3.setPrezzo(18.20);
		libroRepository.save(libro3);
	
	}

}
