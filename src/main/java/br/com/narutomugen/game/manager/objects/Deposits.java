package br.com.narutomugen.game.manager.objects;

import java.util.HashMap;
import java.util.Map;

public class Deposits {

    private static Map<Class, Deposit> stores = new HashMap<>();


    @SuppressWarnings("unchecked")
    public static <T> T recover(Class<T> type, int maxSize) {
        Deposit<T> deposit = stores.get(type);

        if (deposit == null) {
            deposit = new Deposit<T>(maxSize);
            stores.put(type, deposit);
        }

        return (T) deposit.recover(type);
    }

    @SuppressWarnings("unchecked")
    public static void release(Object object) {
        Deposit deposit = stores.get(object.getClass());

        if(deposit != null){
            deposit.release(object);
        }

    }


}
