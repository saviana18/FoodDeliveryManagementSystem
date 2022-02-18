package org.fooddelivery.presentation;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.fooddelivery.App;
import org.fooddelivery.bll.*;
import org.fooddelivery.bll.MenuItem;
import org.fooddelivery.dataLayer.FileWrite;
import org.fooddelivery.dataLayer.Serializator;

public class Controller {

    @FXML private TextField newClientUsername;
    @FXML private TextField newClientEmail;
    @FXML private PasswordField newClientPass;
    @FXML private PasswordField newClientConfirmPass;
    @FXML private TextField adminUsername;
    @FXML private PasswordField adminPass;
    @FXML private TextField employeeUsername;
    @FXML private PasswordField employeePass;
    @FXML private TextField clientUsername;
    @FXML private PasswordField clientPass;
    @FXML private TableView<MenuItem> adminPanelTable;
    @FXML private TableColumn<MenuItem, Integer> adminIdCol;
    @FXML private TableColumn<MenuItem, String> adminTitleCol;
    @FXML private TableColumn<MenuItem, Double> adminRatingCol;
    @FXML private TableColumn<MenuItem, Integer> adminCaloriesCol;
    @FXML private TableColumn<MenuItem, Integer> adminProteinsCol;
    @FXML private TableColumn<MenuItem, Integer> adminFatsCol;
    @FXML private TableColumn<MenuItem, Integer> adminSodiumCol;
    @FXML private TableColumn<MenuItem, Integer> adminPriceCol;
    @FXML private TextField titleField;
    @FXML private TextField ratingField;
    @FXML private TextField caloriesField;
    @FXML private TextField proteinsField;
    @FXML private TextField fatsField;
    @FXML private TextField sodiumField;
    @FXML private TextField priceField;
    @FXML private TextField idField;
    @FXML private TextArea newComposite;
    @FXML private TableView<MenuItem> clientPanelTable;
    @FXML private TableColumn<MenuItem, Integer> clientIdCol;
    @FXML private TableColumn<MenuItem, String> clientTitleCol;
    @FXML private TableColumn<MenuItem, Double> clientRatingCol;
    @FXML private TableColumn<MenuItem, Integer> clientCaloriesCol;
    @FXML private TableColumn<MenuItem, Integer> clientProteinsCol;
    @FXML private TableColumn<MenuItem, Integer> clientFatsCol;
    @FXML private TableColumn<MenuItem, Integer> clientSodiumCol;
    @FXML private TableColumn<MenuItem, Integer> clientPriceCol;
    @FXML private TextField searchTitle;
    @FXML private TextField searchRating;
    @FXML private TextField searchCalories;
    @FXML private TextField searchProteins;
    @FXML private TextField searchFats;
    @FXML private TextField searchSodium;
    @FXML private TextField searchPrice;
    @FXML private TextArea idsForOrder;
    @FXML private TableView<Order> reportPanelTable;
    @FXML private TableColumn<Order, String> reportClientCol;
    @FXML private TableColumn<Order, String> reportOrderCol;
    @FXML private TableColumn<Order, Integer> reportPriceCol;
    @FXML private TableColumn<Order, String> reportDateCol;
    @FXML private TextField reportStartHour;
    @FXML private TextField reportEndHour;
    @FXML private TextField reportNoOfTimes;
    @FXML private TextField reportClientUser;
    @FXML private TextField reportAmount;
    @FXML private TextField reportDay;
    private String title;
    private double rating;
    private int calories;
    private int proteins;
    private int fats;
    private int sodium;
    private int price;
    private int id;
    private int orderId = 1;
    private int clientId;
    private final DeliveryService deliveryService = new DeliveryService();
    private final HashMap<Order, Set<MenuItem>> ordersMap = new HashMap<>();
    private final HashSet<MenuItem> menuItemSet = new HashSet<>();
    private final DeliveryService deliveryService2 = new DeliveryService(ordersMap, menuItemSet);
    private ObservableList<MenuItem> menu = FXCollections.observableArrayList();
    private Set<MenuItem> setMenu;
    private ObservableList<MenuItem> clientMenu = FXCollections.observableArrayList();
    private Set<MenuItem> clientSetMenu;
    private Collection<Order> reportList;

    /**
     * Functia de register pentru client
     */
    @FXML
    private void clientRegister() {
        String accountDetails = newClientUsername.getText().toString() + " " + newClientEmail.getText().toString() + " " +
                newClientPass.getText().toString() + "\n";
        try {
            FileWriter fileWriter = new FileWriter("clientaccounts.txt", true);
            fileWriter.write(accountDetails);
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Success...");
        }
        newClientUsername.clear();
        newClientEmail.clear();
        newClientPass.clear();
        newClientConfirmPass.clear();
    }

