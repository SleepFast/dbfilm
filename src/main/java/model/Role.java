package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIgnoreProperties({"film"})
// @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Role.class, resolver=CustomObjectIdResolver.class)
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE) 
	@JoinColumn(name="idFilm")
    private Film film;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="idActeur")
    private Acteur acteur;

	private String nom;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty("characterName")
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public Acteur getActeur() {
		return acteur;
	}
	public void setActeur(Acteur acteur) {
		this.acteur = acteur;
	}
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", film=" + film + ", acteur=" + acteur + ", nom=" + nom + "]";
	}
	
}
