package model;

import java.util.ArrayList;
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
    @NamedQuery(name = "Film.findByActorId", query = "SELECT f FROM Film f WHERE f.nom LIKE :film")
})
public class Film {
    @Id
    private String id;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_pays")
    private Pays pays;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_lieu")
    private Lieu lieu;

	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "T_Film_Realisateur_Associations",
                joinColumns = @JoinColumn( name = "id_Film" ),
                inverseJoinColumns = @JoinColumn( name = "id_Realisateur" ))
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
	private String sortie;

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
	public void setRoles(List<Role> casting) {
		this.roles = casting;
	}

	@JsonProperty("anneeSortie")
	public String getSortie() {
		return sortie;
	}
	public void setSortie(String sortie) {
		this.sortie = sortie;
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
		return "Film [id=" + id + ", nom=" + nom + ", url=" + url + ", plot=" + plot + ", langue=" + langue + ", sortie=" + sortie + "]";
	}
}
