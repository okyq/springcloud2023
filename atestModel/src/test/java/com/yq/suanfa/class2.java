package com.yq.suanfa;

import org.junit.Test;

/**
 * @author yuqian
 * @ClassName class2
 * @description:
 * @date 2023年03月15日
 */
public class class2 {

    @Test
    public void testA(){
        int[] arr = new int[]{2,3,12,55,4,51,23,76,45,15,8,1};
        int index = 0;
        int x = arr[index++];
        System.out.println(x);
        System.out.println(index);
    }

    @Test
    public void excute(){
        int[] arr = new int[]{2,3,12,55,4,51,23,76,45,15,8,1};
        //int max = process(arr,0,arr.length-1);
        //System.out.println("最大值是："+ max);
        mergeSort(arr,0,arr.length-1);
        printArr(arr);
    }

    /**
      * @description: 递归实现求数组最大值
      * @author yuqian
      * @params [arr]
      * @date 2023/3/15
      * @return int
      */
    public static int process(int[] arr,int L ,int R){
        if(L == R) {
            return arr[L];
        }
        int mid = L + ((R-L)>>1);
        int leftMax = process(arr,L,mid);
        int rightMax = process(arr,mid+1,R);
        return Math.max(leftMax,rightMax);
    }

    /**  
      * @description: 归并排序
      * @author yuqian
      * @params [arr, L, R]
      * @date 2023/3/16
      * @return void
      */ 
    public static void mergeSort(int[] arr , int L , int R){
        if(L == R){
            return;
        }
        int mid = L + ((R-L)>>1); //定义中间位置
        mergeSort(arr,L,mid); //左边部分
        mergeSort(arr,mid+1,R); //右边部分
        merge(arr,L,mid,R); //合并两个有序子数组
    }
    public static void merge(int[] arr , int L , int M, int R){
        int [] help = new int[R-L+1];   //开辟一个help数组，长度为R-L+1
        int i = 0;                      //i是指help数组的指针
        int p1 = L;                     //arr数组左边部分的指针
        int p2 = M+1;                   //arr数组右边部分的指针
        while (p1 <= M && p2 <= R){
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++]; //判断arr[p1] 和 arr[p2] 谁小，小的就放到help数组中
        }
        //执行下去之后，肯定会出现p1或者p2越界的情况，此时代表p1或者p2中所有的数已经有序放到help数组中，则只需把另一部分放入help中
        //假如p2先越界，则放p1
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        //假如p1先越界，则放p2
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        //最后把help的放到arr中
        for (i = 0; i < help.length; i++) {
            arr[L+i] = help[i];
        }
    }








    public static void printArr(int arr[]) {
        for(int i : arr) {
            System.out.print(i+"、");
        }
    }
}
