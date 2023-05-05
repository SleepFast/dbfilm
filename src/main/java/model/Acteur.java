package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Acteur {
	@Id
	private String id;
	@OneToMany( targetEntity=Role.class, mappedBy="acteur" )
    private List<Role> roles = new ArrayList<>();
	private String identite;
	private Date naissance;
	private String lieuNaissance;
	private String url;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdentite() {
		return identite;
	}
	public void setIdentite(String identite) {
		this.identite = identite;
	}
	public Date getNaissance() {
		return naissance;
	}
	public void setNaissance(Date naissance) {
		this.naissance = naissance;
	}
	public String getLieuNaissance() {
		return lieuNaissance;
	}
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "Acteur [id=" + id + ", identite=" + identite + ", naissance=" + naissance + ", lieuNaissance="
				+ lieuNaissance + ", url=" + url + "]";
	}
}
