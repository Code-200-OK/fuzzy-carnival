package ATM;

public class WithCard implements state{

	@Override
	public void insertCard() {
		// TODO Auto-generated method stub
		System.out.println("Already Inserted");
	}

	@Override
	public void removeCard() {
		// TODO Auto-generated method stub
		System.out.println("Removed");
	}

	@Override
	public void withdraw() {
		// TODO Auto-generated method stub
		System.out.println("Withdrawn");
		
	}

}
