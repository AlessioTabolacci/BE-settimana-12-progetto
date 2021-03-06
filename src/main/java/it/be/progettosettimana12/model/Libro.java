package it.be.progettosettimana12.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Libro {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (nullable = false)
	private String titolo;
	@Column (nullable = false)
	private Integer annoPubb;
	@Column (nullable = false)
	private Double prezzo;
	@ManyToMany
	@JoinTable(name = "libri_autori",
	joinColumns = @JoinColumn(name = "libri_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "autore_id", referencedColumnName = "id"))
	private Set<Autore> autori = new HashSet<>();
	@ManyToMany
	@JoinTable(name = "libri_categoria",
	joinColumns = @JoinColumn(name = "libri_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "id"))
	private Set<Categoria> categorie = new HashSet<>();

	
	public void deleteAutore(Autore autore) {
		if(this.autori.contains(autore))
			this.autori.remove(autore);
	}
	public void deleteCategoria(Categoria categoria) {
		if(this.categorie.contains(categoria))
			this.categorie.remove(categoria);
	}
}
