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
import it.be.progettosettimana12.model.Libro;
import it.be.progettosettimana12.service.AutoreService;
import it.be.progettosettimana12.service.CategoriaService;
import it.be.progettosettimana12.service.LibroService;

@SecurityRequirement (name = "bearerAuth")
@RestController
@RequestMapping("/api")
public class LibroController {

	@Autowired
	private LibroService libroService;
	@Autowired
	private AutoreService autoreService;
	@Autowired
	private CategoriaService categoriaService;


	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Non richiede parametri e mostra tutti i libri disponibili")
	@GetMapping("/findall")
	public ResponseEntity<List<Libro>> findAll() {
		List<Libro> findAll = libroService.findAllLibri();

		if (!findAll.isEmpty()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Ricerca un libro dal suo Id")
	@GetMapping("/findlibribyid/{id}")
	public ResponseEntity<Libro> findById(@PathVariable(required = true) Long id) {
		Optional<Libro> find = libroService.findLibriById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "cancellare dal JSON l'id per Libro, Autore e Categoria altrimenti va in errore")
	@PostMapping("/savelibro")
	public ResponseEntity<Libro> save(@RequestBody Libro libro) {
		autoreService.saveAllFromSet(libro.getAutori());
		categoriaService.saveAllFromSet(libro.getCategorie());
		Libro save = libroService.saveLibro(libro);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary="cancella un libro tramite Id")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteLibro(@PathVariable (required = true) Long id){
		Optional<Libro> trovato = libroService.findLibriById(id);
		if(trovato.isEmpty()) {
			return new ResponseEntity<>("Libro non trovato!", HttpStatus.NOT_FOUND);
		}
		else {
			libroService.deleteLibro(id);
			return new ResponseEntity<>("Libro eliminato correttamente!", HttpStatus.OK);
		}
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Permette di aggiornare il libro (NON PASSARE NEL JSON L'ID DEL LIBRO). Inserire un Id già esistente per autore e categoria")
	@PutMapping("/updatelibro/{id}")
	public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libro) {
		categoriaService.saveAllFromSet(libro.getCategorie());
		autoreService.saveAllFromSet(libro.getAutori());
		libroService.updateLibro(id, libro);
		return new ResponseEntity<>(libro, HttpStatus.OK);
	}
}