package com.example.pt2022_30424_iaz_ania_assigment4.bussinesLayer;
import com.example.pt2022_30424_iaz_ania_assigment4.model.*;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class DeliveryServiceProcessing extends Observable implements IDeliveryServiceProcessing {

    private static DeliveryServiceProcessing deliveryServiceProcessing;
    private static List<User> userList = new ArrayList<>();
    private static List<MenuItem> menuItemListCSV = new ArrayList<>();
    private static HashMap<Order, ArrayList<MenuItem>> orders = new HashMap<>();
    FileWriter file = null;
    FileWriter rep1 = null;
    FileWriter rep2 = null;
    FileWriter rep3 = null;
    FileWriter rep4 = null;


    //menuItems get,set
    public List<MenuItem> getMenuItemList() {
        return menuItemListCSV;
    }

    public void setMenuItemListCSV(List<MenuItem> menuItemListCSV) {
        DeliveryServiceProcessing.menuItemListCSV = menuItemListCSV;
    }
    //user get,set
    public List<User> getUserList() {
        return userList;
    }


    public void setUserList(List<User> userList) {
        DeliveryServiceProcessing.userList = userList;
    }

    //hash map
    public HashMap<Order, ArrayList<MenuItem>> getOrders() {
        return orders;
    }

    public void setOrders(HashMap<Order, ArrayList<MenuItem>> orders) {
        DeliveryServiceProcessing.orders = orders;
    }
    public DeliveryServiceProcessing(){}

    /**
     * reads the data from a csv file and stores it in a list
     * @param path ->path to a file.csv
     * @throws IOException
     */
    @Override
    public void loadProducts(String path) throws IOException {
        isWellFormed();
        assert(path != null);
        Stream<String> getInfo = Files.lines(new File(path).toPath());
        menuItemListCSV = getInfo.skip(1).map(line->{
            String[] tokens = line.split(",");
            return new BaseProduct(
                    tokens[0],
                    Double.parseDouble(tokens[1]),
                    Integer.parseInt(tokens[2]),
                    Integer.parseInt(tokens[3]),
                    Integer.parseInt(tokens[4]),
                    Integer.parseInt(tokens[5]),
                    Integer.parseInt(tokens[6]));
        }).distinct().collect(Collectors.toList());
        assert (menuItemListCSV.size()>0);
    }

    /**
     * adds the parameter menuItem to the list of MenuItems
     * @param menuItem
     */
    @Override
    public void addProduct(MenuItem menuItem)  {
        isWellFormed();
        assert (menuItem!=null);
        int size = menuItemListCSV.size();
        menuItemListCSV.add(menuItem);
        assert(menuItemListCSV.size() == size+1);
    }

    /**
     * this function gets the initial product and the wan that we want to modify as parameters
     * we delete the initial one and add the new one
     * @param modifyMenuItem
     * @param initialItem
     */
    @Override
    public void modifyProduct(MenuItem modifyMenuItem, MenuItem initialItem) {
        isWellFormed();
        assert (modifyMenuItem!=null && initialItem!=null);
        int size = menuItemListCSV.size();
        removeProduct(initialItem);
        addProduct(modifyMenuItem);
        assert(menuItemListCSV.size() == size);
    }

    /**
     * removes from the list the item specified as parameter
     * @param removeMenuItem
     */
    @Override
    public void removeProduct( MenuItem removeMenuItem) {
        isWellFormed();
        assert (removeMenuItem!=null);
        int size = menuItemListCSV.size();
        menuItemListCSV.remove(removeMenuItem);
        assert(menuItemListCSV.size() == size-1);
    }

    /**
     * adds to the list the user specified as parameter
     * @param user
     */
    @Override
    public void addClient(User user) {
        isWellFormed();
        assert (user !=null);
        int size = userList.size();
        userList.add(user);
        assert (userList.size() == size +1);
    }

    /**
     * this function searches for the products that respect the specified parameters
     * some of them may be negative value=> they are not taken into consideration
     * @param keyWord
     * @param minR
     * @param maxR
     * @param minC
     * @param maxC
     * @param minP
     * @param maxP
     * @param minF
     * @param maxF
     * @param minS
     * @param maxS
     * @param minPr
     * @param maxPr
     * @return
     */
    @Override
    public List<MenuItem> searchingProduct(String keyWord, double minR, double maxR,
                                           int minC, int maxC, int minP, int maxP, int minF, int maxF,
                                           int minS, int maxS, int minPr, int maxPr) {
        isWellFormed();
        List<MenuItem> foundItems = new ArrayList<>();
        //keyword
        if(!keyWord.equals("")) {
            foundItems = getMenuItemList().stream().filter(menuItem ->
                            menuItem.title().toLowerCase().
                                    contains(keyWord.toLowerCase())).
                    collect(Collectors.toList());
        }
        //rating
        foundItems = (List<MenuItem>) foundItems.stream().filter(menuItem -> {
            if(minR != -100.0) return menuItem.computeRating() >= minR;
            else return true;
        }).collect(Collectors.toList());
        foundItems = (List<MenuItem>) foundItems.stream().filter(menuItem -> {
            if(maxR != -100.0) return menuItem.computeRating() <= maxR;
            else return true;
        }).collect(Collectors.toList());
        //calories
        foundItems = (List<MenuItem>) foundItems.stream().filter(menuItem -> {
            if(minC != -100) return menuItem.computeCalories() >= minC;
            else return true;
        }).collect(Collectors.toList());
        foundItems = (List<MenuItem>) foundItems.stream().filter(menuItem -> {
            if(maxC != -100) return menuItem.computeCalories() <= maxC;
            else return true;
        }).collect(Collectors.toList());
        //protein
        foundItems = (List<MenuItem>) foundItems.stream().filter(menuItem -> {
            if(minP != -100) return menuItem.computeProtein() >= minP;
            else return true;
        }).collect(Collectors.toList());
        foundItems = (List<MenuItem>) foundItems.stream().filter(menuItem -> {
            if(maxP != -100) return menuItem.computeProtein() <= maxP;
            else return true;
        }).collect(Collectors.toList());
        //fat
        foundItems = (List<MenuItem>) foundItems.stream().filter(menuItem -> {
            if(minF != -100) return menuItem.computeFat() >= minF;
            else return true;
        }).collect(Collectors.toList());
        foundItems = (List<MenuItem>) foundItems.stream().filter(menuItem -> {
            if(maxF != -100) return menuItem.computeFat() <= maxF;
            else return true;
        }).collect(Collectors.toList());
        //sodium
        foundItems = (List<MenuItem>) foundItems.stream().filter(menuItem -> {
            if(minS != -100) return menuItem.computeSodium() >= minS;
            else return true;
        }).collect(Collectors.toList());
        foundItems = (List<MenuItem>) foundItems.stream().filter(menuItem -> {
            if(maxS != -100) return menuItem.computeSodium() <= maxS;
            else return true;
        }).collect(Collectors.toList());
        //price
        foundItems = (List<MenuItem>) foundItems.stream().filter(menuItem -> {
            if(minPr != -100) return menuItem.computePrice() >= minPr;
            else return true;
        }).collect(Collectors.toList());
        foundItems = (List<MenuItem>) foundItems.stream().filter(menuItem -> {
            if(maxPr != -100) return menuItem.computePrice() <= maxPr;
            else return true;
        }).collect(Collectors.toList());
        assert (foundItems. size() >= 0);
        return foundItems;
    }

    /**
     * to create an order we need the items from the order, the user and the date when it has taken place
     * also the bill is created when an order is made
     * this method notifies the employee when an order is made
     * @param boughtItems
     * @param user
     * @param date
     */
    @Override
    public void createOrder(List<MenuItem> boughtItems, User user, Date date) {
        isWellFormed();
        assert(user != null);
        assert (boughtItems.size() != 0);
        Order order = new Order(user, date);
        int price = boughtItems.stream().mapToInt(MenuItem::computePrice).sum();
        order.setPrice(price);
        orders.put(order, (ArrayList<MenuItem>) boughtItems);
        try {
            file = new FileWriter("bill.txt");
            writeBill(order,boughtItems);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setChanged();
        notifyObservers(order);
        assert (orders.size()>0);
    }

    /**
     * method that adds a composed product to the list
     * @param title
     * @param menuItems
     */
    @Override
    public void createMenuItem(String title, ArrayList<MenuItem> menuItems) {
        isWellFormed();
        assert (menuItems != null && !title.equals(""));
        ComposedProduct composedProduct = new ComposedProduct(title);
        composedProduct.setProducts(menuItems);
        int sizePre = menuItemListCSV.size();
        menuItemListCSV.add(composedProduct);
        assert (menuItemListCSV.size() == 1+sizePre);
    }

    /**
     * filter order placed between mint and maxt
     * @param minT
     * @param maxT
     */
    @Override
    public void reportTimeInterval(int minT, int maxT) {
        assert (minT > 0 && maxT>0 && minT<maxT);
        List<Order> ordersList = orders.keySet().stream().filter(order -> {
            return order.getDate().getHours()>=minT;
        }).filter(order -> {
            return order.getDate().getHours()<=maxT;
        }).distinct().collect(Collectors.toList());
        System.out.println(ordersList.size());
        try {
            rep1 = new FileWriter("report1.txt");
            writeReport1(ordersList,minT,maxT);
            rep1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    isWellFormed();
    }

    /**
     * filter the clients that have order more than 'number' time of amount bigger than 'amount'
     * @param number
     * @param amount
     */
    @Override
    public void reportClients(int number, int amount) {
        assert(number>0 && amount>0);
        List<User> foundUser = userList.stream().
                filter(user -> {
                    List<Order> foundOrders = orders.keySet().stream().
                            filter(order -> {
                                return order.getPrice()>= amount;
                        }).filter(order -> {
                            return order.getUser().equals(user);
                        }).collect(Collectors.toList());
                 if(foundOrders.size()>=number) {
                     return true;
                 }
                 else
                     return false;
                }).distinct().collect(Collectors.toList());
        for(User user: foundUser){
            System.out.println(user.getUsername());
        }
        System.out.println(foundUser.size());
        try {
            rep3 = new FileWriter("report3.txt");
            writeReport3(foundUser,number,amount);
            rep3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        isWellFormed();
    }

    /**
     * searches for products ordered more or equal to the number specified
     * stores them in an arrayList and writes the information in a .txt
     * @param number
     */
    @Override
    public void reportProducts(int number) {
        assert(number >0);
        List<Order> ordersToSearch = new ArrayList<>();
        for (Order order : orders.keySet()) {
            ordersToSearch.add(order);
        }
        List<MenuItem> menuItemsFound = new ArrayList<>();
        menuItemsFound = menuItemListCSV.stream().filter(
                menuItem ->
                {int numberFound = (int) ordersToSearch.stream().filter(
                        order->orders.get(order).contains(menuItem)).count();
                return numberFound >= number;}).distinct().toList();
        try {
            rep2 = new FileWriter("report2.txt");
            writeReport2(menuItemsFound, number);
            rep2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert(menuItemsFound.size()>0);
    }

    /**
     * finds the product bought in the specified day
     * @param date
     */
    @Override
    public void reportDay(LocalDate date) {
        assert(date != null);
       HashMap<MenuItem,Integer> goodItems = new HashMap<>();
       menuItemListCSV.forEach(menuItem -> {
            int countNo = (int) orders.keySet().stream().filter(order -> {
                Calendar calendar  = Calendar.getInstance();
                calendar.setTime(order.getDate());
                return calendar.get(Calendar.YEAR) == date.getYear() && calendar.get(Calendar.MONTH) == date.getMonth().getValue()-1 && calendar.get(Calendar.DAY_OF_MONTH)==date.getDayOfMonth();
            }).filter(order ->
                orders.get(order).contains(menuItem)).count();
          if(countNo!=0){
              goodItems.put(menuItem,countNo);
          }
       });
        System.out.println(goodItems.size());
        try {
            rep4 = new FileWriter("report4.txt");
            writeReport4(goodItems,date);
            rep4.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert(goodItems.size()>=0);
    }

    public static DeliveryServiceProcessing instance() {
        return deliveryServiceProcessing;
    }

    public boolean isWellFormed(){
        if(deliveryServiceProcessing == null) return false;
        return true;
    }


    /**
     * method that writes in a file information about the bill
     * @param order
     * @param boughtItems
     * @throws IOException
     */
    public void writeBill(Order order, List<MenuItem> boughtItems) throws IOException {
        file.write("CUSTOMER: ");
        file.write(order.getUser().getUsername());
        file.write("\n");
        file.write("DATE AND TIME: ");
        file.write(String.valueOf(order.getDate()));
        file.write("\n");
        file.write("AMOUNT TO PAY :");
        file.write(String.valueOf(order.getPrice()));
        file.write("\n");
        file.write("PRODUCTS BOUGHT: ");
        int size = boughtItems.size();
        int index = 1;
        for(MenuItem menuItem: boughtItems){
            file.write(menuItem.title());
            if(index != size) {
                file.write(",");
                index++;
            }
        }
    }

    public void writeReport2(List<MenuItem> menuItems, int number) throws IOException {
        rep2.write("Products bought more than ");
        rep2.write(String.valueOf(number));
        rep2.write(" times\n");
        for(MenuItem m :menuItems){
            rep2.write(m.title());
            rep2.write("  ");
            rep2.write(String.valueOf(m.computeRating()));
            rep2.write("  ");
            rep2.write(String.valueOf(m.computeCalories()));
            rep2.write("  ");
            rep2.write(String.valueOf(m.computeProtein()));
            rep2.write("  ");
            rep2.write(String.valueOf(m.computeFat()));
            rep2.write("  ");
            rep2.write(String.valueOf(m.computeSodium()));
            rep2.write("  ");
            rep2.write(String.valueOf(m.computePrice()));
            rep2.write("\n");
        }
    }

    public void writeReport3(List<User> userList,int number, int amount) throws IOException{
        rep3.write("Clients that ordered more than ");
        rep3.write(String.valueOf(number));
        rep3.write(" times\n");
        rep3.write("Amount bigger than ");
        rep3.write(String.valueOf(amount));
        rep3.write("\n");
        for(User user: userList){
            rep3.write("USER: ");
            rep3.write(user.getUsername());
            rep3.write("\n");
        }
    }

    public void writeReport1(List<Order> order, int minT, int maxT) throws IOException {
        rep1.write("Orders place between ");
        rep1.write(String.valueOf(minT));
        rep1.write(" and ");
        rep1.write(String.valueOf(maxT));
        rep1.write("\n");
        for(Order o: order){
            rep1.write(o.getUser().getUsername());
            rep1.write("   ");
            rep1.write(String.valueOf(o.getDate()));
            rep1.write("\n");
        }
    }

    public void writeReport4(HashMap<MenuItem,Integer> map,LocalDate date) throws IOException {
        rep4.write("Products bought in date :");
        rep4.write(String.valueOf(date));
        rep4.write("\n");
        for(MenuItem menuItem: map.keySet()){
            rep4.write(menuItem.title());
            rep4.write("\n");
        }
    }
}
