package tests;

import Caculator.Rational;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RationalTest extends Rational {

    public Rational sixT,one,negOne,Tfive,negThreeT,threeO,zero;

     @Before
    public void setUp() {

         sixT = new Rational(6, 2);
         threeO = new Rational(3, 1);
         one = new Rational(1, 1);
         negOne = new Rational(1, -1);
         Tfive = new Rational(2, 5);
         negThreeT = new Rational(-3, 1);
         zero = new Rational(0, 1);

    }


    @Test
    public void testReduce() {
        assertEquals(zero, zero.reduce());
    }

    @Test
    public void testReduce2() {
        assertEquals(threeO, sixT.reduce());
    }

    @Test
    public void testReduce3() {
         Rational realNeg = new Rational(-1,1);
        assertEquals(realNeg, negOne.reduce());
    }

    @Test
    public void testToString() {
         assertEquals("0",zero.toString());
    }
    @Test
    public void testToString2() {
        assertEquals("-3",negThreeT.toString());
    }
    @Test
    public void testToString3() {
        assertEquals("2/5",Tfive.toString());
    }

    @Test
    public void testAdd() {
        Rational tricky = new Rational(17,5);
         assertEquals(tricky,Tfive.add(threeO));
    }

    @Test
    public void testAdd1() {
        assertEquals(zero,one.add(negOne));
    }

    @Test
    public void testAdd2() {
        assertEquals(negOne,negOne.add(zero));
    }

    @Test
    public void testAdd3() {
        Rational tricky = new Rational(-4,1);
        assertEquals(tricky,negOne.add(negThreeT));
    }

    @Test
    public void testMul() {
         assertEquals(negOne,negOne.mul(one));
    }

    @Test
    public void testMul2() {
        assertEquals(one,negOne.mul(negOne));
    }

    @Test
    public void testMul3() {
         Rational tricky =new Rational(9,1);
        assertEquals(tricky,sixT.mul(threeO));
    }

    @Test
    public void testMul4() {
        assertEquals(zero,Tfive.mul(zero));
    }

    @Test
    public void testNeg() {
         assertEquals(negOne,one.neg());
    }

    @Test
    public void testNeg2(){
        assertEquals(zero,zero.neg());
    }

    @Test
    public void testNeg3() {
         Rational tricky = new Rational(-3,1);
        assertEquals(tricky,sixT.neg());
    }

    @Test
    public void testPower() {
         assertEquals(one,one.power(3));
    }

    @Test
    public void testPower2() {
        assertEquals(negOne,negOne.power(3));
    }

    @Test
    public void testPower3() {
        Rational tricky = new Rational(4,5);
        assertEquals(tricky,Tfive.power(2));
    }

    @Test
    public void testSign() {
         assertEquals(1,one.sign());
    }

    @Test
    public void testSign2() {
        assertEquals(-1,negOne.sign());
    }

    @Test
    public void testSign3() {
        assertEquals(0,zero.sign());
    }

    @Test
    public void tetstoString() {
        assertEquals("1/2",new Rational(2,4).toString());
    }

}