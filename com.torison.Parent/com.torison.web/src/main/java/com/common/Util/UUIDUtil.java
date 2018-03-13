package com.common.Util;

import java.util.UUID;

public class UUIDUtil {

    public static String[] chars = new String[] {  "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "0" };

    /**
     * 生成8位UUID
     * @return 8位UUID
     */
    public static String generateShortUuid() {
        long UID =UUID.randomUUID().getMostSignificantBits();
        if (UID<0){
            UID = -UID;
        }
        String str = ""+UID;
        str = str.substring(0,8);
        return str;
    }
}
