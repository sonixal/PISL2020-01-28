package by.it.group773602.grishin.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсионные вызовы должны проводится на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска,
        помните при реализации, что поиск множественный
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {

    private int index0 = 0;
    private int index1 = 0;

    //отрезок
    private class Segment  implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            return (o.stop - o.start) - (stop - start);
        }
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        quickSort(segments, 0, segments.length-1);

        for (int i = 0; i < points.length; i++) {
            int count = 0;
            int low = 0;
            int high = segments.length - 1;
            boolean stop = false;

            while(low <= high && !stop) {
                int middle = (low+high) / 2;

                if(points[i] <= segments[middle].stop && points[i] >= segments[middle].start) {
                    count++;
                    stop = true;
                } else if (segments[middle].stop < points[i]) {
                    low = middle + 1;
                } else if (segments[middle].stop > points[i]) {
                    low = middle - 1;
                }
            }

            result[i] = count;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private void quickSort(Segment[] arr, int low, int high) {
        if (low < high) {
            partition(arr, low, high);
            quickSort(arr, low, index1);
            quickSort(arr, index0, high);
        }
    }

    private void partition (Segment[] arr, int low, int high) {
        index0 = low - 1;
        index1 = high;
        int p = low - 1;
        int q = high;

        Segment v = arr[high];

        while (true) {
            while (v.compareTo(arr[++index0]) > 0) {}
            while (v.compareTo(arr[--index1]) >= 0) {
                if (index1 == low) { break; }
            }
            if (index0 >= index1) {
                break;
            }
            swap(arr, index0, index1);

            if(arr[index0] == v) {
                p++;
                swap(arr, p, index0);
            }
            if(arr[index1] == v) {
                q--;
                swap(arr, index1, q);
            }
        }

        swap(arr, index0, high);
        index1 = index0 - 1;
        for (int i = low; i < p; i++, index1--) {
            swap(arr, i, index1);
        }
        index0++;
        for (int i = high-1; i > p; i--, index0++) {
            swap(arr, index0, i);
        }
    }

    private void swap(Segment[] arr, int first, int second) {
        Segment tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
