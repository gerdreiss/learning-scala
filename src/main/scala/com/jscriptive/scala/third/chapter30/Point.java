package com.jscriptive.scala.third.chapter30;

import java.util.HashSet;
import java.util.Set;

public class Point {
    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point other = (Point) o;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public static void main(String[] args) {
        Point p = new Point(1, 2);
        Set<Point> set = new HashSet<>();
        set.add(p);
        System.out.println("set contains p: " + set.contains(p));
        p.x += 1;
        System.out.println("set still contains p: " + set.contains(p)); // <-- damn!!!
    }
}
