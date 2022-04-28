package Caculator;
import tests.MonomialTest;

import java.util.ArrayList;

public class Polynomial {

	private ArrayList<Monomial> monomials;

	final Rational zero = new Rational(0,1);

	public Polynomial() {
		this.monomials = new ArrayList<Monomial>();
	}

	public Polynomial(Polynomial p){
		this(p.rank()+1);
		for (int i=0; i<p.monomials.size(); i++){
			this.monomials.set(i,new Monomial(p.monomials.get(i)));
		}
	}

	private Polynomial(int rank) {
		this();
		for(int i =0; i <= rank; i++){
			this.monomials.add(new Monomial(i,zero));
		}
	}

	public Polynomial(Monomial m){
		this(m.getExponent()+1);
		this.monomials.set(this.rank(),m);
	}

	public Polynomial(ArrayList<Monomial> monoList) {
		this();
		int rank = findMaxRank(monoList);
		for(int i =0; i <= rank; i++){
			this.monomials.add(new Monomial(i,zero));
		}
		for(int i=0; i < monoList.size();i++){
			int j = monoList.get(i).getExponent();
			Monomial sumOfMono=  new Monomial(this.monomials.get(j).add(monoList.get(i)));
			this.monomials.set(j,sumOfMono);
		}
	}

	private int findMaxRank(ArrayList<Monomial> monoList){
		int max =0;
		for (int i=0; i < monoList.size();i++){
			max = (max < (monoList.get(i).getExponent())) ? (monoList.get(i).getExponent()) : max ;
		}
		return max;
	}

	public static Polynomial build(String input) {
		String[] scalarsStrs = input.split(" ");
		// traverse the coefficients
		Polynomial result = new Polynomial();
		for (int i = 0, exp = 0; i < scalarsStrs.length; i++) {
			if (scalarsStrs[i].length() > 0) {
				// extract a single coefficient
				String s = scalarsStrs[i];
				// convert to scalar
				Scalar scalar;
				if (s.contains("/")) {
					String[] ratio = s.split("/");
					int num = myParseInt(ratio[0]);
					int deno = myParseInt(ratio[1]);
					scalar = new Rational(num, deno);
				} else {
					int intNum = myParseInt(s);
					scalar = new Rational(intNum, 1);
				}
				// add to monomials if it is not zero
				result.monomials.add(exp,new Monomial(exp++, scalar.asRational()));
			}
		}
		return result;
	}

	private static int myParseInt(String str) {
		int result = 0;
		int sing = 1;
		if (str.charAt(0) == '-') {
			sing *= -1;
			str = str.substring(1, str.length());
		}
		for (int i = 0; i < str.length(); i++) {
			result *= 10;
			char x = str.charAt(i);
			int y = x - '0';
			result += y;
		}
		return result * sing;
	}

	public Polynomial add(Polynomial p) {
		// create the result variable
		Polynomial result = new Polynomial();
		int i;
		// sum the coefficients that match with exponent
		for (i = 0; i < monomials.size() && i < p.monomials.size(); i++) {
			Rational sumOfScal = new Rational((monomials.get(i).getCoefficient().asRational()).add(p.monomials.get(i).getCoefficient().asRational()));
			Monomial sumCoeff = new Monomial(monomials.get(i).getExponent(),sumOfScal);
			result.monomials.add(sumCoeff);
		}
		// add the remaining coefficients from the longer polynomial
		int k = i;
		while (k < monomials.size()) {
			result.monomials.add(new Monomial(monomials.get(k++)));
		}
		while (k < p.monomials.size()) {
			result.monomials.add(new Monomial(p.monomials.get(k++)));
		}
		return result;
	}

	public Polynomial mul(Polynomial p) {
		// initialize the product polynomial to store all the
		// coefficients of the result polynomial
		Polynomial prod = new Polynomial();
		// new polynomial size is decided based on the highest ranks
		// start with zero polynomial of the right rank
		int rank1 = rank();
		int rank2 = p.rank();
		for (int i = 0; i < (rank1 + rank2 + 1); i++) {
			prod.monomials.add(new Monomial(i, new Rational(zero)));
		}
		// iterate over the polynomials and add to the correct place the
		// multiply of the corresponding monomials
		for (int i = 0; i <= rank1; i++) {
			for (int j = 0; j <= rank2; j++) {
				Monomial a = monomials.get(i);
				Monomial b = p.monomials.get(j);
				prod.monomials.set(i + j, prod.monomials.get(i + j).add(a.mul(b)));
			}
		}

		return prod;
	}

	private int rank() {
		return this.monomials.size()-1;
	}

	public Scalar evaluate(Scalar s) {
		// initialize a result of zero
		Scalar result = new Rational(0,1);
		// evaluate each monomial and add to the sum
		for (int i = 0; i < monomials.size(); i++) {
			Rational adder = new Rational((this.monomials.get(i).evaluate(s.asRational())).asRational());
			result = result.asRational().add(adder);
//			Monomial a = new Monomial(this.monomials.get(i));
//			Rational x = a.evaluate(s.asRational()).asRational();
//			Rational b = new Rational(result.asRational());
//			Rational sum = new Rational(x.add(b));
//			System.out.println(x+" + "+b+" = "+sum);
//			(result.asRational()).set(sum);

		}
		return result.asRational();
	}

	public Polynomial derivative() {
		// create the result variable
		Polynomial deriv = new Polynomial(this.rank());
		// derive each monomial separately
		for (int i = 1; i < monomials.size(); i++) {
			Monomial monoDeriv = new Monomial((this.monomials.get(i)).derivative());
			deriv.monomials.set(i,monoDeriv);
		}
		return deriv;
	}

	@Override
	public String toString() {
		String res = "";
		boolean firstPlusSign = true; // first + sign does not appear
		for (int i = 0; i < monomials.size(); i++) {
			// get the sign of the monomial
			int sign = monomials.get(i).sign();
			if (sign != 0) {
				// if it's the first plus sign, ignore it
				String plusSignStr = (sign > 0) ? "+" : "";
				if (firstPlusSign) {
					plusSignStr = "";
					firstPlusSign = false;
				}
				// concatenate the sign and the monomials
				res += plusSignStr + monomials.get(i).toString();
			}
		}
		// remove unnecessary spaces
		return res.trim();
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Polynomial) {
			if ((this.rank()) == (((Polynomial) other).rank())) {
				for (int i = 0; i < this.monomials.size(); i++) {
					Monomial a = new Monomial(monomials.get(i));
					Monomial b = new Monomial(((Polynomial) other).monomials.get(i));
					if ((!((Monomial)a).equals((Monomial) b))){
					return false;
					}

				}
				return true;
			}
			//return (this.toString().equals(other.toString()));
		}
		return false;
//		return (this.toString().equals(other.toString()));
	}

}
