package it.be.progettosettimana12.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import it.be.progettosettimana12.model.Categoria;
import it.be.progettosettimana12.model.Libro;
import it.be.progettosettimana12.service.AutoreService;
import it.be.progettosettimana12.service.CategoriaService;
import it.be.progettosettimana12.service.LibroService;

@SecurityRequirement (name = "bearerAuth")
@RestController
@RequestMapping("/api")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;
	@Autowired
	LibroService libroService;
	@Autowired
	AutoreService autoreService;


	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Non richiede paramentri e mostra tutte le categorie disponibili")
	@GetMapping("/findallcategorie")
	public ResponseEntity<List<Categoria>> findAllCategorie() {
		List<Categoria> findAllCategorie = categoriaService.findAllCategorie();

		if (!findAllCategorie.isEmpty()) {
			return new ResponseEntity<>(findAllCategorie, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Ricerca una categoria dal suo Id")
	@GetMapping("/findcategoriabyid/{id}")
	public ResponseEntity<Categoria> findCategoriaById(@PathVariable(required = true) Long id) {
		Optional<Categoria> findCategoria = categoriaService.finCategoriaById(id);

		if (findCategoria.isPresent()) {
			return new ResponseEntity<>(findCategoria.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Inserisce una categoria! Se dovesse andare in errore provare a togliere dal JSON l'id")
	@PostMapping("/savecategoria")
	public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
		Categoria saveCategoria = categoriaService.saveCategoria(categoria);
		return new ResponseEntity<>(saveCategoria, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary="cancella una categoria tramite Id")
	@DeleteMapping("/deletecategoria/{id}")
	public ResponseEntity<String> deleteCategoria(@PathVariable (required = true) Long id){
		Optional<Categoria> categoriaTrovata = categoriaService.finCategoriaById(id);
		if(categoriaTrovata.isEmpty()) {
			return new ResponseEntity<>("Categoria non trovato!", HttpStatus.NOT_FOUND);
		}
		else {
			Categoria c = categoriaTrovata.get();
			List<Libro> tl = libroService.findAllLibri();
			for (Libro libro : tl) {
				libro.deleteCategoria(c);
			}
			categoriaService.deleteCategoria(id);
			return new ResponseEntity<>("Categoria eliminata correttamente!", HttpStatus.OK);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Permette di aggiornare una categoria tramite id")
	@PutMapping("/updatecategoria/{id}")
	public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
		categoriaService.updateCategoria(id, categoria);
		return new ResponseEntity<>(categoria, HttpStatus.OK);

	}



}
