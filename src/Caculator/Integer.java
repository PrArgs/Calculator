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

    public String toString(){
        return ""+this.number;
    }

    public int get(){
        return this.number;
    }

    @Override
    public boolean equals(Scalar s) {
        return this.equals(s);
    }


    public boolean equals(Integer s) {
        return s.number==this.number;
    }


    public void set(int num) {
        this.number = num;
    }

    @Override
    public Scalar add(Scalar s) {
        return this.add(s);
    }

    public Scalar add(Integer s) {
        Integer result = new Integer(this);
        result.number += s.number;
        return result;
    }

    @Override
    public Scalar mul(Scalar s) {
        return null;
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
        Scalar result = new Integer(this);
        for (int i = 1; i < exponent; i++){
            result.mul(this);
        }
        return result;
    }



    @Override
    public int sign() {
        if (this.number ==0) {
            return 0;
        }
        return (this.number < 0) ? -1 : 1;
    }
}

