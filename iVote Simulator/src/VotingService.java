import java.util.*;

public class VotingService {
	private ArrayList<Question> qlist;
	private ArrayList<Student> slist;
	private int numQuestions;
	private ArrayList<Integer> distribution;
	
	public VotingService(){
		qlist = new ArrayList<Question>();
		slist = new ArrayList<Student>();
		distribution = new ArrayList<Integer>();
		numQuestions = 0;
	}
	
	public VotingService(int n){
		qlist = new ArrayList<Question>();
		slist = new ArrayList<Student>();
		distribution = new ArrayList<Integer>();
		numQuestions = n;
	}
	
	public void run(){
		//allow user to input questions first
		addQuestions();
		//now run and gather answers
		getStudents();
		getAnswers();
	}
	
	private void addQuestions(){
		//user inputs no of questions
		System.out.print("How many questions? ");
		Scanner sc = new Scanner(System.in);
		int size = Integer.parseInt(sc.nextLine());
		numQuestions = size;
		String input = "";
		//create new question list and loop to fill it
		qlist = new ArrayList<Question>(size);
		for(int i = 0; i < size; i++){
			Question q;
			//user inputs true/false or multiple choice question
			System.out.print("TF or MC question? ");
			input = sc.nextLine();
			if(input.equals("TF")){
				q = new TFQuestion();
			}else{
				q = new MCQuestion();
			}
			System.out.print("Enter Question " + (i+1) + ": ");
			input = sc.nextLine();
			q.setQuestion(input);
			//if q type = MC then add multiple answer choices
			if(q.getType() == 1){
				String abc = "ABCDE";
				//get answers for letters a->e
				for(int j = 0; j < abc.length(); j++){
					System.out.print("Enter answer for " + abc.charAt(j) + ": ");
					input = sc.nextLine();
					((MCQuestion) q).addAnswer(input);
				}
			}
			//now let the user select the correct answer to the question
			System.out.print("Select correct answer (ABCDE or TF): ");
			input = sc.nextLine();
			q.setAnswer(input.charAt(0));
			qlist.add(q);
		}
	}
	
	private void getStudents(){
		Scanner sc = new Scanner(System.in);
		String input;
		int size;
		//get size of class
		System.out.print("How many students in class? ");
		input = sc.nextLine();
		size = Integer.parseInt(input);
		slist = new ArrayList<Student>(size);
		for(int i = 0; i < size; i++){
			System.out.print("Student " + (i+1) + " name: ");
			input = sc.nextLine();
			//split name by space to get first and last name
			String[] temp = input.split(" ");
			Student s = new Student();
			s.setFName(temp[0]);
			s.setLName(temp[1]);
			//set student id
			System.out.print("id: ");
			input = sc.nextLine();
			s.setID(input);
			//add new student to list
			slist.add(s);
		}
	}
	
	private void getAnswers(){
		for(int i = 0; i < numQuestions; i++){
			//if TF question dist has 2
			if(qlist.get(i).getType() == 0){
				distribution = new ArrayList<Integer>(2);
				distribution.add(0);
				distribution.add(0);
			//MC question has 5
			}else{
				distribution = new ArrayList<Integer>(5);
				distribution.add(0);
				distribution.add(0);
				distribution.add(0);
				distribution.add(0);
				distribution.add(0);
			}
			//set distribution to be 0 at first
			for(int j = 0; j < distribution.size(); j++){
				distribution.set(j, 0);
			}
			//print question
			System.out.println((i+1) + ". " + qlist.get(i));
			//print possible answers
			qlist.get(i).printAllAnswers();
			//get answers from each student
			System.out.println("Type \'x\' when all answers submitted");
			Scanner sc = new Scanner(System.in);
			String input = "";
			while(true){//while input != x
				System.out.print("Student id: ");
				input = sc.nextLine();
				if(input.equals("x")){
					break;
				}
				String id = input;
				System.out.print("Answer: ");
				input = sc.nextLine();
				char a = input.charAt(0);
				//now find student with that id and set answer to a
				for(int j = 0; j < slist.size(); j++){
					if(slist.get(j).getID().equals(id)){
						slist.get(j).setAnswer(a);
					}
				}
			}
			//all answers submitted, reveal correct answer and distribution 
			System.out.print("Correct answer: ");
			qlist.get(i).printAnswer();
			int a = qlist.get(i).getAnswer();
			//get value of distribution at location then increase it by 1
			for(int j = 0; j < slist.size(); j++){
				int c = slist.get(j).getAnswer();
				int d = distribution.get(c);
				d += 1;
				distribution.set(c, d);
				//System.out.println("answer from c " + slist.get(j));
			}
			//now add up distribution for right or wrong
			System.out.print("Correct: " + distribution.get(a));
			int wrong = 0;
			for(int j = 0; j < distribution.size(); j++){
				if(j != a){
					wrong += distribution.get(j);
				}
			}
			System.out.println("\tIncorrect: " + wrong);
			//if MC question print out A B C D E response
			String abc = "ABCDE";
			if(qlist.get(i).getType() == 1){
				for(int j = 0; j < 5; j++){
					System.out.print(abc.charAt(j) + ": " + distribution.get(j) + "\t");
				}
			}
			System.out.println();
		}
	}
}
