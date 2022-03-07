package it.be.progettosettimana12.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.be.progettosettimana12.exception.AutoreException;
import it.be.progettosettimana12.model.Autore;
import it.be.progettosettimana12.repository.AutoreRepository;
@Service
public class AutoreService {

	@Autowired
	AutoreRepository autoreRepository;

	public void saveAllFromSet(Set<Autore> set) {
		for (Autore autore : set) {
			autoreRepository.save(autore);
		}
	}

	public List<Autore> findAllAutori() {
		return autoreRepository.findAll();
	}

	public Optional<Autore> findAutoriById(Long id) {
		return autoreRepository.findById(id);
	}

	public Autore saveAutore(Autore autore) {
		return autoreRepository.save(autore);
	}

	public Autore updateAutore(Long id, Autore autore) {
		Optional<Autore> autoreResult = autoreRepository.findById(id);

		if (autoreResult.isPresent()) {
			Autore autoreUpdate = autoreResult.get();
			autoreUpdate.setNome(autore.getNome());
			autoreUpdate.setCognome(autore.getCognome());

			autoreRepository.save(autoreUpdate);
			return autoreUpdate;
		} else {
			throw new AutoreException("Autore non aggiornato");
		}

	}

	public void deleteAutore (Long id) {
		autoreRepository.deleteById(id);
	}
}
