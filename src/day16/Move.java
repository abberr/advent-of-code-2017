package day16;

public abstract class Move {
	public abstract void doMove(char [] programs);
	
	

	public static Move creatMove(String input) {
		if (input.charAt(0) == 's') {
			return new SpinMove(Integer.parseInt(input.substring(1)));
		}
		else if (input.charAt(0) == 'x') {
			int a = Integer.parseInt(input.substring(1).split("/")[0]);
			int b = Integer.parseInt(input.split("/")[1]);
			return new ExchangeMove(a, b);
		}
		else {
			char a = input.charAt(1);
			char b = input.charAt(3);
			return new PartnerMove(a, b);
		}
	}
}
