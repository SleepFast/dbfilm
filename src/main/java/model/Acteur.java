package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties(value = {"height", "roles"})
// @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Acteur.class, resolver=CustomObjectIdResolver.class)
public class Acteur {
	@Id
	private String id;
	@OneToMany( targetEntity=Role.class, mappedBy="acteur")
    private List<Role> roles = new ArrayList<>();

	private String identite;
	private String naissance;
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

    @JsonProperty("naissance")
    private void unpackNested(Map<String,String> naissance) {
		if (naissance != null) {
			this.naissance = naissance.get("dateNaissance");
			this.lieuNaissance = naissance.get("lieuNaissance");
		}
    }

	public String getNaissance() {
		return naissance;
	}
	public void setNaissance(String naissance) {
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

	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Acteur [id=" + id + ", identite=" + identite + ", naissance=" + naissance + ", lieuNaissance="
				+ lieuNaissance + ", url=" + url + "]";
	}
}
