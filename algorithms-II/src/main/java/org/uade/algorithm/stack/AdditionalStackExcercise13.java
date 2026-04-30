package org.uade.algorithm.stack;

import org.uade.Exception.EmptyADTException;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

import static org.uade.util.StackADTUtil.copy;

public class AdditionalStackExcercise13 {

    //  13)
    //  Cree e inicialice la pila DADA. Pase el primer elemento de la pila DADA a su última
    //  posición, dejando los restantes elementos en el orden original.

    private static void ejercicio (StackADT dada){
        if(!dada.isEmpty()){
            int valor = dada.getElement(); // tengo el ultimo aca
            dada.remove(); // lo saco
            System.out.println("Valor tomado: " + valor);

            StackADT invertida = invertir(dada);
            System.out.println("-------");
            System.out.println("Invertida sin el tope: " + invertida);

            // vacio dada
            while(!dada.isEmpty()){
                dada.remove();
            }

            System.out.println("-------");
            System.out.println("dada vacia: " + dada);

            // meto el ultimo valor, en el final

            System.out.println("-------");
            System.out.println("Insertando " + valor +" en dada...");
            dada.add(valor);
            System.out.println("Dada actual: " + dada);

            System.out.println("-------");
            System.out.println("Insertando invertida en dada...");
            while(!invertida.isEmpty()){
                dada.add(invertida.getElement());
                invertida.remove();
            }

            System.out.println("-------");

        }
        else{
            throw new EmptyADTException("Pila vacia");
        }
    }

    private static StackADT invertir(StackADT stack){
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
            throw new EmptyADTException("Pila vacia");
        }

    }
    public static void main(String[] args) {
        StackADT stack = new DynamicStackADT();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);

        System.out.println("pila og: "+stack);
        System.out.println("aplicando ejercicio...");
        ejercicio(stack);


        System.out.println("Deberia quedar: {3,2,1,4}");
        System.out.println("-----RESULTADO FINAL-----");
        System.out.println(stack);


    }

}
