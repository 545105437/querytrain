package com.train.annotation;

import java.lang.annotation.*;

/**
 * @author wal
 * @date 2018/5/29
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnSearchLogHistory {
    /**actiontype:SER->数据检索
     *            UPD->数据更新
     *            INS->数据增加
     * */
    String actiontype();

    String action();
}