    @FXML
    private void switchToClientLogin() throws IOException {
        App.setRoot("clientlogin");
    }

    @FXML
    private void switchToAdminLogin() throws IOException {
        App.setRoot("adminlogin");
    }

    @FXML
    private void switchToEmployeeLogin() throws IOException {
        App.setRoot("employeelogin");
    }

    @FXML
    private void adminLogin() throws IOException {
        if (adminUsername.getText().equals("admin") && adminPass.getText().equals("admin")) {
            App.setRoot("adminpanel");
            System.out.println("Bravo esti admin");
        }
    }

    @FXML
    private void employeeLogin() {
        if (employeeUsername.getText().equals("angajat") && employeePass.getText().equals("angajat")) {
            System.out.println("Bravo esti angajat");
        }
    }

    /**
     * Functie de login pentru client
     * @throws IOException exceptie pentru citirea din fisier
     */
    @FXML
    private void clientLogin() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("clientaccounts.txt"));
        StringBuilder stringBuilder = new StringBuilder();
        String line = " ";
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(" ");
        }
        reader.close();
        String content = stringBuilder.toString();
        String[] info = content.split(" ");
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> passwords = new ArrayList<>();
        for (int i = 0; i < info.length; i += 3) {
            usernames.add(info[i]);
        }
        for (int i = 2; i < info.length; i += 3) {
            passwords.add(info[i]);
        }
        boolean existentUser = false;
        for (int i = 0; i < usernames.size(); i++) {
            for (int j = 0; j < passwords.size(); j++) {
                if (i == j && clientUsername.getText().equals(usernames.get(i)) && clientPass.getText().equals(passwords.get(j))) {
                    existentUser = true;
                    clientId = i;
                }
            }
        }
        if (existentUser) {
            App.setRoot("clientpanel");
            System.out.println("Bravo esti client");
        } else {
            System.out.println("Teapa");
        }

        try {
            FileWriter fileWriter = new FileWriter("currentClients.txt", true);
            fileWriter.write(clientUsername.getText() + "\n");
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(" ");
        }
    }

    @FXML
    private void switchToClientRegister() throws IOException {
        App.setRoot("clientregister");
    }

    /**
     * Seteaza header-ul tabelului pentru panelul adminului
     */
    private void setMyTableHeader() {
        adminIdCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Id"));
        adminTitleCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("Title"));
        adminRatingCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Double>("Rating"));
        adminCaloriesCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Calories"));
        adminProteinsCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Proteins"));
        adminFatsCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Fats"));
        adminSodiumCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Sodium"));
        adminPriceCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Price"));
    }

    /**
     * Functionalitatea adminului de a importa meniul din fisierul csv
     */
    @FXML
    private void importMenu() {
        ObservableList<MenuItem> menuItemObservableList = FXCollections.observableArrayList();
        menuItemObservableList.addAll(deliveryService.importProducts());
        setMyTableHeader();
        adminPanelTable.getItems().setAll(menuItemObservableList);
        Serializator serializator = new Serializator();
        serializator.serialize(deliveryService.importProducts());
    }

    /**
     * Functia de deserializare a datelor
     */
    @FXML
    private void deserializeMenu() {
        Serializator serializator = new Serializator();
        setMenu = serializator.deserialize();
        menu.addAll(setMenu);
        setMyTableHeader();
        adminPanelTable.getItems().setAll(menu);
    }

    private void readFromFields() {
        id = Integer.parseInt(idField.getText());
        title = titleField.getText();
        rating = Double.parseDouble(ratingField.getText());
        calories = Integer.parseInt(caloriesField.getText());
        proteins = Integer.parseInt(proteinsField.getText());
        fats = Integer.parseInt(fatsField.getText());
        sodium = Integer.parseInt(sodiumField.getText());
        price = Integer.parseInt(priceField.getText());
    }

    /**
     * Functionalitatea adminului de a adauga un produs in meniu
     */
    @FXML
    private void addProduct() {
        readFromFields();
        BaseProduct toBeAdded = new BaseProduct(id, title, rating, calories, proteins, fats, sodium, price);
        setMenu.add(toBeAdded);
    }

    /**
     * Serializarea datelor dupa ce se fac anumite modificari la meniu
     */
    @FXML
    private void saveChanges() {
        Serializator serializator = new Serializator();
        serializator.serialize(setMenu);
    }

    /**
     * Functionalitatea adminului de a sterge un produs din meniu
     */
    @FXML
    private void deleteProduct() {
        id = Integer.parseInt(idField.getText());
        setMenu.removeIf(item -> item.getId() == id);
    }

    /**
     * Functionalitatea adminului de a modifica un produs din meniu
     */
    @FXML
    private void modifyProduct() {
        readFromFields();
        for (MenuItem item : setMenu) {
            if (item.getId() == id) {
                item.setTitle(title);
                item.setRating(rating);
                item.setCalories(calories);
                item.setProteins(proteins);
                item.setFats(fats);
                item.setSodium(sodium);
                item.setPrice(price);
            }
        }
    }

    /**
     * Functionalitatea adminului de a adauga un produs compus in meniu
     */
    @FXML
    private void addNewComposite() {
        String [] string = newComposite.getText().split(", ");
        List<String> idsAsStrings = new ArrayList<String>();
        idsAsStrings = Arrays.asList(string);

        ArrayList<Integer> productIds = new ArrayList<>();
        for (int i = 0; i < idsAsStrings.size(); i++) {
            productIds.add(i, Integer.parseInt(idsAsStrings.get(i)));
        }
        id = Integer.parseInt(idField.getText());
        title = titleField.getText();
        rating = Double.parseDouble(ratingField.getText());
        price = Integer.parseInt(priceField.getText());
        ArrayList<MenuItem> selectedProducts = new ArrayList<>();
        int totalCalories = 0, totalProteins = 0, totalFats = 0, totalSodium = 0;
        for (MenuItem item : setMenu) {
            for (Integer id : productIds) {
                if (item.getId() == id) {
                    selectedProducts.add(item);
                    totalCalories += item.getCalories();
                    totalProteins += item.getProteins();
                    totalFats += item.getFats();
                    totalSodium += item.getSodium();
                }
            }
        }
        BaseProduct finalComposite = new BaseProduct(id, title, rating, totalCalories, totalProteins, totalFats,
                totalSodium, price);
        setMenu.add(finalComposite);
    }

    /**
     * Functionalitatea clientului de a cauta in meniu
     */
    @FXML
    public void search() {
        List<MenuItem> filtered = clientSetMenu.stream()
                    .filter(i -> searchTitle.getText().equals("") || i.getTitle().toLowerCase().contains(searchTitle.getText().toLowerCase()))
                    .filter(i -> searchRating.getText().equals("") || (Double.parseDouble(searchRating.getText()) != 0 && i.getRating() == Double.parseDouble(searchRating.getText())))
                    .filter(i -> searchCalories.getText().equals("") || (Integer.parseInt(searchCalories.getText()) != 0 && i.getCalories() == Integer.parseInt(searchCalories.getText())))
                    .filter(i -> searchProteins.getText().equals("") || (Integer.parseInt(searchProteins.getText()) != 0 && i.getProteins() == Integer.parseInt(searchProteins.getText())))
                    .filter(i -> searchFats.getText().equals("") || (Integer.parseInt(searchFats.getText()) != 0 && i.getFats() == Integer.parseInt(searchFats.getText())))
                    .filter(i -> searchSodium.getText().equals("") || (Integer.parseInt(searchSodium.getText()) != 0 && i.getSodium() == Integer.parseInt(searchSodium.getText())))
                    .filter(i -> searchPrice.getText().equals("") || (Integer.parseInt(searchPrice.getText()) != 0 &&i.getPrice() == Integer.parseInt(searchPrice.getText())))
                    .collect(Collectors.toList());
        ObservableList<MenuItem> found = FXCollections.observableArrayList();
        found.addAll(filtered);
        clientPanelTable.getItems().clear();
        clientPanelTable.getItems().setAll(found);
    }

    /**
     * Seteaza header-ul tabelului din panelul clientului
     */
    private void setMyClientTableHeader() {
        clientIdCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Id"));
        clientTitleCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("Title"));
        clientRatingCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Double>("Rating"));
        clientCaloriesCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Calories"));
        clientProteinsCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Proteins"));
        clientFatsCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Fats"));
        clientSodiumCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Sodium"));
        clientPriceCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Price"));
    }

    /**
     * Functionalitatea clientului de a vedea meniul
     */
    @FXML
    private void clientViewMenu() {
        setMyClientTableHeader();
        Serializator serializator = new Serializator();
        clientSetMenu = serializator.deserialize();
        clientMenu.addAll(clientSetMenu);
        clientPanelTable.getItems().setAll(clientMenu);
    }

    @FXML
    private void logOutAdmin() throws IOException {
        App.setRoot("adminlogin");
    }

    /**
     * Functionalitatea clientului de a face o comanda
     * @throws IOException exceptie pentru citirea din fisier
     */
    @FXML
    private void orderClient() throws IOException {
        String [] string = idsForOrder.getText().split(", ");
        List<String> orderIds = new ArrayList<String>();
        orderIds = Arrays.asList(string);

        ArrayList<Integer> idsAsIntegers = new ArrayList<>();
        for (int i = 0; i < orderIds.size(); i++) {
            idsAsIntegers.add(i, Integer.parseInt(orderIds.get(i)));
        }
        System.out.println(idsAsIntegers);
        HashSet<MenuItem> ordered = new HashSet<MenuItem>();
        for (MenuItem item : clientSetMenu) {
            for (Integer integer : idsAsIntegers) {
                if (item.getId() == integer) {
                    ordered.add(item);
                }
            }
        }
        Order newOrder = new Order();
        deliveryService2.createOrder(newOrder, orderId, clientId, ordered);
        BufferedReader reader = new BufferedReader(new FileReader("currentClients.txt"));
        StringBuilder stringBuilder = new StringBuilder();
        String line = " ";
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(" ");
        }
        reader.close();
        String content = stringBuilder.toString();
        String[] info = content.split(" ");
        StringBuilder orderDetails = new StringBuilder("Thank you for your order!" + "\n");

        int totalPrice = 0;
        ArrayList<String> test = new ArrayList<>();
        for (MenuItem item : ordered) {
            orderDetails.append(item.getTitle()).append("\n");
            test.add(item.getTitle());
            totalPrice += item.computePrice();
        }
        String garbage = "";
        for (String s : info) {
            garbage = s + "~" + test + "~" + totalPrice + "~" + newOrder.getOrderDate().toString() + "~" + "\n";
        }
        //System.out.println(garbage);

        orderDetails.append("Total: " + totalPrice);
        FileWrite writeBill = new FileWrite();
        writeBill.writeBill(orderDetails);
        //System.out.println(ordersMap);
        try {
            FileWriter fileWriter = new FileWriter("orders.txt", true);
            fileWriter.write(garbage);
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(" ");
        }
    }

    @FXML
    private void logOutClient() throws IOException {
        App.setRoot("clientlogin");
    }

    @FXML
    private void logOutAdminReport() throws IOException {
        App.setRoot("adminlogin");
    }

    /**
     * Fucntionalitatea adminului de a vedea comenzile
     * @throws IOException exceptie pentru citirea din fisier
     */
    @FXML
    private void seeOrders() throws IOException {
        reportClientCol.setCellValueFactory(new PropertyValueFactory<Order, String>("Client"));
        reportOrderCol.setCellValueFactory(new PropertyValueFactory<Order, String>("Order"));
        reportPriceCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("Price"));
        reportDateCol.setCellValueFactory(new PropertyValueFactory<Order, String>("Date"));

        reportList = Files.readAllLines(new File("C:\\Users\\Savi\\IdeaProjects\\tema4\\orders.txt").toPath())
                .stream()
                .map(line -> {
                    String[] details = line.split("~");
                    return new Order(details[0], Collections.singletonList(details[1]), Integer.parseInt(details[2]),
                            details[3]);
                })
                .collect(Collectors.toList());
        ObservableList<Order> details = FXCollections.observableArrayList();
        details.addAll(reportList);
        reportPanelTable.getItems().setAll(details);
    }

    /**
     * Functionalitatea adminului de a genera report-uri
     */
    @FXML
    private void getReport() {
        List<Order> filtered = reportList.stream()
                .filter(i -> reportStartHour.getText().equals("") || i.getDate().contains(reportStartHour.getText()))
                .filter(i -> reportEndHour.getText().equals("") || i.getDate().contains(reportEndHour.getText()))
                .filter(i -> reportClientUser.getText().equals("") || i.getClient().contains(reportClientUser.getText()))
                .filter(i -> reportNoOfTimes.getText().equals(""))
                .filter(i -> reportAmount.getText().equals(""))
                .filter(i -> reportDay.getText().equals(""))
                .collect(Collectors.toList());
        ObservableList<Order> found = FXCollections.observableArrayList();
        found.addAll(filtered);
        reportPanelTable.getItems().clear();
        reportPanelTable.getItems().setAll(found);
    }

    @FXML
    private void goToReportsPanel() throws IOException {
        App.setRoot("reportspanel");
    }
}
