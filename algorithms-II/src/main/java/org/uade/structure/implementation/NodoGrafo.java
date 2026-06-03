    package org.uade.structure.implementation;

    public class NodoGrafo {
        int nodo;
        NodoArista arista;
        NodoGrafo sigNodo;

        public NodoGrafo(int nodo) {
            this.nodo = nodo;
            this.arista = null;
            this.sigNodo = null;
        }

        public int getNodo() {
            return nodo;
        }

        public void setNodo(int nodo) {
            this.nodo = nodo;
        }

        public NodoArista getArista() {
            return arista;
        }

        public void setArista(NodoArista arista) {
            this.arista = arista;
        }

        public NodoGrafo getSigNodo() {
            return sigNodo;
        }

        public void setSigNodo(NodoGrafo sigNodo) {
            this.sigNodo = sigNodo;
        }
    }

