package dawg;

import java.util.ArrayList;

public class ContestsClass {
	
	private String name;
	private int score;
	private ActualDog dog;
	private int DogCount;
	ArrayList<ActualDog> doglist = new ArrayList<ActualDog>();
	
	
	public ContestsClass(String name) {
		this.name = name;
		//this.score = Integer.parseInt(score);
		
	
	}


	public String getName() {
		return name;
	}
	
	//Update Grooming
	public void Grooming(String dogName, int score)
	{
		for(int i = 0; i < DogCount; i++)
			if(doglist.get(i).getName().equals(dogName)) {
				doglist.get(i).setgScore(score);
				System.out.println(doglist.get(i).getName() + "'s score is: " + doglist.get(i).getgScore());
			}
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}
	
	public int getDogCount() {
		return DogCount;
	}
	
	public ActualDog findDog(String name)
	{
		for(int i = 0; i < DogCount; i++) {
			if(doglist.get(i).getName().equals(name))
				return doglist.get(i);
		}

		return doglist.get(0);
	}

	public void addDog(String name, String id, String owner, String gender, boolean groom,
			boolean obedience, boolean social, boolean fetch) {
		ActualDog x = new ActualDog(name, id, owner, gender, groom, obedience, social, fetch);
		doglist.add(x);
		DogCount++;
		for(int i = 0; i < DogCount; i++) {
			System.out.println(doglist.get(i).getName());
		}

	}
	
	public int getGroomScore(String name) {
		for(int i = 0; i < DogCount; i++) {
			if(doglist.get(i).getName().equals(name))
				return doglist.get(i).getgScore();
			
		}
		return 0;
	}

}