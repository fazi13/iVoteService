
public class Student implements Person{
	private String fname;
	private String lname;
	private String id;
	private int answer;
	
	@Override
	public void setFName(String name) {
		fname = name;
	}

	@Override
	public String getFName() {
		return fname;
	}
	
	@Override
	public void setLName(String name) {
		lname = name;
	}

	@Override
	public String getLName() {
		return lname;
	}	
	
	public void setID(String id1){
		id = id1;
	}
	
	public String getID(){
		return id;
	}
	
	public String toString(){
		return fname + " " + lname + " " + answer;
	}
	
	public void setAnswer(char a){
		//convert to upper to keep all answers in sync
		//then converts answer to int
		a = Character.toUpperCase(a);
		if(a == 'T'){
			answer = 1;
		}else if(a == 'F'){
			answer = 0;
		}else{
			//answer must be MC so need to make A = 0 B = 1 etc
			answer = Character.getNumericValue(a) - 10;	//subtract 10 since A = 10
		}
	}
	
	public int getAnswer(){
		return answer;
	}
	
	@Override
	public boolean equals(Object o){
		if(this == o){
			return true;
		}
		if(o == null){
			return false;
		}
		if(getClass() != o.getClass()){
			return false;
		}
		Student s2 = (Student) o;
		if(s2.getID().equals(id)){
			return true;
		}
		return false;
	}
}
