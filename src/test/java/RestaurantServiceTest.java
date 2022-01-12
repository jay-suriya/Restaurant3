import jdk.nashorn.internal.runtime.ECMAException;
import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;

    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){

        Item item1 = new Item("Sweet",119);
        Item item2 = new Item("Vegetable", 269);
        List<Item> menu = new ArrayList<>();
        menu.add(item1);
        menu.add(item2);
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime,menu);
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"),menu);
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }

    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        Restaurant result = service.findRestaurantByName("Pumpkin Tales");
        assertNotNull(result);
    }

    @Test
    public void searching_for_non_existing() throws restaurantNotFoundException {
        assertThrows(restaurantNotFoundException.class,()->service.findRestaurantByName("Alsham"),
                "The restaurant is not available.");
    }

    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {

        Item item1 = new Item("Sweet",119);
        Item item2 = new Item("Vegetable", 269);
        List<Item> menu = new ArrayList<>();
        menu.add(item1);
        menu.add(item2);
        restaurant = service.addRestaurant("test","Chennai",openingTime,closingTime,menu);
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("test");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("CafeDay"),
                "The restaurant to be removed is not found");
    }

    @Test
    public void placeOrder_sucess() throws restaurantNotFoundException {
        List<String> order = new ArrayList<>();
        order.add("Sweet");
        order.add("Vegetable");
        int amount = service.placeOrder("Amelie's cafe",order);
        assertEquals(119+269,amount);
    }

    @Test
    public void placeOrder_restaurantNotFound() throws restaurantNotFoundException {
        List<String> order = new ArrayList<>();
        order.add("Sweet");
        order.add("Vegetable");
        assertThrows(restaurantNotFoundException.class,()->service.placeOrder("Bon Bon cakes",order),
                "The restaurant is not available.");
    }
}