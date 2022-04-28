package tests;

import Caculator.Monomial;
import Caculator.Rational;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonomialTest extends Monomial {

    public Monomial negOne,one,ZeroTwenThree,onlyThree,half,zero;

    @Before
    public void setUp() {

        negOne = new Monomial(3, new Rational(-1,1));
        one = new Monomial(3, new Rational(1,1));
        ZeroTwenThree = new Monomial(23, new Rational(0,1));
        onlyThree =new Monomial(0, new Rational(3,1));
        half = new Monomial(3,new Rational(1,2));
        zero = new Monomial(1,new Rational(0,1));
    }


    @Test
    public void testAdd() {
        assertEquals(zero,one.add(negOne));
    }

    @Test
    public void testAdd2() {
        Monomial adder = new Monomial(23,new Rational(1,2));
        assertEquals(adder,ZeroTwenThree.add(adder));
    }

    @Test
    public void testAdd3() {
        assertEquals(zero,one.add(negOne));
    }


    @Test
    public void testAdd4() {
        Monomial adder = new Monomial(23,new Rational(1,2));
        assertEquals(adder,adder.add(ZeroTwenThree));
    }

    @Test
    public void testMul() {
        Monomial mult = new Monomial(6, new Rational(-1,1));
        assertEquals(mult,one.mul(negOne));
    }

    @Test
    public void testMul2() {
        Monomial mult = new Monomial(23, new Rational(0,1));
        assertEquals(mult,ZeroTwenThree.mul(negOne));
    }

    @Test
    public void testEvaluate() {
        Rational same = new Rational(1,1);
        assertEquals(onlyThree.getCoefficient(),onlyThree.evaluate(same));
    }

    @Test
    public void testEvaluate1() {
        assertEquals(ZeroTwenThree.getCoefficient(),ZeroTwenThree.evaluate(new Rational(1,1)));
    }

    @Test
    public void testEvaluate2() {
        Rational first = new Rational(27,2);
        Rational second = new Rational(3,1);
        assertEquals(first,half.evaluate(second));
    }



    @Test
    public void testDerivative() {
        Monomial der =new Monomial(0, new Rational(0,1));
        assertEquals(der,onlyThree.derivative());
    }

    @Test
    public void testDerivative1() {
        Monomial der =new Monomial(2, new Rational(3,2));
        assertEquals(der,half.derivative());
    }
}