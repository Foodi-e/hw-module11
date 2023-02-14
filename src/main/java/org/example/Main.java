package org.example;

import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static String foo(List<String> names) {
        return names.stream()
                .filter(name -> names.indexOf(name) % 2 == 0)
                .map(name -> names.indexOf(name)+1 + ". " + name)// щоб перед іменем поставаити індекс
                .collect(Collectors.joining(", "));
    }

    public static List<String> foo2(List<String> names) {
        return names.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    public static String foo3(String[] array) {
        return Arrays.stream(array)
                .flatMap(s -> Arrays.stream(s.split(", ")))
                .map(Integer::valueOf)
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    public static Stream<Long> foo4(long a, long m, long c){
        //// x[n + 1] = 1 (a x[n] + c) % m
        return Stream.iterate(1L, x -> (a * x + c) % m)
                .limit(10);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        List<T> res = new ArrayList<>();
        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            res.add(iterator1.next());
            res.add(iterator2.next());
        }
        return res.stream();
    }
    public static void main(String[] args) {
        List<String> names = List.of("John", "Jane", "Mary", "Mike", "Bob", "Jim", "Jill", "Ann", "Zip");
        System.out.println(foo(names));
        System.out.println(foo2(names));

        String[] array = {"1, 2, 0", "4, 5"};
        System.out.println(foo3(array));
        long a = 25214903917L;
        long c = 11L;
        long m = (long)Math.pow(2, 48);

        Stream<Long> stream = foo4(a, m, c);
        stream.forEach(System.out::println);

        Stream<Integer> stream1 = Stream.of(1, 3, 5, 7, 9);
        Stream<Integer> stream2 = Stream.of(2, 4, 6, 8);
        Stream<Integer> zip = zip(stream1, stream2);
        System.out.println(zip.collect(Collectors.toList()));



    }
}