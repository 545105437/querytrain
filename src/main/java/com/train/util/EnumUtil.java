package com.train.util;


import com.train.enums.CodeEnum;

/**
 * @author wal
 * @date 2018/3/9
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
