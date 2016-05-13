package com.dengliang.zeus.webdemo.util;

import java.util.UUID;

public class UIDGenerator {
	  /**
     * private constructor
     */
    private UIDGenerator() {
        super();
    }
    /**
     * 
     * @return A unique key generated by UUID
     */
    public static final synchronized String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}