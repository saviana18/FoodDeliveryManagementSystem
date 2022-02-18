package org.fooddelivery.dataLayer;

import java.io.FileWriter;

public class FileWrite {

    /**
     * Scrie chitanta pentru o comanda intr-un fisier text
     * @param details string care contine date despre comanda si care urmeaza sa fie scris in fisierul text
     */
    public void writeBill(StringBuilder details) {
        try {
            FileWriter fileWriter = new FileWriter("bill.txt");
            fileWriter.write(String.valueOf(details));
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(" ");
        }
    }
}
