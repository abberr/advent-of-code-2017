package day16;

public class ExchangeMove extends Move{
	
	private int a, b;
	
	public ExchangeMove(int a, int b) {
		this.a = a;
		this.b = b;		
	}

	@Override
	public void doMove(char[] programs) {
		char temp = programs[a];
		programs[a] = programs[b];
		programs[b] = temp;
	}
}
