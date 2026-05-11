package org.uade.util;

import org.uade.Exception.EmptyADTException;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.structure.implementation.fixed.StaticStackADT;

public class StackADTUtil {

    public static <T> void print(StackADT<T> stack) {
        StackADT<T> copy = copy(stack);
        while (!copy.isEmpty()) {
            System.out.println(copy.getElement());
            copy.remove();
        }
    }

    public static <T> StackADT<T> copy(StackADT<T> stack) {
        StackADT<T> aux = getNewStack(stack);
        StackADT<T> copia = getNewStack(stack);

        while (!stack.isEmpty()) {
            aux.add(stack.getElement());
            stack.remove();
        }
        while (!aux.isEmpty()) {
            stack.add(aux.getElement());
            copia.add(aux.getElement());
            aux.remove();
        }
        return copia;
    }

    private static <T> StackADT<T> getNewStack(StackADT<T> stack) {
        if (stack instanceof StaticStackADT) {
            return new StaticStackADT<>();
        } else {
            return new DynamicStackADT<>();
        }
    }

    public static <T> boolean esPar(StackADT<T> stack){
        if(!stack.isEmpty()){
            StackADT<T> aux = copy(stack);
            int count = 0;
            while(!aux.isEmpty()){
                aux.remove();
                count++;
            }
            if(count%2 == 0){
                return true;
            }
            return false;
        }
        else{
            throw new EmptyADTException("Empty Stack");
        }
    }

    public static <T> StackADT<T> invertir(StackADT<T> stack){
        if(!stack.isEmpty()){
            StackADT<T> copia1 = copy(stack);
            StackADT<T> invertida = new DynamicStackADT<>();
            while(!copia1.isEmpty()){
                invertida.add(copia1.getElement());
                copia1.remove();
            }
            return invertida;
        }
        else{
            throw new EmptyADTException("Pila vacia");
        }
    }

    public static <T> boolean estaEnPila(T elemento, StackADT<T> pila) {
        if (pila.isEmpty()) return false;

        StackADT<T> copia = copy(pila);
        while (!copia.isEmpty()) {
            if (copia.getElement().equals(elemento)) {
                return true;
            }
            copia.remove();
        }
        return false;
    }
}
