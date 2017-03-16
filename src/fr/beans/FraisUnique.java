package fr.beans;

public class FraisUnique {
	private String idFrais;
	private String dateFrais;
	private String motifFrais;
	private String trajetFrais;
	private String kmsFrais;
	private String coutFrais;
	private String peageFrais;
	private String repasFrais;
	private String hebergementFrais;
	
	public FraisUnique() {
		/*this.idFrais = idFrais;
		this.dateFrais = dateFrais;
		this.motifFrais = motifFrais;*/
	}
	
	public void setId(String id) {
		this.idFrais = id;
	}
	public void setDate(String date) {
		this.dateFrais = date;
	}
	public void setMotif(String motif) {
		this.motifFrais = motif;
	}
	public void setTrajet(String trajet) {
		this.trajetFrais = trajet;
	}
	public void setKms(String kms) {
		this.kmsFrais = kms;
	}
	public void setCout(String cout) {
		this.coutFrais = cout;
	}
	public void setPeage(String peage) {
		this.peageFrais = peage;
	}
	public void setRepas(String repas) {
		this.repasFrais = repas;
	}
	public void setHebergement(String hebergement) {
		this.hebergementFrais = hebergement;
	}
	
	
	public String getId() {
		return this.idFrais;
	}
	public String getDate() {
		return this.dateFrais;
	}
	public String getMotif() {
		return this.motifFrais;
	}
	public String getTrajet() {
		return this.trajetFrais;
	}
	public String getKms() {
		return this.kmsFrais;
	}
	public String getCout() {
		return this.coutFrais;
	}
	public String getPeage() {
		return this.peageFrais;
	}
	public String getRepas() {
		return this.repasFrais;
	}
	public String getHebergement() {
		return this.hebergementFrais;
	}

	
}
