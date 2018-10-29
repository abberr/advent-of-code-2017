package day15;

public class Day15 {

	public static final long FACTOR_A = 16807;
	public static final long FACTOR_B = 48271;
	public static final long A_DIVISIBLE_BY = 4;
	public static final long B_DIVISIBLE_BY = 8;
	public static final long DIVISOR = 2147483647;

	public static int solveP1(int a, int b) {
		long valueA = a;
		long valueB = b;
		int matchingCounter = 0;
		for (int i = 0; i < 40000000; i++) {
			valueA = generateValue(valueA, FACTOR_A);
			valueB = generateValue(valueB, FACTOR_B);
			if (lowest16bitsMatch(valueA, valueB)) {
				matchingCounter++;
			}
		}
		return matchingCounter;
	}

	private static int solveP2(long a, long b) {
		long valueA = a;
		long valueB = b;
		int matchingCounter = 0;
		for (int i = 0; i < 5000000; i++) {
			while (!isDivisibleBy(valueA = generateValue(valueA, FACTOR_A), A_DIVISIBLE_BY)) {
			}
			while (!isDivisibleBy(valueB = generateValue(valueB, FACTOR_B), B_DIVISIBLE_BY)) {
			}
			if (lowest16bitsMatch(valueA, valueB)) {

				matchingCounter++;
			}
		}

		return matchingCounter;
	}

	private static long generateValue(long value, long factor) {
		long product = value * factor;
		value = product % DIVISOR;
		return value;
	}

	private static boolean isDivisibleBy(long valueA, long divisor) {
		return (valueA % divisor == 0);
	}

	private static boolean lowest16bitsMatch(long valueA, long valueB) {
		byte[] aByteArray = longToBytes(valueA);
		byte[] bByteArray = longToBytes(valueB);
		return (aByteArray[6] == bByteArray[6] && aByteArray[7] == bByteArray[7]);
	}

	public static byte[] longToBytes(long l) {
		byte[] result = new byte[8];
		for (int i = 7; i >= 0; i--) {
			result[i] = (byte) (l & 0xFF);
			l >>= 8;
		}
		return result;
	}

	public static void main(String[] args) {
		int inputA = 512;
		int inputB = 191;
		System.out.println(solveP1(inputA, inputB));
		System.out.println(solveP2(inputA, inputB));
//		System.out.println(solveP2(65, 8921));
	}

}
