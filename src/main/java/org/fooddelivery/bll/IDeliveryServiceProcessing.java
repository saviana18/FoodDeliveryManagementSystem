package org.fooddelivery.bll;

import java.util.Set;

public interface IDeliveryServiceProcessing {

    /**
     * Adauga un nou produs in meniu
     * @param newItem produsul care este adaugat in meniu
     */
    void addProduct(MenuItem newItem);

    /**
     * Sterge un produs din meniu in functie de id
     * @param id id-ul produsului care urmeaza sa fie sters
     */
    void deleteProduct(int id);

    /**
     * Modifica un produs in functie de id
     * @param modifiedItem un nou produs cu noi proprietati care il va inlocui pe cel vechi
     * @param id id-ul produsului care urmeaza sa fie modificat
     */
    void modifyProduct(MenuItem modifiedItem, int id);

    /**
     * Creeaza o noua comanda
     * @param order obiect al clasei Order care va fi adaugat in lista de comenzi
     * @param orderId id-ul comenzii
     * @param clientId id-ul clientului care a comandat
     * @param orderedItems lista de produse comandate
     */
    void createOrder(Order order, int orderId, int clientId, Set<MenuItem> orderedItems);

    /**
     * Importa produsele din fisierul csv
     * @return set cu obiecte ale clasei MenuItem
     */
    Set<MenuItem> importProducts();
}
