package Caculator;

public class Monomial {

    private int exponent;
    private Scalar coefficient;

    private static Rational  zero = new Rational(0,1);

    public Monomial() {// Default constructor
        this.exponent = 1;
        Rational tmp = new Rational();// set the coefficient to be rational zero
    }

    public Monomial(int exp, Scalar scal) {//constructor
        this.exponent = exp;// set the coefficient to be rational zero
        this.coefficient = new Rational(scal.asRational());// set coefficient to a Rational num regardless of scal type
    }

    public Monomial(Monomial mono){ // Copy constructor
        this.exponent = mono.exponent;
        this.coefficient= new Rational(mono.coefficient.asRational());
    }

    public Monomial add(Monomial m) {
        if ((this.exponent == m.exponent)){
            Monomial result = new Monomial(this);
            result.coefficient = new Rational(((m.coefficient).asRational()).add((this.coefficient)));
            return result;
        }
        return null;
    }

    public Monomial mul(Monomial m){
        Monomial result = new Monomial(this);
//        System.out.println(this.coefficient+" * "+m.coefficient+" = "+((m.coefficient).asRational()).mul((this.coefficient).asRational()));
        result.coefficient = ((m.coefficient).asRational()).mul((this.coefficient).asRational());
        result.exponent += m.exponent;
        return result;
    }

    public Scalar evaluate(Scalar s){
        if ((s.asRational().getNumerator() == 0) || (this.coefficient.asRational().getNumerator() == 0)){
            return new Rational(0,1);
        }
        if (this.getExponent() == 0){
            return ((this.coefficient).asRational());
        }
        Rational xExp = new Rational((s.asRational().power(this.getExponent())));
        Rational result = new Rational(xExp.mul((this.coefficient).asRational()));
        return result;
    }

    public Monomial derivative(){
        if (exponent == 0){
            this.coefficient.asRational().set(zero);
            return this;
        }
        else{
        int a = this.coefficient.asRational().getNumerator()*this.getExponent();
        int b = this.coefficient.asRational().getDenominator();
        int c = this.getExponent()-1;
        Rational res = new Rational(a,b);
        return new Monomial(c,res.reduce());
        }
    }

    public void setExponent(int exp){
        this.exponent = exp;
    }

    public void setCoefficient(Scalar s){
        this.coefficient = new Rational(s.asRational());
    }

    public int getExponent() {
        return this.exponent;
    }

    public Rational getCoefficient(){
        return new Rational(this.coefficient.asRational());
    }

    public int sign(){
        return this.coefficient.sign();
    }

    @Override
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

    @Override
    public boolean equals(Object other){
        if(other instanceof Monomial){
            if((this.coefficient.equals(((Monomial) other).coefficient)) &&
                    this.coefficient.asRational().equals(zero)){
                return true;
            }
            return ((this.coefficient.equals(((Monomial) other).coefficient)) &&
                    (this.exponent==((Monomial) other).exponent));
        }
        return false;
    }

     static boolean bothZero(Monomial other,Monomial me) {
        if (((other.coefficient.asRational()).equals(zero)) && // if both coefficients equals 0 return true
                ((me.coefficient.asRational()).equals(zero))) {
            return true;
        }
        return false;
    }

    private Monomial findTheZero(Monomial other){
            if(((other).coefficient.asRational().equals(zero))){
                return other;
            }
            if(this.coefficient.asRational().equals(zero)){
                return this;
            }
            return null;
        }



}