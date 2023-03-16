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
    public void excute(){
        int[] arr = new int[]{2,3,12,55,4,51,23,76,45,15,8,1};
        int max = process(arr,0,arr.length-1);
        System.out.println("最大值是："+ max);
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
}
