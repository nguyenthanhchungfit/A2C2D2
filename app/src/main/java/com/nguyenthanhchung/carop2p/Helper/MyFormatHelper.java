package com.nguyenthanhchung.carop2p.Helper;

/**
 * Created by Nguyen Thanh Chung on 2018-05-06.
 */

public class MyFormatHelper {
    public static String fotmatTimeRecord(int miliseconds){
        int SoGiay = miliseconds/1000;
        int SoPhut = SoGiay/60;
        if(SoPhut == 0){
            return Integer.toString(SoGiay) + " giây";
        }
        else {
            int SoGiayDu = SoGiay % 60;
            if (SoGiayDu > 0)
                return Integer.toString(SoPhut) + " phút " + Integer.toString(SoGiayDu) + " giây";
            else
                return Integer.toString(SoPhut) + " phút";
        }
    }
}
