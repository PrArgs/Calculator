package Caculator;

public class Monomial {

    private int exponent;
    private Scalar coefficient;

    public Monomial() {// Default constructor
        this.exponent = 1;
        Rational tmp = new Rational();// set the coefficient to be rational zero
    }

    public Monomial(int exp, Scalar scal) {//constructor
        this.exponent = exp;
        Rational tmp = new Rational();// set the coefficient to be rational zero
        this.coefficient = tmp.add(scal);// set coefficient to a Rational num regardless of scal type
    }

    public Monomial(Monomial mono){ // Copy constructor
        this.exponent = mono.exponent;
        this.coefficient= mono.coefficient;
    }



    public Monomial add(Monomial m) {
        if (this.exponent == m.exponent){
            Monomial result = new Monomial(this);
            result.coefficient = result.coefficient.add(m.coefficient);// no worries since all coefficients are Rational
            return result;
        }
        return null;
    }

    Monomial mul(Monomial m){
        Monomial result = new Monomial(this);
        result.coefficient = result.coefficient.mul(m.coefficient);
        result.exponent += m.exponent;
        return result;
    }

    public Scalar evaluate(Scalar s){
        Scalar result = new Rational();
        result.add(s);
        result.power(this.exponent);
        result.mul(this.coefficient);
        return result;
    }

    public Monomial derivative(){
        Monomial result = new Monomial(this);
        Rational multi = new Rational(this.exponent,1);
        this.coefficient.mul(multi);
        this.exponent -= 1;
        return result;
    }

    public int sign(){
        return this.coefficient.sign();
    }

    public String toString(){
        if (this.exponent == 0){
            return this.coefficient.toString();
        }
        if (this.coefficient.toString().equals("-1")){
            return "-"+"X^"+this.exponent;
        }
        if (this.coefficient.toString().equals("1")){
            return "X^"+this.exponent;
        }
        return ""+this.coefficient.toString()+"X^"+this.exponent;
    }
}