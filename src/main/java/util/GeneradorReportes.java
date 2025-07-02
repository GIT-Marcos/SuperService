package util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Map;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Usuario
 */
public class GeneradorReportes {

    public void totalVentasAnual(String ruta, Map<String, BigDecimal> valores){
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, BigDecimal> entry : valores.entrySet()) {
            String mes = entry.getKey();
            BigDecimal totalVentas = entry.getValue();
            dataset.setValue(totalVentas, "totalVentas", mes);
        }
        JFreeChart chart = ChartFactory.createBarChart("TOTAL DE VENTAS", null, null, dataset,
                PlotOrientation.VERTICAL, false, false, true);
        
//        // Configurar eje Y
//        CategoryPlot plot = chart.getCategoryPlot();
//        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
//
//        long max = valores.values().stream().mapToLong(Long::longValue).max().orElse(1);
//        int tickUnit = calcularTickUnit(max);
//
//        // Rango desde 0 hasta el siguiente múltiplo del tickUnit por encima del valor máximo
//        double upperBound = Math.ceil((double) max / tickUnit) * tickUnit;
//
//        rangeAxis.setRange(0, upperBound);
//        rangeAxis.setTickUnit(new NumberTickUnit(tickUnit));
//        rangeAxis.setNumberFormatOverride(NumberFormat.getIntegerInstance());
        
        //TO-DO: GESTIONAR COMO TIENE Q SER ESTAS EXEPCIONES, EN PDF GENERADOR TBN
        try {
            ChartUtils.saveChartAsJPEG(new File(ruta), chart, 1200, 700);
            JOptionPane.showMessageDialog(null, "Reporte creado exitosamente en:\n" + ruta);
        } catch (IOException ex) {
            System.err.println("ERROR AL GUARDAR CHART");
        }
    }
    
    public void cantidadVentasAnual(String ruta, Map<String, Long> valores) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Long> entry : valores.entrySet()) {
            String mes = entry.getKey();
            Long cantidadDeVentas = entry.getValue();
            dataset.setValue(cantidadDeVentas, "CantidadVentas", mes);
        }

        JFreeChart chart = ChartFactory.createBarChart("CANTIDAD DE VENTAS", null, null, dataset,
                PlotOrientation.VERTICAL, false, false, true);

        // Configurar eje Y
        CategoryPlot plot = chart.getCategoryPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

        long max = valores.values().stream().mapToLong(Long::longValue).max().orElse(1);
        int tickUnit = calcularTickUnit(max);

        // Rango desde 0 hasta el siguiente múltiplo del tickUnit por encima del valor máximo
        double upperBound = Math.ceil((double) max / tickUnit) * tickUnit;

        rangeAxis.setRange(0, upperBound);
        rangeAxis.setTickUnit(new NumberTickUnit(tickUnit));
        rangeAxis.setNumberFormatOverride(NumberFormat.getIntegerInstance());
        
        //TO-DO: GESTIONAR COMO TIENE Q SER ESTAS EXEPCIONES, EN PDF GENERADOR TBN
        try {
            ChartUtils.saveChartAsJPEG(new File(ruta), chart, 1200, 700);
            JOptionPane.showMessageDialog(null, "Reporte creado exitosamente en:\n" + ruta);
        } catch (IOException ex) {
            System.err.println("ERROR AL GUARDAR CHART");
        }
    }

// Método auxiliar para calcular el TickUnit óptimo
    private int calcularTickUnit(long max) {
        if (max <= 10) {
            return 1;
        }
        if (max <= 50) {
            return 5;
        }
        if (max <= 100) {
            return 10;
        }
        if (max <= 500) {
            return 50;
        }
        if (max <= 1000) {
            return 100;
        }
        return 500;
    }

}
