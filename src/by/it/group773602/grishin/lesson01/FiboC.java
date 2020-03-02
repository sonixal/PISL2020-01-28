package by.it.group773602.grishin.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import javax.sound.midi.Sequence;
import java.util.ArrayList;
import java.util.List;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
        List<Long> sequence = getSequence(m);
        long period = sequence.size() - 2;

        int index = (int)(n % period);
        return sequence.get(index);
    }

    private static List<Long> getSequence(long m) {
        List<Long> sequence = new ArrayList<>();

        sequence.add(0L);
        sequence.add(1L);

        for (int i=2; i < m*6; i++) {
            sequence.add((sequence.get(i-1) + sequence.get(i-2)) % m);
            if (sequence.get(i) == 1 && sequence.get(i-1) == 0) { break; }
        }

        return sequence;
    }


}

