package Caculator;

public class Rational implements Scalar {

    private int numerator;
    private int denominator;

    public Rational() {// deafult constructor
        this.numerator = 0;
        this.denominator = 1;
    }

    public Rational(Rational copy) {// copy constructor
        this.numerator = copy.numerator;
        this.denominator = copy.denominator;
    }

    public Rational(Scalar s){
       this.set(s.asRational());
    }

    public Rational(int num, int den){ //constructor
        this.numerator = num;
        if (den != 0) {
            this.denominator = den;
        }
        else {
            this.denominator = -1;
            System.out.println(" AYHA.... Why divided by 0? "+this+" this is your obj now");
        }

    }

    public float get() {
        float result = this.numerator/this.denominator;
        return result;
    }

    public int getNumerator(){
        return this.numerator;
    }

    public int getDenominator(){
        return this.denominator;
    }

    public void setNumerator(int i){
        this.numerator = i;
    }

    public void setDenominator(int i){
        this.denominator =i;
    }

    public void set(Rational other){
        Rational setter = new Rational(other.reduce());
        this.numerator = setter.numerator;
        this.denominator = setter.denominator;
    }

    public Rational reduce() {
        Rational result = new Rational();
        if (this.numerator%this.denominator == 0){
            result.numerator = this.numerator/this.denominator;
            result.denominator = 1;
        }
        else {
            int dev = this.findMinDiv();
            result.setNumerator(this.numerator/dev);
            result.setDenominator(this.denominator/dev);
        }
        if (this.sign() != 0) {
            result.numerator = this.sign() * Math.abs(result.numerator);
            result.denominator = Math.abs(result.denominator);
        }
        return result;
//
            
//        int n = this.numerator, d = this.denominator, max,sign;
//        sign = this.sign();
//        if(sign !=0 ){
//            n = Math.abs(n);
//            d = Math.abs(d);
//        }
//        max = (n<d)? d: n;
//        int maxDiv = 0;
//        for (int i = max; i >= 2; i--) {
//            if (n % i == 0 && d % i == 0) {
//                maxDiv = i;
//                break;
//            }
//        }
//
//        // divide the largest common denominator out of numerator, denominator
//        if (maxDiv != 0) {
//            n /= maxDiv;
//            d /= maxDiv;
//        }
//        if(sign != 0){
//            n *= sign;
//        }
//        return new Rational(n,d);
    }

    private int findMinDiv(){
        int result = 1;
        int max = (this.denominator>this.numerator)? this.denominator : this.numerator ;
        for (int i = max; i >= 2 ; i--){
            if((this.denominator%i == 0) && (this.numerator%i ==0))
                return i;
        }
        return result;
    }

    @Override
    public Scalar add(Scalar s) {
        return this.add(s.asRational());
    }

    public Rational add(Rational s) {
        if(this.denominator == 0 || s.denominator == 0){
            return new Rational(0,1);}
        int a,b;
        a = this.getNumerator()*s.getDenominator();
        b = this.getDenominator()*s.getDenominator();
        a += this.getDenominator()*s.getNumerator();
        Rational res = new Rational(a,b);
        return res.reduce();


    }

    public Rational asRational(){
        return this;
    }

    @Override
    public Scalar mul(Scalar s) {
        Rational result = new Rational(this);
        result.numerator *= s.asRational().numerator;
        result.denominator *= s.asRational().denominator;
        result.set(result.reduce());
        return result;
    }

    @Override
    public Scalar neg() {
        Rational result = new Rational(this);
        result.numerator *= -1;
        result.reduce();
        return result;
    }

    @Override
    public Scalar power(int exponent) {
        Rational result = new Rational(this);
        for (int i = 1; i < exponent; i++) {
           result.set(result.mul(this).asRational());
        }
        return result;
    }

    @Override
    public int sign() {
        if (this.numerator == 0) {
            return 0;
        }
        return (this.denominator * this.numerator < 0) ? -1 : 1;

    }

    @Override
    public String toString() {
        Rational result = new Rational(this.reduce());
        if (result.denominator == 1) {
            return  ""+result.numerator;
        }
        return ""+result.numerator+"/"+result.denominator;
    }

    @Override
    public boolean equals(Object s) {
        this.set(this.reduce());// make sure the sing is in the numerator
        if (s instanceof Scalar){
            Rational other = new Rational(((Scalar) s).asRational());
            return ((this.numerator/this.denominator) == (other.numerator/other.denominator));
        }
        return false;
    }
}

