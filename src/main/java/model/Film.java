package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties(value = {"castingPrincipal"})
@NamedQueries({
    @NamedQuery(name = "Film.findByFilmName", query = "SELECT f FROM Film f WHERE f.nom = :film"), 
    @NamedQuery(name = "Film.findByDate", query = "SELECT f FROM Film f WHERE EXTRACT(YEAR FROM f.sortie) BETWEEN :before AND :after"),
	@NamedQuery(name = "Film.findByFilmName2", query = "SELECT f FROM Film f WHERE f.nom = :film or f.nom = :film2")
})
public class Film {
    @Id
    private String id;

	@ManyToOne(cascade = CascadeType.MERGE)
    private Pays pays;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_lieu")
    private Lieu lieu;

	@ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable( name = "T_Film_Realisateur_Associations",
                joinColumns = @JoinColumn( name = "id_Film" ),
                inverseJoinColumns = @JoinColumn( name = "realisateur" ))
    private List<Realisateur> realisateurs = new ArrayList<>();

	@OneToMany( targetEntity=Role.class, mappedBy="film", cascade = CascadeType.ALL )
    private List<Role> roles = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "T_Film_Genre_Associations",
                joinColumns = @JoinColumn( name = "id_Film" ),
                inverseJoinColumns = @JoinColumn( name = "id_Genre" ))
    private List<Genre> genreList = new ArrayList<>();

    private String nom;
    private String url;
    private String plot;
    private String langue;
	private Date sortie;

	@JsonProperty("genres")
    private void transformListToGenreList(List<String> noms) {
		if (noms != null) {
			for (String nom : noms) {
				Genre genre = new Genre();
				genre.setNom(nom);
				genre.getFilms().add(this);
				genreList.add(genre);
			}
		}
    }
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	public String getLangue() {
		return langue;
	}
	public void setLangue(String langue) {
		this.langue = langue;
	}

	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@JsonProperty("anneeSortie")
	public Date getSortie() {
		return sortie;
	}
	public void setSortie(String sortie) {
		if (sortie != "") {
			int index = sortie.indexOf('/');
			if (index > -1) {
				sortie = sortie.substring(0, index);
			}
			index = sortie.indexOf('-');
			if (index > -1) {
				sortie = sortie.substring(0, index);
			}
			
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.set(Calendar.YEAR, Integer.parseInt(sortie));
			Date date = cal.getTime();
			this.sortie = date;
		} else {
			sortie = null;
		}
	}

    public Pays getPays() {
		return pays;
	}
	public void setPays(Pays pays) {
		this.pays = pays;
	}

	@JsonProperty("lieuTournage")
	public Lieu getLieu() {
		return lieu;
	}
	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}
	public List<Realisateur> getRealisateurs() {
		return realisateurs;
	}
	public void setRealisateurs(List<Realisateur> realisateurs) {
		this.realisateurs = realisateurs;
	}

	public List<Genre> getGenreList() {
		return genreList;
	}
	public void setGenreList(List<Genre> genreList) {
		this.genreList = genreList;
	}
	
	@Override
	public String toString() {
		return "\n Film [id=" + id + ", nom=" + nom + ", url=" + url + ", plot=" + plot + ", langue=" + langue + ", sortie=" + sortie + "]\n";
	}
}
