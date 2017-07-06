
public class TFQuestion implements Question{
	//answer = 0 means false; answer = 1 means true
	private String question;
	private int answer;
	private int type;
	
	public TFQuestion(){
		question = "";
		answer = 0;
		type = 0;
	}
	
	public TFQuestion(String q, int a){
		question = q + " [input answers as T/F]";
		answer = a;
		type = 0;
	}
	
	@Override
	public void setQuestion(String q) {
		question = q;		
	}

	@Override
	public String getQuestion() {
		return question;
	}

	@Override
	public void setAnswer(char a) {
		if(a == 'T'){
			answer = 1;
		}else{
			answer = 0;
		}
	}

	@Override
	public int getAnswer() {
		return answer;
	}
	
	public void printAnswer(){
		if(answer == 0){
			System.out.println("False");
		}else{
			System.out.println("True");
		}
	}
	
	public void printAllAnswers(){
		System.out.println("True or False (T/F)");
	}
	
	public String toString(){
		return question;
	}

	@Override
	public int getType() {
		//question type 0 = TF
		return 0;
	}
}
