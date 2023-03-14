package com.yq.suanfa;

import org.junit.Test;

/**
 * @author yuqian
 * @ClassName Test
 * @description:
 * @date 2023年02月27日
 */
public class TestOwn {

    @Test
    public void mytest() {
        int[] arr = new int[]{2,3,12,55,4,51,23,76,45,15,8,1};
        //选择排序
        //selectionSort(x);、

        //冒泡排序升序
        //bubbleSour(arr);

        //冒泡排序降序：
        //bubbleSortDesc(arr);

        //插入排序：
        //insertionSortDesc(arr);

        //插入排序：
        insertionSortAsc(arr);


        printArr(arr);
    }


    /**
      * @description: 冒泡排序升序
      * @author yuqian
      * @params [arr]
      * @date 2023/2/27
      * @return void
      */
    public static void bubbleSour(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for(int i = 0 ; i<arr.length-1 ; i++) {
            for (int j = 0 ; j<arr.length-i-1 ; j++){
                if( arr[j] > arr[j+1] ){        //如果前面一个数比后面一个数大，则交换位置
                    swap(arr,j,j+1);
                }
            }
        }
    }

    /**
      * @description: 冒泡排序降序
      * @author yuqian
      * @params [arr]
      * @date 2023/3/1
      * @return void
      */
    public static void bubbleSortDesc(int [] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        for(int i = 0 ; i<arr.length-1 ; i++) {
            for (int j = 0 ; j<arr.length-i-1 ; j++){
                if( arr[j] < arr[j+1] ){        //如果前面一个数比后面一个数小，则交换位置
                    swap(arr,j,j+1);
                }
            }
        }
    }

    /**  
      * @description: 选择排序,每次选择最小的下标
      * @author yuqian
      * @params []
      * @date 2023/2/27
      * @return void
      */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swapArr(arr,i,minIndex);
        }
    }

    /**  
      * @description: 插入排序升序
      * @author yuqian
      * @params [arr]
      * @date 2023/3/1
      * @return void
      */ 
    public static void insertionSortAsc(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for(int i = 1 ; i< arr.length; i++){
            for(int j = i-1; j>=0 && arr[j]>arr[j+1]; j--){
                swapArr(arr,j,j+1);
            }
        }
    }

    /**
     * @description: 插入排序降序
     * @author yuqian
     * @params [arr]
     * @date 2023/3/1
     * @return void
     */
    public static void insertionSortDesc(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for(int i = 1 ; i< arr.length; i++){
            for(int j = i-1; j>=0 && arr[j]<arr[j+1]; j--){
                swapArr(arr,j,j+1);
            }
        }
    }



    
    /**  
      * @description: 数组交换
      * @author yuqian
      * @params []
      * @date 2023/2/27
      * @return void
      */ 
    public static void swapArr(int arr[], int indexA, int indexB){
        int temp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = temp;
    }

    /**  
      * @description: 通过位运算交换两个数
      * @author yuqian
      * @params [arr, i, j]
      * @date 2023/3/1
      * @return void
      */ 
    public static void swap(int[] arr , int i , int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
      * @description: 数组打印
      * @author yuqian
      * @params [arr]
      * @date 2023/2/27
      * @return void
      */
    public static void printArr(int arr[]) {
        for(int i : arr) {
            System.out.print(i+"、");
        }
    }

}
