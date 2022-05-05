package com.meng.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result implements Serializable {
    /**
     * 响应给前端是否成功的标志
     */
    private boolean flag;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据
     */
    private Object data;

    /**
     * 响应成功
     * @param message
     * @param data
     * @return
     */
    public static Result success(String message, Object data){
        return new Result(true,message,data);
    }
    public static Result fail(String message){
        return new Result(false,message);
    }

    public Result(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }
}


