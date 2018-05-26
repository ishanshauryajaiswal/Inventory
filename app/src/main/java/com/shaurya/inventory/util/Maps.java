package com.shaurya.inventory.util;

import java.util.HashMap;

/**
 * Created by shaurya on 09/04/18.
 */

public class Maps {

    public static HashMap<Integer,String> companyMap = new HashMap<Integer, String>()
    {{
        put(1,"Samrath Pharmaceuticals");
        put(2,"Samrath Udyog Samiti");
        put(3,"Marketing Material");
        put(4,"Packaging Material");
    }};

    public static HashMap<Integer,String> stockModeMap = new HashMap<Integer, String>(){{
        put(1,"STOCK_IN");
        put(2,"STOCK_OUT");
        put(3,"PACKAGING_MATERIAL");
    }};

    public static HashMap<Integer,String> sourceInMap = new HashMap<Integer, String>(){{
        put(1,"Factory");
        put(2,"Repackaging");
        put(3,"Repair");
    }};


    public static HashMap<Integer,String> sourceOutMap = new HashMap<Integer, String>(){{
        put(1,"Distributor");
        put(2,"Stockist");
        put(3,"Salesman");
        put(4,"Retail");
        put(5,"Cash");
        put(6,"Sample");
    }};

    public static HashMap<Integer,HashMap<Integer,String>> sourceMap = new HashMap<Integer, HashMap<Integer, String>>(){{
       put(1,sourceInMap);
       put(2,sourceOutMap);
    }};
}
