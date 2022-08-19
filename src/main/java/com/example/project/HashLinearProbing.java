package com.example.project;

//import java.util.Random;

public class HashLinearProbing{
    private int hsize; // tamano de la tabla hash
    private Persona[] buckets; // array que representa la tabla hash
    private Persona AVAILABLE;
    private int size; // cantidad de elementos en la tabla hash

    public HashLinearProbing(int hsize) {
        this.buckets = new Persona[hsize];
        this.hsize = hsize;
        this.AVAILABLE = new Persona("disponible",null);
        this.size = 0;
    }

    public int hashing(String dni){
    	int dni1 = Integer.parseInt(dni);
        int hash = dni1 % hsize;
        if (hash < 0) {
            hash += hsize;
        }
        return hash;
    }

    public void insertHash(Persona persona){
        Persona wrappedPer = new Persona(persona.DNI,persona.nombre);
        int hash = hashing(persona.DNI);

        if (isFull()){
            System.out.println("Tabla hash esta llena!");
            return;
        }

        for (int i = 0; i < hsize; i++) {
            if (buckets[hash].nombre.equals(null) || buckets[hash].DNI.equals("disponible")) {
                buckets[hash] = new Persona(wrappedPer.DNI,wrappedPer.nombre);
                size++;
                return;
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
    }

    public void deleteHash(String dni) {
        String wrappedInt = dni;
        int hash = hashing(dni);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return;
        }

        for (int i = 0; i < hsize; i++) {
            if (buckets[hash] != null && buckets[hash].DNI.equals(wrappedInt)) {
                buckets[hash] = AVAILABLE;
                size--;
                return;
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        System.out.println("Clave " + dni + " no encontrada");
    }

    public void displayHashtable() {
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] == null || buckets[i] == AVAILABLE) {
                System.out.println("Celda " + i + ": Vacia");
            } else {
                System.out.println("Celda " + i + ": " + buckets[i].toString());
            }
        }
    }

    public String findHash(String dni) {
        String wrappedString = dni;
        int hash = hashing(dni);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return null;
        }

        for (int i = 0; i < hsize; i++) {
            try {
                if (buckets[hash].DNI.equals(wrappedString)) {
                    buckets[hash] = AVAILABLE;
                    return String.valueOf(hash);
                }
            } catch (Exception E) {
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        System.out.println("Clave " + dni + " no encontrada!");
        return null;
    }    
   
    public boolean isFull() {        
        return size == hsize;
    }

    public boolean isEmpty() {
        boolean response = true;
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] != null) {
                response = false;
                break;
            }
        }
        return response;
    }
 /*
    public static void main (String[] args){
        HashLinearProbing tb = new HashLinearProbing(10);

        Random rd = new Random();

        for(int i = 0; i < 5; i++){
            tb.insertHash(rd.nextInt(100));
        }

        tb.displayHashtable();        
    }*/
}
