package org.uade.util;

import org.uade.Exception.EmptyException;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.structure.implementation.fixed.StaticStackADT;

import java.util.EmptyStackException;

public class StackADTUtil {

    public static void print(StackADT stack) {
        StackADT copy = copy(stack);
        while (!copy.isEmpty()) {
            System.out.println(copy.getElement());
            copy.remove();
        }
    }

    public static StackADT copy(StackADT stack) {
        StackADT aux = getNewStack(stack);
        StackADT copia = getNewStack(stack);

        // 1. Invertimos stack en aux (stack queda vacía)
        while (!stack.isEmpty()) {
            aux.add(stack.getElement());
            stack.remove();
        }
        // 2. Restauramos stack Y cargamos la copia (vuelven al orden original)
        while (!aux.isEmpty()) {
            stack.add(aux.getElement());
            copia.add(aux.getElement());
            aux.remove();
        }
        return copia;
    }

    private static StackADT getNewStack(StackADT stack) {
        if (stack instanceof StaticStackADT) {
            return new StaticStackADT();
        } else {
            return new DynamicStackADT();
        }
    }

    public static boolean esPar(StackADT stack){
        if(!stack.isEmpty()){
            StackADT aux = copy(stack);
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
            throw new EmptyException("Empty Stack");
        }
    }

    public static StackADT invertir(StackADT stack){
        if(!stack.isEmpty()){
            StackADT copia1 = copy(stack);
            StackADT invertida = new DynamicStackADT();
            while(!copia1.isEmpty()){
                invertida.add(copia1.getElement());
                copia1.remove();
            }
            return invertida;
        }
        else{
            throw new EmptyException("Pila vacia");
        }
    }

    public static boolean estaEnPila(int elemento, StackADT pila) {
        if (pila.isEmpty()) return false; // si está vacía, claramente no está el elemento

        StackADT copia = copy(pila);
        while (!copia.isEmpty()) {
            if (copia.getElement() == elemento) {
                return true;
            }
            copia.remove();
        }
        return false;
    }
}
