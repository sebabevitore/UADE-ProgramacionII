package org.uade.util;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;

public class SimpleDictionaryADTUtil {

    public static  <K, V> void print(SimpleDictionaryADT<K,V> dict){
        if (dict == null || dict.isEmpty()) {
            System.out.println("El diccionario está vacío.");
            return;
        }
        SetADT<K> keys = dict.getKeys();
        SetADT<K> copy = SetADTUtil.copy(keys);
        while(!copy.isEmpty()){
            K key = copy.choose();
            V value = dict.get(key);
            System.out.println(value);
            copy.remove(key);
        }
    }
}
