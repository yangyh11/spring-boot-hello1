package com.example.cache;

import org.joda.time.DateTime;

public enum CacheId {

    REDIS_TEST("REDIS_TEST");

    private String predix;

    CacheId(String predix) {
        this.predix = predix;
    }

    public String getRawPrefix(){
        return this.predix;
    }

    public String getCacheIdByKey(String... keys){
        if (keys == null || keys.length <= 0) {
            return this.getCacheIdByKey();
        }
        String ret = this.getCacheIdByKey();
        for (String key : keys) {
            ret += "_" + key;
        }

        return ret;
    }

    private String getCacheIdByKey(){
        return String.format(this.predix + "_%s", DateTime.now().toString("yyyyMMdd"));
    }
}
