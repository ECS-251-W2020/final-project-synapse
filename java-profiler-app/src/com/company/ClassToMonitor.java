package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class ClassToMonitor {
    public void foo() {
        int b = 1, c = 2;
        int a[] = {b, c};
        System.out.print(Arrays.toString(a));
    }
    public void bar() {
        // create an empty array list with an initial capacity
        ArrayList<Integer> arrlist = new ArrayList<Integer>(5);

        // use add() method to add elements in the list
        arrlist.add(15);
        arrlist.add(22);
        arrlist.add(30);
        arrlist.add(40);

        // adding element 25 at third position
//        arrlist.add(2,25);

        // let us print all the elements available in list
        for (Integer number : arrlist) {
            System.out.println("Number = " + number);
        }
    }
}
