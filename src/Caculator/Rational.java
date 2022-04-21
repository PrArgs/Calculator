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

//     private Rational(Integer inti){
//        this(inti.get() ,1);
//    }


    public Rational(int num, int den) { //constructor
        this.numerator = num;
        if (den != 0) {
            this.denominator = den;
        } else {
            this.denominator = -1;
        }

    }

    public Rational reduce() {
        Rational result = new Rational();
        if (this.numerator%this.denominator == 0){
            result.numerator = this.numerator/this.denominator;
            result.denominator = 1;
        }
        else {
            int dev = this.findMinDiv();
            result.numerator = this.numerator/dev;
            result.denominator = this.denominator/dev;
        }
        result.numerator = result.sign()* Math.abs(result.numerator);
        result.denominator= Math.abs(result.denominator);
        return result;
    }

    private int findMinDiv(){
        int result = 1;
        int min = Math.min(this.denominator,this.numerator);
        int sqr = (int)Math.sqrt(min);
        for (int i = sqr; i > 0 ; i--){
            if((this.denominator%i == 0) && (this.numerator%i ==0))
                return i;
        }
        return result;
    }

    public String toString() {
        Rational result = new Rational(this.reduce());
        if (result.denominator == 1) {
            return  ""+result.numerator;
        }
        return ""+result.numerator+"/"+result.denominator;
    }

    @Override
    public Scalar add(Scalar s) {
        return this.add(s);
    }


    public Scalar add(Rational s) {
        Rational result = new Rational(this);
        result.numerator *= s.denominator;
        result.denominator *= s.denominator;
        s.numerator *= result.denominator;
        s.denominator *= result.denominator;
        result.numerator += s.numerator;
        result.reduce();
        return result;
    }

    public Scalar add(Integer inti) {
//        Rational result = new Rational(inti);
        int num = this.denominator*inti.get();
        Rational result = new Rational(this);
        result.numerator += num;
        return result.reduce();
    }

    @Override
    public Scalar mul(Scalar s) {
        return this.add(s);
    }

    public Scalar mul(Rational s) {
        Rational result = new Rational(this);
        result.numerator *= s.numerator;
        result.denominator *= s.denominator;
        result.reduce();
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
            result.mul(this);
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
    public int get() {
        float result = this.numerator/this.denominator;
        return Math.round(result);
    }

    @Override
    public boolean equals(Scalar s) {
        return this.equals(s);
    }

    public boolean equals(Rational s) {
        Rational a = new Rational(s.reduce());
        Rational b = new Rational(this.reduce());
        return ((b.denominator == a.denominator)&& (b.numerator == a.numerator));
    }
}

