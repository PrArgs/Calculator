package tests;

import Caculator.Monomial;
import Caculator.Polynomial;
import Caculator.Rational;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PolynomialTest extends Polynomial {
    public Monomial mMinOne,mPlusOne,mZeroTwenThree,mOnlyThree,mHalf,mZero,mFour,mTwo_sqr;
    public ArrayList<Monomial> first,second,third,fourth,fifth,sixth;
    public Polynomial pfirst,psecond,pthird,pfourth,pfifth,psixth;

    @Before
    public void setUp() throws Exception {
        Monomial mMinOne = new Monomial(0, new Rational(-1, 1));
        Monomial mPlusOne = new Monomial(3, new Rational(1, 1));
        Monomial mZeroTwenThree = new Monomial(23, new Rational(0, 1));
        Monomial mOnlyThree = new Monomial(0, new Rational(3, 1));
        Monomial mHalf = new Monomial(3, new Rational(1, 2));
        Monomial mZero = new Monomial(1, new Rational(0, 1));
        Monomial mFour = new Monomial(1,new Rational(-4,1));
        Monomial mTwo_sqr = new Monomial(2,new Rational(2,1));

        first = new ArrayList<Monomial>(Arrays.asList(mMinOne));
        second = new ArrayList<Monomial>(Arrays.asList(mMinOne,mOnlyThree));
        third = new ArrayList<Monomial>(Arrays.asList(mMinOne,mPlusOne));
        fourth = new ArrayList<Monomial>(Arrays.asList(mFour,mTwo_sqr));
        fifth = new ArrayList<Monomial>(Arrays.asList(mFour,mTwo_sqr,mPlusOne));
        sixth = new ArrayList<Monomial>(Arrays.asList(mMinOne,mOnlyThree,mTwo_sqr,mHalf,mPlusOne));

        pfirst = new Polynomial(first);
        psecond = new Polynomial(second);
        pthird = new Polynomial(third);
        pfourth = new Polynomial(fourth);
        pfifth = new Polynomial(fifth);
        psixth = new Polynomial(sixth);
    }




    @Test
    public void testBuild1() {
        assertEquals(pfirst,Polynomial.build("-1"));
    }

    @Test
    public void testBuild2() {
        assertEquals(psecond,Polynomial.build("2"));
    }

    @Test
    public void testBuild3() {
        assertEquals(pthird,Polynomial.build("-1 0 0 1"));
    }

    @Test
    public void testBuild4() {
        assertEquals(pfourth,Polynomial.build("0 -4 2"));
    }

    @Test
    public void testBuild5() {
        assertEquals(pfifth,Polynomial.build("0 -4 2 1"));
    }

    @Test
    public void testBuild6() {
        assertEquals(psixth,Polynomial.build("2 0 2 3/2"));
    }


    @Test
    public void testAdd() {
        //if add didn't work half of the code wouldn't since the constructors use it
        assertEquals(Polynomial.build("-2 2  14 5"),(Polynomial.build("-1 2  4 5").add(Polynomial.build("-1 0 10"))));
    }

    @Test
    public void testMul() {
        assertEquals(Polynomial.build("0 4 -2 -1"),pfirst.mul(pfifth));
    }

    @Test
    public void testMul2() {
        Polynomial val = new Polynomial(Polynomial.build("0"));
        Polynomial val2 = Polynomial.build("0");
        assertEquals(val,pthird.mul(val2));
    }

    @Test
    public void testMul3() {
        Polynomial val = (Polynomial.build("-2 0 -2 1/2  0 2 3/2"));
        assertEquals(val,pthird.mul(psixth));
    }

    @Test
    public void testMul4() {
    }

    @Test
    public void testEvaluate() {
        assertEquals(new Rational(-1, 1),pfirst.evaluate(new Rational(8, 1)));
    }

    @Test
    public void testEvaluate1() {
        assertEquals(new Rational(22, 1),psixth.evaluate(new Rational(2, 1)));
    }

    @Test
    public void testDerivative() {
        assertEquals(Polynomial.build("0"),pfirst.derivative());
    }


    @Test
    public void testDerivative2() {
        Polynomial val = Polynomial.build("-4 4 3");
        assertEquals(val,pfifth.derivative());
    }

    @Test
    public void testDerivative3() {
        Polynomial val = (Polynomial.build("0 4 9/2"));
        assertEquals(val,psixth.derivative());
    }

}