package com.lxc.autopage.excel;

/**
 * Created by chenlx
 * on 2016/9/12.
 */
public class CellException extends Exception {

    private String message;

    public CellException(String message){
        super(message);
        this.message = message;
    }

    public CellException(String header, String message){
        super();
        StringBuilder sb = new StringBuilder(header);
        sb.append(message);
        this.message = sb.toString();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    class ErrorType{

        public static final String NOT_NULL = "不允许为空";

        public static final String NOT_REG = "不符合规则";

        public static final String NOT_CONVERT = "值非指定类型";

        public static final String NOT_EXPLAIN = "解析类型错误";

    }

}
