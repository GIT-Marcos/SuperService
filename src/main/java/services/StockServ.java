package services;

import DAOs.StockDAO;
import DAOs.StockDAOimpl;
import entities.NotaRetiro;
import entities.Stock;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class StockServ {

    StockDAO dao = new StockDAOimpl();

    public Boolean actualizarStock(NotaRetiro notaConSalidas) {
        List<Stock> stocks = new ArrayList<>();
        for (int i = 0; i < notaConSalidas.getDetallesRetiro().size(); i++) {
            Stock s = notaConSalidas.getDetallesRetiro().get(i).getRepuesto().getStock();
            stocks.add(s);
        }
        return dao.actualizarStock(stocks);
    }

}
