package it.be.progettosettimana12.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.be.progettosettimana12.exception.AutoreException;
import it.be.progettosettimana12.exception.CategoriaException;
import it.be.progettosettimana12.model.Autore;
import it.be.progettosettimana12.model.Categoria;
import it.be.progettosettimana12.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public void saveAllFromSet(Set<Categoria> set) {
		for (Categoria categoria : set) {
			categoriaRepository.save(categoria);
		}
	}

	public List<Categoria> findAllCategorie() {
		return categoriaRepository.findAll();
	}

	public Optional<Categoria> finCategoriaById(Long id) {
		return categoriaRepository.findById(id);
	}

	public Categoria saveCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria updateCategoria(Long id, Categoria categoria) {
		Optional<Categoria> categoriaResult = categoriaRepository.findById(id);

		if (categoriaResult.isPresent()) {
			Categoria categoriaUpdate = categoriaResult.get();
			categoriaUpdate.setNome(categoria.getNome());

			categoriaRepository.save(categoriaUpdate);
			return categoriaUpdate;
		} else {
			throw new CategoriaException("Categoria non aggiornato");
		}

	}
	public void deleteCategoria (Long id) {
		categoriaRepository.deleteById(id);
	}
}
