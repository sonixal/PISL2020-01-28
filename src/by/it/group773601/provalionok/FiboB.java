package by.it.group773601.provalionok;

import java.lang.reflect.Array;
import java.math.BigInteger;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    private BigInteger[] array = {BigInteger.ZERO,BigInteger.ONE};

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        for (int i = 0;i<n;i++){
            if (i%2==0){
                array[0]=array[0].add(array[1]);
            }
            else {
                array[1]=array[0].add(array[1]);
            }
        }

        if (n%2==0) return array[0];
        else return array[1];
    }

}

