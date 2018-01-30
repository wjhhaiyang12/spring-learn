package com.example.dbtest.baidu;

import com.example.dbtest.model.MismatchStore;
import com.example.dbtest.model.Store;

import java.util.ArrayList;
import java.util.List;

public class TelephoneUtil {

    public static int telContain(Store store, MismatchStore mismatchStore){
        List<String> standardTels = getStandardTels(store);
        List<String> mismatchTels = getMismatchTels(mismatchStore);

        for(int i = 0; i < standardTels.size(); ++i){
            for(int j = 0; j < mismatchTels.size(); ++j){
                if(standardTels.get(i).equals(mismatchTels.get(j)))
                    return 1;
            }
        }
        return 0;
    }

    private static boolean isValidTel(String telephone) {
        if (telephone.equals("0-0") || telephone.equals("000000"))
            return false;

        for (int i = 0; i < telephone.length(); ++i) {
            char aim = telephone.charAt(i);
            if (!((aim >= '0' && aim <= '9') || aim == ',' || aim == '-' || aim == ' ' || aim == '(' || aim == ')' || aim == '/'))
                return false;
        }
        return true;
    }

    private static String standardTel(String tel) {
        //删除左括号，并替换右括号为-
        if (tel.contains("(") && tel.contains(")")) {
            tel = tel.replaceAll("\\(", "");
            tel = tel.replaceAll("\\)", "-");
        }
        return tel;
    }

    private static List<String> getStandardTels(Store store){
        List<String> standardTels = new ArrayList<>();
        String telephone = store.getTel();

        //电话为空，直接跳过这一条
        if (telephone == null || telephone.length() == 0 || !isValidTel(telephone)) {
            return new ArrayList<>();
        }
        //以'/'分隔
        if (telephone.contains("/")) {
            String[] tels = telephone.split("/");
            for (int j = 0; j < tels.length; ++j)
                standardTels.add(standardTel(tels[j]));
        }
        //以逗号分隔
        else if (telephone.contains(",")) {
            String[] tels = telephone.split(",");
            for (int j = 0; j < tels.length; ++j)
                standardTels.add(standardTel(tels[j]));
        } else {
            standardTels.add(telephone);
        }
        return standardTels;
    }

    private static List<String> getMismatchTels(MismatchStore mismatchStore){
        List<String> mismatchTels = new ArrayList<>();
        String telephone = mismatchStore.getTel();

        //电话为空，或者格式不符合条件，直接跳过这一条
        if (telephone == null || telephone.length() == 0 || !isValidTel(telephone)) {
            return new ArrayList<>();
        }

        //以逗号分隔，表示分机号
        if (telephone.contains(",")) {
            telephone = telephone.replaceAll(",", "-");
            mismatchTels.add(telephone);
        }
        //以空格分隔
        else if (telephone.contains(" ")) {
            String[] tels = telephone.split(" ");
            mismatchTels.add(tels[0]);
            mismatchTels.add(tels[1]);
        } else {
            mismatchTels.add(telephone);
        }
        return mismatchTels;
    }



}
