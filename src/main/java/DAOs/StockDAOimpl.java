package DAOs;

import entities.Stock;
import org.hibernate.Session;
import util.Util;

/**
 *
 * @author Usuario
 */
public class StockDAOimpl implements StockDAO {

    Session session;

    @Override
    public Stock actualizarStock(Stock stock) {
        session = Util.getHibernateSession();
        try {
            session.beginTransaction();
            session.merge(stock);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return stock;
    }

}
