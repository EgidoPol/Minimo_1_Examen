package edu.upc.eetac.dsa;

public class RandomID {
    public static String getId() {
        String ID = null;
        for (int i=0;i<5;i++){
            double valor = Math.random();
            valor = 10*valor;
            ID = ID + Double.toString(valor);
        }
        return ID;
    }
}
