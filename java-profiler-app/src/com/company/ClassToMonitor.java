package com.company;

import java.util.Arrays;

public class ClassToMonitor {
    public void foo() {
        int b = 1, c = 2;
        int a[] = {b, c};
        System.out.print(Arrays.toString(a));
    }
}
