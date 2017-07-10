package ATM;

public class NoCard implements state{

	@Override
	public void insertCard() {
		// TODO Auto-generated method stub
		System.out.println("Inserted");
	}

	@Override
	public void removeCard() {
		// TODO Auto-generated method stub
		System.out.println("No card to remove");
	}

	@Override
	public void withdraw() {
		// TODO Auto-generated method stub
		System.out.println("No card to withdraw");
		
	}

}
