package academy.service;

import academy.model.Order;
import academy.model.ShoppingCart;
import academy.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
