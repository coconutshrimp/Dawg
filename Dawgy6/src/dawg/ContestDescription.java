package dawg;

import java.io.Serializable;
import java.util.ArrayList;

public class ContestDescription implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int score;
	private ActualDog doggy;
	private int dogCount;
	ArrayList<ActualDog> doglist = new ArrayList<ActualDog>();

	public ContestDescription(String name) {
		this.name = name;
		// this.score = Integer.parseInt(score);

	}

	public Object[][] formatArray(ArrayList<ActualDog> list) {

		Object[][] data = new Object[list.size()][];
		int index = 0;

		for (ActualDog dog : list) {
			Object[] dogInfo = new Object[9];
			dogInfo[0] = dog.getName();
			dogInfo[1] = dog.getId();
			dogInfo[2] = dog.getGender();
			dogInfo[3] = dog.getOwner();
			dogInfo[4] = dog.getfScore();
			dogInfo[5] = dog.getgScore();
			dogInfo[6] = dog.getoScore();
			dogInfo[7] = dog.getsScore();
			dogInfo[8] = dog.getImage();
			data[index++] = dogInfo;
		}

		return data;

	}

	public ArrayList<ActualDog> getDogList() {
		return doglist;

	}

	public String getName() {
		return name;
	}

	// Update Grooming
	public void grooming(String ID, int score) {
		for(int i = 0; i < doglist.size(); i++) {
			if(Integer.parseInt(doglist.get(i).getId())==Integer.parseInt(ID)) {
				doglist.get(i).setgScore(score);
			}
		}
	}

	public void obedience(String ID, int score) {
		for(int i = 0; i < doglist.size(); i++) {
			if(Integer.parseInt(doglist.get(i).getId())==Integer.parseInt(ID)) {
				doglist.get(i).setoScore(score);
			}
		}
	}

	public void socialization(String ID, int score) {
		for(int i = 0; i < doglist.size(); i++) {
			if(Integer.parseInt(doglist.get(i).getId())==Integer.parseInt(ID)) {
				doglist.get(i).setsScore(score);
			}
		}
	}

	public void fetch(String ID, int score) {
		for(int i = 0; i < doglist.size(); i++) {
			if(Integer.parseInt(doglist.get(i).getId())==Integer.parseInt(ID)) {
				doglist.get(i).setfScore(score);
			}
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
		return dogCount;
	}

	public ActualDog findDog(String name) {
		for (int i = 0; i < dogCount; i++) {
			if (doglist.get(i).getName().equals(name))
				return doglist.get(i);
		}
		ActualDog x = new ActualDog();
		return x;
	}

	public void dogs() {
		for (int i = 0; i < dogCount; i++) {
			String n = doglist.get(i).getName();
			int g = doglist.get(i).getgScore();
			int o = doglist.get(i).getoScore();
			int s = doglist.get(i).getsScore();
			int f = doglist.get(i).getfScore();
			//System.out.println(n + "'s Scores: " + g + " " + o + " " + s + " " + f);
		}

	}

	public ArrayList<ActualDog> getList() {
		return doglist;

	}

	public ArrayList<String> getDogNames() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Dog Name");
		for (int i = 0; i < dogCount; i++) {
			names.add(doglist.get(i).getName());
		}

		return names;
	}

	public void addDog(String name, String id, String owner, String gender, boolean groom, boolean obedience,
			boolean social, boolean fetch, ContestantIcon icon) {
		ActualDog x = new ActualDog(name, id, owner, gender, groom, obedience, social, fetch, icon);
		doglist.add(x);
		dogCount++;
//		for (int i = 0; i < dogCount; i++) {
//			System.out.println(doglist.get(i).getName());
//		}

	}

	public int getGroomScore(String name) {
		for (int i = 0; i < dogCount; i++) {
			if (doglist.get(i).getName().equals(name))
				return doglist.get(i).getgScore();

		}
		return 0;
	}

	public ActualDog[] getArray() {
		return (ActualDog[]) doglist.toArray();

	}

	public String notScored() {
		String str = "";
		for (int i = 0; i < dogCount; i++) {
			if (doglist.get(i).getgScore() == 0 || doglist.get(i).getfScore() == 0 || doglist.get(i).getoScore() == 0
					|| doglist.get(i).getsScore() == 0)
				str += doglist.get(i).getName() + " ";
		}
		return str;
	}

	public boolean isScored() {
		boolean check = true;
		for (int i = 0; i < dogCount; i++) {
			if (doglist.get(i).getgScore() == 0 || doglist.get(i).getfScore() == 0 || doglist.get(i).getoScore() == 0
					|| doglist.get(i).getsScore() == 0)
				check = false;
		}

		return check;
	}

}
