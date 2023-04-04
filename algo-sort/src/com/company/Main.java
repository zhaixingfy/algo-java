package com.company;

import java.util.Arrays;

public class Main {
    public static int rand(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
    public static int[] range(int min, int max) {
        int len = max - min + 1;
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[i] = i + min;
        }

        return arr;
    }
    public static void swap(int[] arr, int a, int b) {
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }
    public static int[] shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, (int) Math.floor(Math.random() * (i + 1)));
        }
        return arr;
    }
    public static int[] cloneIntArr(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            result[i] = arr[i];
        }

        return result;
    }
    public static void selectionSort(int[] arr) {
        int len = arr.length;

        for (int i = 0; i < len; i++) {
            int minIndex = i;

            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            swap(arr, i, minIndex);
        }
    }
    public static void insertionSort(int[] arr) {
        int len = arr.length;

        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }
    public static void mergeSort(int[] arr, int l, int r) {
        if (l >= r) return;

        int mid = l + (r - l) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);

        int[] aux = new int[r - l + 1];

        for (int i = l; i <= r; i++) {
            aux[i - l] = arr[i];
        }

        int i = l;
        int j = mid + 1;

        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l] <= aux[j - l]) {
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }
    }
    public static void quickSort1(int[] arr, int l, int r) {
        if (l >= r) return;

        swap(arr, l, rand(l, r));
        
        int e = arr[l];
        int j = l;

        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < e) {
                swap(arr, i, j + 1);
                j++;
            }
        }

        swap(arr, l, j);
        quickSort1(arr, l, j - 1);
        quickSort1(arr, j + 1, r);
    }
    public static void quickSort2(int[] arr, int l, int r) {
        if (l >= r) return;

        swap(arr, l, rand(l, r));
        
        int e = arr[l];
        int i = l + 1;
        int j = r;

        while (true) {
            while (i <= r && arr[i] < e) {
                i++;
            }
            while (j > l && arr[j] > e) {
                j--;
            }
            if (i > j) break;
            swap(arr, i, j);
            i++;
            j--;
        }

        swap(arr, l, j);
        quickSort2(arr, l, j - 1);
        quickSort2(arr, j + 1, r);
    }
    public static void quickSort3(int[] arr, int l, int r) {
        if (l >= r) return;

        swap(arr, l, rand(l, r));
        
        int e = arr[l];
        int lt = l;
        int gt = r + 1;
        int i = l + 1;

        while (i < gt) {
            if (arr[i] < e) {
                swap(arr, i, lt + 1);
                i++;
                lt++;
            } else if (arr[i] > e) {
                swap(arr, i, gt - 1);
                gt--;
            } else {
                i++;
            }
        }

        swap(arr, l, lt);
        quickSort3(arr, l, lt - 1);
        quickSort3(arr, gt, r);
    }
    public static class MaxHeap {
        int[] arr;
        public void shiftUp(int i) {
            while (i > 0 && arr[i / 2] < arr[i]) {
                swap(arr, i, i / 2);
                i /= 2;
            }
        }
        public void shiftDown(int i, int len) {
            while (i * 2 + 1 < len) {
                int j = i * 2 + 1;
                if (j + 1 < len && arr[j] < arr[j + 1]) j++;
                if (arr[i] >= arr[j]) break;
                swap(arr, i, j);
                i = j;
            }
        }
        public void create() {
            for (int i = 1; i < arr.length; i++) {
                shiftUp(i);
            }
        }
        public void heapify() {
            for (int i = (arr.length - 2) / 2; i > -1; i--) {
                shiftDown(i, arr.length);
            }
        }
        public void sort(int[] arr) {
            this.arr = arr;
            int len = arr.length;

            // create();
            heapify();

            for (int i = 0; i < len; i++) {
                swap(arr, 0, len - i - 1);
                shiftDown(0, len - i - 1);
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = shuffle(range(1, 20));

        System.out.println("selectionsort");
        int[] arr1 = cloneIntArr(arr);
        System.out.println(Arrays.toString(arr1));
        selectionSort(arr1);
        System.out.println(Arrays.toString(arr1));

        System.out.println("insertionSort");
        int[] arr2 = cloneIntArr(arr);
        System.out.println(Arrays.toString(arr2));
        insertionSort(arr2);
        System.out.println(Arrays.toString(arr2));

        System.out.println("mergeSort");
        int[] arr3 = cloneIntArr(arr);
        System.out.println(Arrays.toString(arr3));
        mergeSort(arr3, 0, arr3.length - 1);
        System.out.println(Arrays.toString(arr3));

        System.out.println("quickSort1");
        int[] arr4 = cloneIntArr(arr);
        System.out.println(Arrays.toString(arr4));
        quickSort1(arr4, 0, arr4.length - 1);
        System.out.println(Arrays.toString(arr4));

        System.out.println("quickSort2");
        int[] arr5 = cloneIntArr(arr);
        System.out.println(Arrays.toString(arr5));
        quickSort2(arr5, 0, arr5.length - 1);
        System.out.println(Arrays.toString(arr5));

        System.out.println("quickSort3");
        int[] arr6 = cloneIntArr(arr);
        System.out.println(Arrays.toString(arr6));
        quickSort3(arr6, 0, arr6.length - 1);
        System.out.println(Arrays.toString(arr6));

        System.out.println("heapSort");
        int[] arr7 = cloneIntArr(arr);
        System.out.println(Arrays.toString(arr7));
        MaxHeap maxHheap = new MaxHeap();
        maxHheap.sort(arr7);
        System.out.println(Arrays.toString(arr7));
    }
}