package com.gaku.java.lambda;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LambdaTest {

    public static void main(String[] args) {

        int[] intArray = {1,2,3,3,3,2};
        Integer[] objectArray = {1,2,3,3,3,2};
        List<Integer> objectList = Arrays.asList(1,2,3, 3,3, 2);


        // 分组，计算组内元素个数
        Map<Integer, Long> a = Arrays.stream(intArray).collect(()->new HashMap<Integer, Long>(), (xp, b)->{
            Long ppp = xp.get(b);
            if (ppp == null){
                xp.put(b, (long)1);
            }else{
                xp.put(b, (ppp + 1));
            }
        }, null); // intArray对应的是intStream
        Map<Integer, Long> b = Arrays.stream(objectArray).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<Integer, Long> c = objectList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//        System.out.println(a);
        System.out.println("分组，计算组内元素个数："+a);
        System.out.println("分组，计算组内元素个数："+b);
        System.out.println("分组，计算组内元素个数："+c);

        // 计算元素个数
        System.out.println("统计个数：" + Arrays.stream(intArray).count());
        System.out.println("统计个数：" + Arrays.stream(objectArray).count());
        System.out.println("统计个数：" + objectList.stream().count());


        // 求和
        System.out.println("求和：" + Arrays.stream(intArray).reduce(0, (x,y) -> x + y));
        System.out.println("求和：" + Arrays.stream(objectArray).reduce(0, (x,y) -> x + y));
        System.out.println("求和：" + objectList.stream().reduce(0, (x,y) -> x + y));





    }
}
