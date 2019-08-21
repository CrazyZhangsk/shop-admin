package com.fh.shop.utils;

public class CacheManager {
    private BaseCache bc;
    private static volatile CacheManager instance;
    private static Object lock = new Object();
    private CacheManager(){
         bc = new BaseCache("fh_shop", 24*60*60);
    }

    public static CacheManager getInstance(){
        if (instance == null){
            synchronized( lock ){
                if (instance == null){
                    instance = new CacheManager();
                }
            }
        }
        return instance;
    }

    public void putObj(String ids,Object obj){
        bc.put(ids,obj);
    }
    
    public void putObj(String ids,Object obj, int expire){
        bc.put(ids,obj,expire);
    }
    
   

    public Object getObj(String ids){
        try {
            return bc.get(ids);
        } catch (Exception e) {
            return null;
        }
    }

    public void remove(String ids){
        bc.remove(ids);
    }
    public void removeAll(){
        bc.removeAll();
    }

}
