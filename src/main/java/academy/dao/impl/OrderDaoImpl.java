package academy.dao.impl;

import academy.dao.OrderDao;
import academy.exception.DataProcessingException;
import academy.lib.Dao;
import academy.model.Order;
import academy.model.User;
import academy.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cannot add order to DB: " + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return order;
    }

    @Override
    public List<Order> getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Order> query = session.createQuery("SELECT DISTINCT o FROM Order o"
                    + "LEFT JOIN FETCH o.user u"
                    + "JOIN FETCH o.tickets t"
                    + "LEFT JOIN FETCH t.movieSession ms"
                    + "LEFT JOIN FETCH ms.movieHall"
                    + "LEFT JOIN FETCH ms.movie"
                    + "WHERE o.user = :user", Order.class);
            query.setParameter("user", user);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Cannot find user`s orders: " + user, e);
        }
    }
}
