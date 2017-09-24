package com.liukevin.inter.callback;

import java.util.Map;

/**
 * 回调方法
 * Created by liukai on 2017/9/24.
 */
public interface CallBack {

    /**
     * 入参为map的回调方法
     * @param resultMap
     */
    void callBack(Map<String, String> resultMap);
}
