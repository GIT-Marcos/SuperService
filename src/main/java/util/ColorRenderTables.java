package util;

import java.awt.Color;
import java.awt.Component;
import java.util.function.BiPredicate;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * clase que crea el render para colorear filas de tablas según condición
 *
 * @author ia
 */
public class ColorRenderTables extends DefaultTableCellRenderer {

    private final BiPredicate<JTable, Integer> rowCondition;
    private final Color backgroundColor;

    public ColorRenderTables(BiPredicate<JTable, Integer> rowCondition, Color backgroundColor) {
        this.rowCondition = rowCondition;
        this.backgroundColor = backgroundColor;
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        boolean shouldColor = !isSelected && rowCondition.test(table, row);
        c.setBackground(shouldColor ? backgroundColor : (isSelected ? table.getSelectionBackground() : Color.WHITE));
        c.setForeground(Color.BLACK);

        return c;
    }
}
