package day16;

public class SpinMove extends Move{

	private int spinLength;
	
	public SpinMove(int spinLength) {
		this.spinLength = spinLength;
	}
	
	@Override
	public void doMove(char[] programs) {
		char [] first = new char[programs.length - spinLength];
		char [] last = new char[spinLength];		
		System.arraycopy(programs, 0, first, 0, first.length);
		System.arraycopy(programs, programs.length - spinLength, last, 0, spinLength);
		System.arraycopy(last, 0 , programs, 0, last.length);
		System.arraycopy(first, 0 , programs, last.length, first.length);	
	}
	
}
