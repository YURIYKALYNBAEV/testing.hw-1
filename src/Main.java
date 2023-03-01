import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();
    }

    public static void task1() {
        System.out.println("Задача №1");
        findMinMax(
                () -> IntStream
                        .range(1, 100)
                        .boxed(),
                new NumberComparator(),
                (min, max) -> System.out.println("Min : " + min + " Max " + max)
        );
        System.out.println();

        findMinMaxVersion2(
                () -> IntStream
                        .range(20, 30)
                        .boxed(),
                new NumberComparator(),
                (min, max) -> System.out.println("Min : " + min + " Max " + max)
        );
        System.out.println();
    }

    public static void task2() {
        System.out.println("Задача №2");
        List<Integer> integerList = new ArrayList<>();
        int number;
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            number = random.nextInt() % 100;
            integerList.add(number);
        }
        System.out.println("Исходный список целых чисел: " + integerList);
        workingEvenNumbers(integerList);
    }

    public static <T> void findMinMax(
            Supplier<Stream<? extends T>> stream,
            Comparator<? super T> comparator,
            BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<T> arrayList = new ArrayList<>();
        T min = null;
        T max = null;
        arrayList = stream
                .get()
                .sorted(comparator)
                .collect(Collectors.toList());
        if (!arrayList.isEmpty()) {
            min = arrayList.get(0);
            max = arrayList.get(arrayList.size() - 1);
        }
        minMaxConsumer.accept(min, max);
    }

    public static <T> void findMinMaxVersion2(
            Supplier<Stream<? extends T>> stream,
            Comparator<? super T> comparator,
            BiConsumer<? super T, ? super T> minMaxConsumer) {
        T min = stream.get().min(comparator).orElse(null);
        T max = stream.get().max(comparator).orElse(null);
        minMaxConsumer.accept(min, max);
    }

    static class NumberComparator implements Comparator<Number> {
        @Override
        public int compare(Number o1, Number o2) {
            return Double.compare(o1.doubleValue(), o2.doubleValue());
        }
    }

    public static void workingEvenNumbers(List<Integer> integerList) {
        System.out.println("Список четных чисел: " +
                integerList.stream().filter(i -> i % 2 == 0).collect(Collectors.toList()) + "\n" +
                "Количество четных чисел: " + integerList.stream().filter(i -> i % 2 == 0).count());
        System.out.println();

        // версия №2
        System.out.println("Количество четных чисел: " + integerList.stream()
                .filter(i -> i % 2 == 0)
                .peek(e -> System.out.println("Четное число: " + e))
                .count());
    }
}