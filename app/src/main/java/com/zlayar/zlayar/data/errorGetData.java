package com.zlayar.zlayar.data;

/**
 * Created by IrfanRZ on 10/09/2018.
 */

public class errorGetData {
    String data;
    public String dataError(int errorType){
        switch (errorType){
            case 0:
                data="tidak";
                break;
            case 1:
                data="Nothing Users found!";
                break;
            case 2:
                data="Check Your Internet Connection!";
                break;
            case 3:
                data="Error";
                break;
            default:
                data="Error";
                break;
        }
        return data;
    }
}
