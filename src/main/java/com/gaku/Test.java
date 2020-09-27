package com.gaku;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Collectors.*;

public class Test {

    public static void main(String[] args) {



//        Map<Integer, Integer> map = Arrays.stream(abc).
//                collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
//
//        Map<Integer, Integer> map1 = Arrays.stream(abc).
//                collect(Function.identity(), );

        System.out.println("================");

        Test test = new Test();

        char[] a = {'A', 'A', 'B', 'B', 'C', 'C', 'D', 'D', 'E', 'E', 'F', 'F', 'G', 'G', 'H', 'H', 'I', 'I', 'J', 'J', 'K', 'K', 'L', 'L', 'M', 'M', 'N', 'N', 'O', 'O', 'P', 'P', 'Q', 'Q', 'R', 'R', 'S', 'S', 'T', 'T', 'U', 'U', 'V', 'V', 'W', 'W', 'X', 'X', 'Y', 'Y', 'Z', 'Z'};
        char[] b = {'A', 'A', 'B', 'B', 'C', 'C', 'D', 'D', 'E', 'E', 'F', 'F'};

        int c = test.leastInterval(b, 2);
        System.out.println(c);

        System.out.println(test.leastInterval11(b, 2));


    }

    public int leastInterval11(char[] tasks, int n) {
        int[] count = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            count[tasks[i]-'A']++;
        }//统计词频
        Arrays.sort(count);//词频排序，升序排序，count[25]是频率最高的
        int maxCount = 0;
        //统计有多少个频率最高的字母
        for (int i = 25; i >= 0; i--) {
            if(count[i] != count[25]){
                break;
            }
            maxCount++;
        }
        //公式算出的值可能会比数组的长度小，取两者中最大的那个
        return Math.max((count[25] - 1) * (n + 1) + maxCount , tasks.length);
    }

    public int leastInterval(char[] tasks, int n) {

        if (tasks == null || tasks.length == 0) {
            return 0;
        }

        Map<Character, Integer> map = toMap(tasks);
        return handle(map, n);
    }

    private int handle(Map<Character, Integer> map, int n) {
        if (map.isEmpty()) {
            return 0;
        }

        System.out.print(map);

        Character c = getLongest(map);
        Integer count = map.get(c);
        map.remove(c);

        int sum = count + (count - 1) * n;

        System.out.print("当前值：");
        System.out.println(sum);

        for (int i = 1; i <= count - 1; i++) {

            int curCount = n;
            Iterator<Character> it = map.keySet().iterator();
            while (it.hasNext()) {
                Character key = it.next();
                Integer value = map.get(key);
                if (curCount > 0) {
                    int thisTime = value - 1;
                    if (thisTime == 0) {
                        it.remove();
                    } else {
                        map.put(key, thisTime);
                    }

                    curCount--;
                }else{
                    break;
                }
            }
        }


        int temp = handle(map, n);


        return sum + temp;


    }

    private Character getLongest(Map<Character, Integer> map) {
        Character c = null;
        Integer count = null;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (c == null) {
                c = entry.getKey();
                count = entry.getValue();
            } else {

                if (entry.getValue() > count) {
                    c = entry.getKey();
                    count = entry.getValue();
                }
            }
        }

        return c;
    }

    private Map<Character, Integer> toMap(char[] tasks) {

        Map<Character, Integer> map = new HashMap();


        for (char task : tasks) {
            Integer count = map.get(task);
            if (count == null) {
                map.put(task, 1);
            } else {
                map.put(task, count + 1);
            }
        }

        return map;

    }
}
