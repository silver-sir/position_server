package com.tct.positionApp.util;

public class MathUtil {
    public double distance(double x1,double y1,double x2,double y2){
        double pk = (float) (180/3.14169);

        double a1 = y1 / pk;
        double a2 = x1 / pk;
        double b1 = y2 / pk;
        double b2 = x2 / pk;

        double t1 = Math.cos(a1)*Math.cos(a2)*Math.cos(b1)*Math.cos(b2);
        double t2 = Math.cos(a1)*Math.sin(a2)*Math.cos(b1)*Math.sin(b2);
        double t3 = Math.sin(a1)*Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);

        return 6378000*tt;

    }
}
