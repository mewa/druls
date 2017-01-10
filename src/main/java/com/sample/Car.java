package com.sample;

public class Car {
	public static enum Klass {
		A, B, D
	};

	@Override
	public String toString() {
		return "Class " + klass + " Car " + Integer.toHexString(hashCode());
	}
	
	public Klass klass;
	public boolean available = true;

	public static float ClassPricing(Klass klass, boolean threeDaysPlus) {
		switch (klass) {
		case A:
			return threeDaysPlus ? 59 : 69;
		case B:
			return threeDaysPlus ? 69 : 79;
		case D:
			return threeDaysPlus ? 79 : 89;
		}
		return Integer.MIN_VALUE;
	}

	public static float DistancePricing(boolean threeDaysPlus) {
		return threeDaysPlus ? 0.5f : 1f;
	}

	public static class ACar extends Car {
		public ACar() {
			klass = Klass.A;
		}
	}

	public static class BCar extends Car {
		public BCar() {
			klass = Klass.B;
		}
	}

	public static class DCar extends Car {
		public DCar() {
			klass = Klass.D;
		}
	}
}
