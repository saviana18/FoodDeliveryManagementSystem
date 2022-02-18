package org.fooddelivery.bll;

import org.fooddelivery.dataLayer.Serializator;

import java.time.LocalDateTime;
import java.util.*;

public class DeliveryService implements IDeliveryServiceProcessing {

    private HashMap<Order, Set<MenuItem>> orders;
    private Set<MenuItem> menu;
    private final Serializator serializator = new Serializator();

    public DeliveryService() {

    }

    public DeliveryService(HashMap<Order, Set<MenuItem>> orders, Set<MenuItem> menu) {
        this.orders = orders;
        this.menu = menu;
    }

    public Set<MenuItem> importProducts() {
        assert menu != null;

        Serializator serializator = new Serializator();
        menu = serializator.readFromFile();
        ArrayList<MenuItem> duplicates = new ArrayList<>();

        for (MenuItem firstProduct : menu) {
            for (MenuItem secondProduct : menu) {
                if (firstProduct.getTitle().equals(secondProduct.getTitle()) && !firstProduct.equals(secondProduct)) {
                    duplicates.add(secondProduct);
                }
            }
        }

        duplicates.forEach(menu::remove);
        int i = 1;
        for (MenuItem item : menu) {
            item.setId(i);
            i++;
        }
        return menu;
    }

    public void addProduct(MenuItem newItem) {
        assert menu != null;
        for (MenuItem item : menu) {
            if (!item.getTitle().equals(newItem.getTitle())) {
                menu.add(newItem);
            }
        }
        serializator.serialize(menu);
    }

    public void deleteProduct(int id) {
        assert menu != null;
        assert id != 0;
        menu.removeIf(item -> item.getId() == id);
    }

    public void modifyProduct(MenuItem modifiedItem, int id) {
        assert menu != null;
        assert id != 0;
        for (MenuItem item : menu) {
            if (item.getId() == id) {
                item.setTitle((String) modifiedItem.getTitle());
                item.setRating((Double) modifiedItem.getRating());
                item.setCalories((Integer) modifiedItem.getCalories());
                item.setProteins((Integer) modifiedItem.getProteins());
                item.setFats((Integer) modifiedItem.getFats());
                item.setSodium((Integer) modifiedItem.getSodium());
                item.setPrice((Integer) modifiedItem.getPrice());
            }
        }
    }

    public void createOrder(Order order, int orderId, int clientId, Set<MenuItem> orderedItems) {
        assert order != null;
        assert orderId != 0;
        assert clientId != 0;
        assert  orderedItems != null;
        order.setOrderId(orderId);
        order.setClientId(clientId);
        order.setOrderDate(LocalDateTime.now());
        orders.put(order, orderedItems);
        //System.out.println(orders.toString());
        //System.out.println(newOrder.getOrderDate().toString());
    }

}
