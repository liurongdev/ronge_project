package com.ronge.demo;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liurong
 * @date 2021/1/26 22:03
 */
public class Demo2 {

    private ReentrantLock lock = new ReentrantLock();

    private Map<Integer, Integer> resultMap = new HashMap<>();

    private volatile int moneyCount = 1000;

    private int personCount = 7;

    private double MaxPercent = 0.5d;

    private CountDownLatch countDownLatch = null;

    @Test
    public void contextLoads() throws InterruptedException {
        System.out.println("hello tencent");
        HashMap<Integer,Integer> map=new HashMap<>();
        map.remove(10);
    }

    class Point{
        int i;
        int j;
        int value;
        public Point(int i,int j,int value){
            this.i=i;
            this.j=j;
            this.value=value;
        }
    }
    public int getShrotLength(int[][] data){
        if (data == null || data.length == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE, n = data.length, m = data[0].length;
        Stack<Point> stack = new Stack<>();
        for (int j = 0; j < data[0].length; j++) {
            if (data[0][j] != 0) {
                Point point = new Point(0, j, data[0][j]);
                stack.push(point);
            }
        }
        while (stack.size() > 0) {
            Point point = stack.pop();
            if (point.i == n - 1) {
                ans = Math.min(ans, point.value);
            }
            if (point.i + 1 < n) {
                if (point.j - 1 >= 0) {
                    stack.push(new Point(point.i + 1, point.j - 1, data[point.i + 1][point.j - 1] + data[point.i][point.j]));
                }
                if (point.j + 1 < m) {
                    stack.push(new Point(point.i + 1, point.j + 1, data[point.i + 1][point.j + 1] + data[point.i][point.j]));
                }
            }
        }
        return ans;
    }
}
