package com.test.autotestplatform;

/**
 * dongdong Created by 11:33 AM  2020/9/11
 */
public class Test1 {
    //    public static void main(String[] args) {
//        int[] arr = {1,2,5,7,3,5,5,3,1};
//        Set<Integer> list = new HashSet<>();
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr.length; j++) {
//                if (i==j){
//                    continue;
//                }else if (arr[i]==arr[j]){
//                    list.add(arr[i]);
//                }
//
//            }
//        }
//        list.stream().forEach(System.out::println);
//
//    }
    public static void main(String[] args) {
        int[] num = {1,2,5,7,3,5,5,3,1};
        int NumChange;
        System.out.println("重复数字是：");
        for (int index = 0; index < num.length; index++) {
            while (num[index] != index) {
                if (num[index] == num[num[index]]) {
                    System.out.print(num[index] + " ");
                    break;
                } else {
                    NumChange = num[num[index]];
                    num[num[index]] = num[index];
                    num[index] = NumChange;
                }
            }
        }
    }
}

