import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static final List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime, List<Item> menu) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        newRestaurant.setMenu(menu);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public void removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        if(restaurantToBeRemoved == null)
        {
            throw new restaurantNotFoundException("The restaurant to be removed is not found");
        }
        restaurants.remove(restaurantToBeRemoved);
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{
        for( Restaurant rest : restaurants)
        {
            if(rest.getName().equals(restaurantName))
            {
                return rest;
            }
        }
        throw new restaurantNotFoundException("The restaurant is not available.");
    }

    public int placeOrder(String name, List<String> orderedItems) throws restaurantNotFoundException {
        Restaurant restaurant = findRestaurantByName(name);
        int totalCost = 0;
        for(String orderedItem : orderedItems)
        {
            for(Item item : restaurant.getMenu())
            {
                if(orderedItem.equals(item.getName()))
                {
                    totalCost = totalCost + item.getPrice();
                    break;
                }
            }
        }
        return totalCost;
    }
}
