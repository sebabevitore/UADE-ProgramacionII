package org.uade.algorithm.binarytree;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;

import static org.uade.util.StackADTUtil.copy;

public class AdditionalBinaryTreeExcercise72 {
    //72. Dado una pila se quiere obtener un arbol pero considerando:
    //  ○ El metodo principal recibe una pila y retorna un arbol
    //  ○ Se deben eliminar los duplicados antes de pasar los valores al arbol. Se
    //      suguiere usar un conjunto

    private static BinaryTreeADT pilaArbol(StackADT stack){
        if(stack.isEmpty()){
            return null;
        }
        //StackADT copiaPila = copy(stack);
        SetADT set = pilaSet(stack);
        BinaryTreeADT arbol = new DynamicBinaryTreeADT();
        while(!set.isEmpty()){
            int elemento = set.choose();
            arbol.add(elemento);
            set.remove(elemento);
        }
        return arbol;
    }

    private static SetADT pilaSet(StackADT stack){
        SetADT resultado = new DynamicSetADT();
        StackADT copiaPila = copy(stack);
        while(!copiaPila.isEmpty()){
            resultado.add(copiaPila.getElement());
            copiaPila.remove();
        }
        return resultado;
    }


    public static void main(String[] args) {

    }
}
