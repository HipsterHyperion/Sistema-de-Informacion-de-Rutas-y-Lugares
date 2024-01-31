

package vista;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class ColorCeldaTabla extends DefaultTableCellRenderer{
    
    private List<Integer> color;
    private boolean activado;


    public void setColor(int color) {
        if(this.color == null){
            this.color = new ArrayList<>(); 
        }
        if(color>1){
            int i,j;
            if(activado){
                activado = false;
                j=1;
            }
            else{
                activado = true;
                j=2;
            }
            
            for(i=0; i<color; i++){
                this.color.add(j);
            }
        }
        else{
            this.color.add(0);
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row, int column){
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        switch (color.get(row)) {
            case 0:
                this.setBackground(Color.cyan);
                break;
            case 1:
                this.setBackground(Color.lightGray);
                break;
            case 2:
                this.setBackground(Color.PINK);
                break;
            default:
                throw new AssertionError();
        }
        
        return this;
    }
    
    
}
