package model;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Realisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToMany(mappedBy = "realisateurs")
    // @JoinTable( name = "T_Film_Realisateur_Associations",
    //             joinColumns = @JoinColumn( name = "id_Realisateur"),
    //             inverseJoinColumns = @JoinColumn( name = "id_Film"))
    private List<Film> films = new ArrayList<>();
	private String identite;
	private String url;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdentite() {
		return identite;
	}
	public void setIdentite(String identite) {
		this.identite = identite;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Film> getFilms() {
		return films;
	}
	public void setFilms(List<Film> films) {
		this.films = films;
	}
	
	@Override
	public String toString() {
		return "Realisateur [id=" + id + ", identite=" + identite + ", url=" + url + "]";
	}
}
