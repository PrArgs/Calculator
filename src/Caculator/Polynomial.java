package Caculator;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {
	private List<Monomial> monomials;

	public Polynomial() {
		this.monomials = new ArrayList<Monomial>();

	}

	public static Polynomial build(String input) {
		throw new RuntimeException("Not yet implemented");
	}

	public Polynomial add(Polynomial p) {
		throw new RuntimeException("Not yet implemented");
	}

	public Polynomial mul(Polynomial p) {
		throw new RuntimeException("Not yet implemented");
	}

	private int rank() {
		//return monomials.get(monomials.size() - 1).getExponent();
		throw new RuntimeException("Not yet implemented");
	}

	public Scalar evaluate(Scalar s) {
		throw new RuntimeException("Not yet implemented");
	}

	public Polynomial derivative() {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public String toString() {
		throw new RuntimeException("Not yet implemented");
	}
}
