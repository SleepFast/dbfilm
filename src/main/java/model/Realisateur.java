package model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Realisateur {
	@Id
	private String identite;

	@ManyToMany(mappedBy = "realisateurs")
    private List<Film> films = new ArrayList<>();
	private String url;

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
		return "Realisateur [identite=" + identite + ", url=" + url + "]";
	}
}
