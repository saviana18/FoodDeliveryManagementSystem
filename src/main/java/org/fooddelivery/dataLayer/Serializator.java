package org.fooddelivery.dataLayer;

import org.fooddelivery.bll.BaseProduct;
import org.fooddelivery.bll.MenuItem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Serializator {

    private Set<MenuItem> products;

    public Set<MenuItem> getProducts() {
        return products;
    }

    public void setProducts(Set<MenuItem> products) {
        this.products = products;
    }

    /**
     * Citeste din fisierul csv folosind stream-uri si expresii lambda
     * @return un set cu obiecte ale clasei MenuItem
     */
    public Set<MenuItem> readFromFile() {
        try {
            List<String[]> lines = Files.lines(Paths.get("C:\\Users\\Savi\\IdeaProjects\\tema4\\products.csv"))
                    .filter(line -> !line.startsWith("Title"))
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());
            products = lines.stream()
                    .map(a -> new BaseProduct(0, a[0], Double.parseDouble(a[1]), Integer.parseInt(a[2]),
                            Integer.parseInt(a[3]), Integer.parseInt(a[4]), Integer.parseInt(a[5]), Integer.parseInt(a[6])))
                    .collect(Collectors.toSet());
            System.out.println(getProducts());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Serializeaza datele citite din fisierul csv (scrie)
     * @param menu set cu obiecte ale clasei MenuItem
     */
    public void serialize(Set<MenuItem> menu) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("menu.ser");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(menu);
            outputStream.close();
            fileOutputStream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Deserializeaza datele din fisierul menu.ser (citeste)
     * @return un set care contine obiecte ale clasei MenuItem
     */
    public HashSet<MenuItem> deserialize() {
        HashSet<MenuItem> menu = new HashSet<MenuItem>();
        try {
            FileInputStream fileInputStream = new FileInputStream("menu.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            menu = (HashSet<MenuItem>)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return menu;
    }
}
