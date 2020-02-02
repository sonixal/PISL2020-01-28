package by.it.group773601.provalionok;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

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
        long[] array = new long[m*6];
        array[0] = 0L;
          if (m==1||n<1){
            return array[0];
        }
        array[1] = 1;
        int i;
        for (i = 2;i<m*6;i++){
            array[i] = (array[i-2]+array[i-1])%m;
            if (array[i]==0L&&array[i-1]==1){break;}

        }
        return array[(int) n % i];


        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
    }


}

