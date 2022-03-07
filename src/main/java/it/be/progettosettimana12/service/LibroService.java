package it.be.progettosettimana12.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.be.progettosettimana12.exception.LibroException;
import it.be.progettosettimana12.model.Libro;
import it.be.progettosettimana12.repository.AutoreRepository;
import it.be.progettosettimana12.repository.LibroRepository;

@Service
public class LibroService {

	@Autowired
	LibroRepository libroRepository;
	@Autowired
	AutoreRepository autoreRepository;
	@Autowired
	AutoreService autoreService;


	public List<Libro> findAllLibri() {
		return libroRepository.findAll();
	}

	public Optional<Libro> findLibriById(Long id) {
		return libroRepository.findById(id);
	}

	public Libro saveLibro(Libro libro) {
		return libroRepository.save(libro);
	}

	public Libro updateLibro(Long id, Libro libro) {
		Optional<Libro> libroResult = libroRepository.findById(id);

		if (libroResult.isPresent()) {
			Libro libroUpdate = libroResult.get();
			libroUpdate.setTitolo(libro.getTitolo());
			libroUpdate.setAutori(libro.getAutori());
			libroUpdate.setCategorie(libro.getCategorie());
			libroUpdate.setAnnoPubb(libro.getAnnoPubb());
			libroUpdate.setPrezzo(libro.getPrezzo());

			libroRepository.save(libroUpdate);
			return libroUpdate;
		} else {
			throw new LibroException("Libro non aggiornato");
		}

	}

	public void deleteLibro (Long id) {
		libroRepository.deleteById(id);
	}

	public void deleteLibriNoAutore() {
		List<Libro> allLibri = libroRepository.findAll();
		List<Libro> libriOrfani = new ArrayList<>();
		for (Libro libro : allLibri) {
			if(libro.getAutori().isEmpty()) {
				libriOrfani.add(libro);

			}
		}
		libroRepository.deleteAll(libriOrfani);
	}
		
}
