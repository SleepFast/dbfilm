package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Film {
    @Id
    private String id;
	@ManyToOne
    @JoinColumn(name="id_pays")
    private Pays pays;
	@ManyToOne
    @JoinColumn(name="id_lieu")
    private Lieu lieu;
	@ManyToMany
    @JoinTable( name = "T_Film_Realisateur_Associations",
                joinColumns = @JoinColumn( name = "id_Film" ),
                inverseJoinColumns = @JoinColumn( name = "id_Realisateur" ))
    private List<Realisateur> realisateurs = new ArrayList<>();
	@OneToMany( targetEntity=Role.class, mappedBy="film" )
    private List<Role> casting = new ArrayList<>();
	@ManyToMany
    @JoinTable( name = "T_Film_Genre_Associations",
                joinColumns = @JoinColumn( name = "id_Film" ),
                inverseJoinColumns = @JoinColumn( name = "id_Genre" ))
    private List<Genre> genre = new ArrayList<>();
    private String nom;
    private String url;
    private String plot;
    private String langue;
	private Date sortie;
    
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
	public List<Role> getCasting() {
		return casting;
	}
	public void setCasting(List<Role> casting) {
		this.casting = casting;
	}
	public Date getSortie() {
		return sortie;
	}
	public void setSortie(Date sortie) {
		this.sortie = sortie;
	}

    public Pays getPays() {
		return pays;
	}
	public void setPays(Pays pays) {
		this.pays = pays;
	}
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
	public List<Genre> getGenre() {
		return genre;
	}
	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}
	
	@Override
	public String toString() {
		return "Film [id=" + id + ", nom=" + nom + ", url=" + url + ", plot=" + plot + ", langue=" + langue
				+ ", casting=" + casting + ", sortie=" + sortie + "]";
	}
}
