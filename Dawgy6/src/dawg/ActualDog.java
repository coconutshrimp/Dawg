package dawg;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

public class ActualDog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name, id, owner, gender;
	ContestantIcon image;
	boolean grooming, obedience, socialization, fetch;
	int gScore, oScore, sScore, fScore;

	public ActualDog(Register reg, CheckBoxPanel cbp, ContestantIcon icon) {
		name = reg.getNameField().getText();
		id = reg.getIdField().getText();
		owner = reg.getOwnerField().getText();
		gender = reg.getGenderDropDown();
		fetch = cbp.getFetch();
		grooming = cbp.getGrooming();
		socialization = cbp.getSocialization();
		obedience = cbp.getObedience();
		image = icon;
		
		
		System.out.println("Name: " + name + " Owner: " + owner + " Gender: " + gender + " ID: " + id + " Fetch: "
				+ fetch + " Grooming: " + grooming + " Socialization: " + socialization + " Obedience: " + obedience + " Image: " + image);

	}


	public ActualDog(String name, String id, String owner, String gender, boolean groom,
			boolean obedience, boolean social, boolean fetch, ContestantIcon icon) {
		this.name = name;
		this.id = id;
		this.owner = owner;
		this.gender = gender;
		this.grooming = groom;
		this.obedience = obedience;
		this.socialization = social;
		this.fetch = fetch;
		this.image = icon;
		if(!grooming)
			gScore = -1;
		else
			gScore = 0;
		if(!socialization)
			sScore = -1;
		else
			sScore = 0;
		if(!obedience)
			oScore = -1;
		else
			oScore = 0;
		if(!fetch)
			fScore = -1;
		else
			fScore = 0;


	}
	 

	
	public int getgScore() {
		return gScore;
	}
	
	public int getoScore() {
		return oScore;
	}
	
	public int getsScore() {
		return sScore;
	}
	
	public int getfScore() {
		return fScore;
	}
	
	public void setgScore(int score) {
		gScore = score;
	}
	
	public void setoScore(int score) {
		oScore = score;
	}
	
	public void setsScore(int score) {
		sScore = score;
	}
	
	public void setfScore(int score) {
		fScore = score;
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

	public ContestantIcon getImage() {
		return image;
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