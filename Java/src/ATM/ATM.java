package ATM;

public class ATM {
private state currentState;
public ATM(){
	currentState = new NoCard();
}
public void insert(){
	currentState.insertCard();
	if(currentState instanceof NoCard){
		currentState = new WithCard();
		System.out.println("Changed from No to With");
	}
}
public void remove(){
	currentState.removeCard();
	if(currentState instanceof WithCard){
		currentState = new NoCard();
		System.out.println("Changed from With to No");
	}
}
public void withdraw(){
	currentState.withdraw();
}
}
