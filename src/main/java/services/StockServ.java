package services;

import DAOs.StockDAO;
import DAOs.StockDAOimpl;
import entities.Stock;

/**
 *
 * @author Usuario
 */
public class StockServ {
    
    StockDAO dao = new StockDAOimpl();
    
    public Stock actualizarStock(Stock stock){
        if (stock==null){
            throw new NullPointerException("Error: el stock es nulo");
        }
        return dao.actualizarStock(stock);
    }
    
}
