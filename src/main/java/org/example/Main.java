package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        List<String> names = Arrays.asList("john","bill","andrew","paul","zack","sten","will");
        System.out.println("________Task1________");
        String oddNames = oddNamesFilter(names);
        System.out.println(oddNames);
        System.out.println("________Task2________");
        List<String> sortedUppercaseNames=reverseSortedUppercaseName(names);
        System.out.println(sortedUppercaseNames);
        System.out.println("________Task3________");
        String[] arr={"1, 2, 0", "4, 5"};
        System.out.println(Arrays.toString(arr));
        String numbers = Stream.of(arr)
                .flatMap(input -> Pattern.compile(",").splitAsStream(input))
                .map(s -> s.trim())
                .sorted()
                .map(n -> n.toString())
                .collect(Collectors.joining(", "));
        System.out.println(numbers);
        System.out.println("________Task4________");
        List<Long> randomNumberList = randomAlgorithm(25214903917L, 11L, (long) Math.pow(2,48))
                .limit(10).peek(System.out::println).collect(Collectors.toList());
        System.out.println("________Task5________");
        Stream<String> firstStream = Stream.of("john", "bill", "andrew", "paul", "zack", "sten", "will");
        Stream<String> secondStream = Stream.of("1", "2", "3", "4", "5");
        Stream<String> resultStream= zip(firstStream, secondStream);
        resultStream.peek(it-> System.out.println(it)).collect(Collectors.toList());
    }
    public static <T> Stream<T> zip(Stream<T> first, Stream<T>  second){
        List<T> firstList=first.collect(Collectors.toList());
        List<T> secondList=second.collect(Collectors.toList());
        int minSize=Math.min(firstList.size(),secondList.size());
        List<T> result = new ArrayList<>();
        for(int i=0;i<minSize;i++){
            result.add(firstList.get(i));
            result.add(secondList.get(i));
        }
        return result.stream();

    }
    public static Stream<Long> randomAlgorithm( long a, long c, long m){
        RandomAlgorithm r = new RandomAlgorithm(a,c,m);
        long seed=21;
        Stream<Long> stream=Stream.iterate(seed,element->r.next(element));
        return stream;
    }


    public static List<String>reverseSortedUppercaseName(List<String> list){
        return list.stream()
                .map(s -> s.toUpperCase())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
    public static String oddNamesFilter(List<String> list){
        String filteredNames=  IntStream.range(0, list.size())
                .mapToObj(idx ->(idx+1) + ". " +list.get(idx))
                .filter(name->{
                    String[] x= name.split(". ");
                    return Integer.parseInt(x[0])%2!=0;
                })
                .collect(Collectors.joining(", "));
        return filteredNames;
    }
}