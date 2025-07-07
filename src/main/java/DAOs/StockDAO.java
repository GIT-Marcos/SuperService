package DAOs;

import entities.Stock;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface StockDAO {

    Boolean actualizarStock(List<Stock> stocks);
    
}
