package com.chen.clientc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test {

//    public static void main(String[] args) {
//        String str = "aabcaabdaabc";
//
////        Map<String,Integer> map = new HashMap<String,Integer>();
////
////        for (int i = 0; i < str.length(); i++) {
////            for (int j = i + 1; j <= str.length(); j++) {
////                String st = str.substring(i,j);
////
////                if(map.containsKey(st)){
////                    int value = map.get(st);
////
////                    map.put(st,value+1);
////                }else{
////                    map.put(st,1);
////                }
////            }
////        }
////
////        String sub = str.substring(0,1);
////        for (Map.Entry<String, Integer> entry : map.entrySet()) {
////            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
////
////            if(entry.getValue() >= 2){
////                if(sub.length() < entry.getKey().length() ){
////                    sub = entry.getKey();
////                }
////            }
////        }
////
////        System.out.println(sub);
//
//        Test test = new Test();
//        int length = test.lengthOfLongestSubstring(str);
//
//        System.out.println(length);
//
//    }
//
//    public int lengthOfLongestSubstring(String s) {
//        int n = s.length();
//        int ans = 0;
//        for (int i = 0; i < n; i++)
//            for (int j = i + 1; j <= n; j++)
//                if (allUnique(s, i, j))
//                    ans = Math.max(ans, j - i);
//        return ans;
//    }
//
//    public boolean allUnique(String s, int start, int end) {
//        Set<Character> set = new HashSet<>();
//        for (int i = start; i < end; i++) {
//            Character ch = s.charAt(i);
//            if (set.contains(ch)) return false;
//            set.add(ch);
//        }
//        return true;
//    }
}
