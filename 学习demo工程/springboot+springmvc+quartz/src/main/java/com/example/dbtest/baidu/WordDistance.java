package com.example.dbtest.baidu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordDistance {

    public static float levenshtein(String str1, String str2) {
        //删除字符串中的特殊符号
        str1 = str1.replaceAll("\\(", "");
        str1 = str1.replaceAll("\\)", "");
        str2 = str2.replaceAll("\\(", "");
        str2 = str2.replaceAll("\\)", "");

        //对每个char进行排序，在进行编辑距离的计算
        List<String> listStr1 = new ArrayList<>();
        List<String> listStr2 = new ArrayList<>();
        for(int i = 0; i < str1.length(); ++i)
            listStr1.add(str1.charAt(i) + "");
        for(int i = 0; i < str2.length(); ++i)
            listStr2.add(str2.charAt(i) + "");
        Collections.sort(listStr1);
        Collections.sort(listStr2);

        String newStr1 = "";
        String newStr2 = "";
        for(int i = 0; i < listStr1.size(); ++i)
            newStr1 += listStr1.get(i);
        for(int i = 0; i < listStr2.size(); ++i)
            newStr2 += listStr2.get(i);

        str1 = newStr1;
        str2 = newStr2;

        // 计算两个字符串的长度。
        int len1 = str1.length();
        int len2 = str2.length();
        // 建立上面说的数组，比字符长度大一个空间
        int[][] dif = new int[len1 + 1][len2 + 1];
        // 赋初值，步骤B。
        for (int a = 0; a <= len1; a++) {
            dif[a][0] = a;
        }
        for (int a = 0; a <= len2; a++) {
            dif[0][a] = a;
        }
        // 计算两个字符是否一样，计算左上的值
        int temp;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                // 取三个值中最小的
                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
                        dif[i - 1][j] + 1);
            }
        }
        // 取数组右下角的值，同样不同位置代表不同字符串的比较
        // 计算相似度
        float similarity = 1 - (float) dif[len1][len2]
                / Math.max(str1.length(), str2.length());
        return similarity;
    }

    public static int min(int varA, int varB, int varC){
        int temp = Math.min(varA, varB);
        return Math.min(temp, varC);
    }
}
