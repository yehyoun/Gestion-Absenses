package ing.entity;
// Generated 9 avr. 2016 22:00:36 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Absence generated by hbm2java
 */
@Entity
@Table(name = "absence", catalog = "gestionabsence")
public class Absence implements java.io.Serializable {

	private Long id;
	private Etudiant etudiant;
	private Matiere matiere;
	private Professeur professeur;
	private Date date;

	public Absence() {
	}

	public Absence(Etudiant etudiant, Matiere matiere, Professeur professeur) {
		this.etudiant = etudiant;
		this.matiere = matiere;
		this.professeur = professeur;
	}

	public Absence(Etudiant etudiant, Matiere matiere, Professeur professeur, Date date) {
		this.etudiant = etudiant;
		this.matiere = matiere;
		this.professeur = professeur;
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_etud", nullable = false)
	public Etudiant getEtudiant() {
		return this.etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mat", nullable = false)
	public Matiere getMatiere() {
		return this.matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_prof", nullable = false)
	public Professeur getProfesseur() {
		return this.professeur;
	}

	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", length = 19)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
