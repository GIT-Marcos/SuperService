package DAOs;

import entities.Stock;
import java.util.List;
import org.hibernate.Session;
import util.Util;

/**
 *
 * @author Usuario
 */
public class StockDAOimpl implements StockDAO {

    Session session;

    @Override
    public Boolean actualizarStock(List<Stock> stocks) {
        session = Util.getHibernateSession();
        try {
            session.beginTransaction();
            for (Stock s : stocks) {
                session.merge(s);
            }
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
    }

}
