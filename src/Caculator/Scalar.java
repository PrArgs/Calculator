package Caculator;

public interface Scalar<peblic> {

    public Scalar add(Scalar s);
    public Scalar mul(Scalar s);
    public Scalar neg();
    public Scalar power(int exponent);
    public  int sign();
    public int get();
    public boolean equals(Scalar s);
}
