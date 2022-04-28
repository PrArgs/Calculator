package Caculator;

public class Integer implements Scalar {

    private int number;

    //constructor
    public Integer(){
        this.number=0;
    }

    public Integer(Integer copy){ //copy constructor
        this.number=copy.number;
    }

    public Integer(int val){ //constructor
        this.number=val;
    }

    public int get(){
        return this.number;
    }

    public void set(int num) {
        this.number = num;
    }

    @Override
    public boolean equals(Object s) {
        if (s instanceof Integer) {
            return ((Integer) s).number==this.number;
        }
        if(s instanceof Rational){
            if(((Rational)s).getDenominator()==1){
                return ((Rational) s).getNumerator()==this.number;
            }
        }
        System.out.println("Not a scalar.....");
        return false;
    }

    @Override
    public Scalar add(Scalar s) {
        return this.add(s);
    }

    public Integer add(Integer s) {
        Integer result = new Integer(this);
        result.number += s.number;
        return result;
    }

    @Override
    public Scalar mul(Scalar s) {
        return this.mul(s);
    }

    public Integer mul(Integer s) {
        Integer result = new Integer(this);
        result.number *= s.number;
        return result;
    }

    @Override
    public Scalar neg() {
        Scalar result = new Integer(this);
        Integer neg = new Integer(-1);
        result= this.mul(neg);
        return result;
    }

    @Override
    public Scalar power(int exponent) {
        int expRes = this.number;
        for (int i = 1; i < exponent; i++){
            expRes *= this.number;
        }
        Scalar result = new Integer(expRes);
        return result;
    }

    @Override
    public int sign() {
        if (this.number ==0) {
            return 0;
        }
        return (this.number < 0) ? -1 : 1;
    }

    @Override
    public Rational asRational() {
        return new Rational(this.number,1);
    }

    @Override
    public String toString(){
        return ""+this.number;
    }
}

