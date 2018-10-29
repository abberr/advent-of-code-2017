package day16;

public class PartnerMove extends Move{

	private char a;
	private char b;
		
	public PartnerMove(char a, char b) {
		this.a = a;
		this.b = b;
	}



	@Override
	public void doMove(char[] programs) {
		int aIndex = -1;
		for (int i = 0; i < programs.length; i++) {
			if (programs[i] == a) {
				aIndex = i;
			}
		}
		for (int i = 0; i < programs.length; i++) {
			if (programs[i] == b) {
				programs[i] = a;
				programs[aIndex] = b;
			}
		}
	}

}
