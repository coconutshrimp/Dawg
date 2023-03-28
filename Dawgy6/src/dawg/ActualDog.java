package dawg;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class ActualDog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name, id, owner, gender;
	BufferedImage image;
	boolean grooming, obedience, socialization, fetch;

	public ActualDog(Register reg, CheckBoxPanel cbp, DnDImagePanel dnd) {
		name = reg.getNameField().getText();
		id = reg.getIdField().getText();
		owner = reg.getOwnerField().getText();
		gender = reg.getGenderDropDown();
		fetch = cbp.getFetch();
		grooming = cbp.getGrooming();
		socialization = cbp.getSocialization();
		obedience = cbp.getObedience();
		image = dnd.getImage();
		
		System.out.println("Name: " + name + " Owner: " + owner + " Gender: " + gender + " ID: " + id + " Fetch: "
				+ fetch + " Grooming: " + grooming + " Socialization: " + socialization + " Obedience: " + obedience + " Image: " + image);

	}

	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getOwner() {
		return owner;
	}



	public void setOwner(String owner) {
		this.owner = owner;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public boolean isGrooming() {
		return grooming;
	}



	public void setGrooming(boolean grooming) {
		this.grooming = grooming;
	}



	public boolean isObedience() {
		return obedience;
	}



	public void setObedience(boolean obedience) {
		this.obedience = obedience;
	}



	public boolean isSocialization() {
		return socialization;
	}



	public void setSocialization(boolean socialization) {
		this.socialization = socialization;
	}



	public boolean isFetch() {
		return fetch;
	}



	public void setFetch(boolean fetch) {
		this.fetch = fetch;
	}



	public ActualDog() {

	}

}