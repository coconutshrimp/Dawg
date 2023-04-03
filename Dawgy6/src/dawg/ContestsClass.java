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
	public void Grooming(String contestName, String dogName, int score)
	{
		System.out.println(contestName + dogName + score);
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
	
	
//	public String[] getDogs() {
//		ArrayList<String> temp = new ArrayList<String>();
//		for(int i = 0; i < this.getDogCount(); i++) {
//			//temp.add(name)
//		}
//	}
	
	
}
