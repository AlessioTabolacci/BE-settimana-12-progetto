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
import it.be.progettosettimana12.model.Autore;
import it.be.progettosettimana12.model.Libro;
import it.be.progettosettimana12.service.AutoreService;
import it.be.progettosettimana12.service.LibroService;

@SecurityRequirement (name = "bearerAuth")
@RestController
@RequestMapping("/api")
public class AutoreController {

	@Autowired
	private LibroService libroService;
	@Autowired
	private AutoreService autoreService;



	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Non richiede paramentri e mostra tutti gli autori disponibili")
	@GetMapping("/findallautori")
	public ResponseEntity<List<Autore>> findAllAutori() {
		List<Autore> findAllAutori = autoreService.findAllAutori();

		if (!findAllAutori.isEmpty()) {
			return new ResponseEntity<>(findAllAutori, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Ricerca un autore dal suo Id")
	@GetMapping("/findautorebyid/{id}")
	public ResponseEntity<Autore> findAutoreById(@PathVariable(required = true) Long id) {
		Optional<Autore> findAutore = autoreService.findAutoriById(id);

		if (findAutore.isPresent()) {
			return new ResponseEntity<>(findAutore.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Salva autore")
	@PostMapping("/saveautore")
	public ResponseEntity<Autore> saveAutore(@RequestBody Autore autore) {
		Autore saveAutore = autoreService.saveAutore(autore);
		return new ResponseEntity<>(saveAutore, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary="cancella un autore tramite Id")
	@DeleteMapping("/deleteautore/{id}")
	public ResponseEntity<String> deleteAutore(@PathVariable (required = true) Long id){
		Optional<Autore> autoreTrovato = autoreService.findAutoriById(id);
		if(autoreTrovato.isEmpty()) {
			return new ResponseEntity<>("Autore non trovato!", HttpStatus.NOT_FOUND);
		}
		else {
			Autore a = autoreTrovato.get();
			List<Libro> ls = libroService.findAllLibri();
			for (Libro libro : ls) {
				libro.deleteAutore(a);
			}
			libroService.deleteLibriNoAutore();
			autoreService.deleteAutore(id);
			return new ResponseEntity<>("Autore e libro eliminati correttamente!", HttpStatus.OK);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Permette di aggiornare un autore tramite id")
	@PutMapping("/updateautore/{id}")
	public ResponseEntity<Autore> updateAutore(@PathVariable Long id, @RequestBody Autore autore) {
		autoreService.updateAutore(id, autore);
		return new ResponseEntity<>(autore, HttpStatus.OK);

	}
}
