package be.ucll.vansteelandttim;

import java.util.Random;


public class Test {

    private int number;

    public Test() {
        this.number = new Random().nextInt(20);
    }

    public String test(int parameter) {
        String result = "False";
        if (parameter == this.number) {result = "True";}
        return result;
    }

}
