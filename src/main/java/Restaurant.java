import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant()
    {

    }
    public Restaurant(String name,String location)
    {
        this.name = name;
        this.location = location;
    }
    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this(name,location);
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public void setOpeningTime(LocalTime openingTime)
    {
        this.openingTime = openingTime;
    }

    public void setClosingTime(LocalTime closingTime)
    {
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime currentTime = getCurrentTime();
        return currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime);
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return Collections
                .unmodifiableList(menu);
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void setMenu(List<Item> menu) {
        this.menu = menu;
    }

    public void addToMenu(String name,int price)
    {
        Item item = new Item(name,price);
        menu.add(item);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }


}
