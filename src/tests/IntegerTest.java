package tests;

import Caculator.Integer;
import org.junit.*;
import static org.junit.Assert.*;

public class IntegerTest extends Integer {

    public Integer zero, one, negOne,three, negFive;
    @Before
    public void BeforeAny (){
        zero= new Integer(0);
        one= new Integer(1);
        negOne= new Integer(-1);
        three= new Integer(3);
        negFive= new Integer(-5);
    }

    @Test
    public void testToString() {
        assertEquals("0", zero.toString());
    }

    public void testToString2() {
        assertEquals("-1", negOne.toString());
    }

    @Test
    public void Add() {
        Assert.assertEquals(zero, zero.add(zero));
    }

    public void Add2() {
        Assert.assertEquals(zero, one.add(negOne));
    }

    @Test
    public void testMul()
    {
        Assert.assertEquals(zero,three.mul(zero));
    }

    public void testMul2()
    {
        Assert.assertEquals(one,negOne.mul(negOne));
    }

    public void neg1() {
        Assert.assertEquals(zero,zero.neg());
    }

    public void neg2() {
        Assert.assertEquals(one,negOne.neg());
    }

    @Test
    public void power() {
        Assert.assertEquals(zero,zero.power(3));
    }

    public void power2() {
        Assert.assertEquals(negOne,negOne.power(3));
    }

    public void power3() {
        Assert.assertEquals(one,negOne.power(2));
    }

    public void power25() {
        Integer tFive = new Integer(25);
        Assert.assertEquals(tFive,negFive.power(2));
    }


    @Test
    public void sign1() {
        Assert.assertEquals(0,zero.sign());
    }

    @Test
    public void sign2() {
        Assert.assertEquals(-1,negFive.sign());
    }

    @Test
    public void sign3() {
        Assert.assertEquals(1,three.sign());
    }

}
