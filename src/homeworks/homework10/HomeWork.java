package homeworks.homework10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class HomeWork {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add((int) (Math.random() * 100000));
        }
        List<Integer> list2 = new ArrayList<>(list);
        List<Integer> list3 = new ArrayList<>(list);

        //MySort result
        Date startDate = new Date();
        sorting(list);
        Date endDate = new Date();
        long duration = endDate.getTime() - startDate.getTime();
        for (Integer i : list) {
            System.out.print(i + " ");
        }
        System.out.println("\nMySortDuration - " + duration);

        //CollectionSort result
        Date startDate2 = new Date();
        Collections.sort(list2);
        Date endDate2 = new Date();
        long duration2 = endDate2.getTime() - startDate2.getTime();
        System.out.println("\nCollectionSortDuration - " + duration2);

        //BubbleSort result
        Date startDate3 = new Date();
        bubbleSort(list3);
        Date endDate3 = new Date();
        long duration3 = endDate3.getTime() - startDate3.getTime();
        System.out.println("\nBubbleSortDuration - " + duration3);
    }

    //MySort
    static void sorting(List<Integer> list) {
        int shift = 0;
        for (int i = 0; i < list.size(); i++) {
            int currentMinValue = list.get(0);
            int currentPositionMinValue = 0;
            for (int j = 0; j < list.size() - shift; j++) {
                if (list.get(j) < currentMinValue) {
                    currentMinValue = list.get(j);
                    currentPositionMinValue = j;
                }
            }
            list.remove(currentPositionMinValue);
            list.add(currentMinValue);
            shift++;
        }
    }

    //BubbleSort
    public static void bubbleSort(List<Integer> list3) {
        boolean sorted = false;
        int temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < list3.size() - 1; i++) {
                if (list3.get(i) > list3.get(i + 1)) {
                    temp = list3.get(i);
                    list3.set(i, i + 1);
                    list3.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
    }
}





