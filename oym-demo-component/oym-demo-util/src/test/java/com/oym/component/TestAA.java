package com.oym.component;

/**
 * @author zyd
 * @date 2020/1/27 16:30
 * @desc
 */
public class TestAA {
    public static void main(String[] args) {
        TestAA testAA = new TestBB();
        testAA.tt();
    }

    public void tt(){
        this.aa();
    }

    public void aa() {
        System.out.println("aa");
    }
}
