import java.util.*;

public class MCQuestion implements Question{
	private String question;
	private int answer;	//0 goes to A, 1 goes to B etc
	private ArrayList<String> answers;
	private int type;
	
	public MCQuestion(){
		question = "";
		answer = -1;
		answers = new ArrayList<>(0);
		type = 1;
	}
	
	public MCQuestion(String q, int a){
		question = q + " [input answers as ABCD etc]";
		answer = a;
		answers = new ArrayList<>();
		type = 1;
	}
	
	public void setQuestion(String q){
		question = q;
	}
	
	public String getQuestion(){
		return question;
	}
	
	//sets the answer key
	public void setAnswer(char a){
		answer = Character.getNumericValue(a) - 10;
	}
	
	public int getAnswer(){
		return answer;
	}
	
	//add an answer choice for the students
	public void addAnswer(String a){
		answers.add(a);
	}
	
	//user types in answer to be removed, A B C D
	//converts char to int to get index of array to remove
	public String removeAnswer(char letter){
		int location = Character.getNumericValue(letter);
		location -= 10;	//subtract 10 since a = 10
		return answers.remove(location);
	}
	
	//print list of all the possible answers
	public void printAllAnswers(){
		for(int i = 0; i < answers.size(); i++){
			char a = (char) (i + 65);
			System.out.println("\t" + a + ". " + answers.get(i));
		}
	}
	
	public void printAnswer(){
		int a = answer + 65;
		//convert answer to its character value to print A. answer
		System.out.println((char) a + ". " + answers.get(answer));
	}
	
	public String toString(){
		return question;
	}

	@Override
	public int getType() {
		//question type 1 = MC
		return 1;
	}
}
