package academy.dao;

import academy.model.Order;
import academy.model.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getByUser(User user);
}
