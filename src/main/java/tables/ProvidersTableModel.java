package tables;

import model.ProvidersModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProvidersTableModel extends AbstractTableModel {

    ArrayList data = new ArrayList();

    String[] columnas = {
            "provider_id",
            "provider_name",
            "provider_rewrite",
            "provider_email",
            "provider_phone",
            "provider_address"
    };

    public ProvidersTableModel() {
        Object[] row = new Object[6];
        row[0] = "TEST";
        row[1] = "TEST";
        row[2] = "TEST";
        row[3] = "TEST";
        row[4] = "TEST";
        row[5] = "TEST";
        data.add(row);
    }

    public ProvidersTableModel(List<ProvidersModel> providers) {
        for (ProvidersModel pm : providers) {
            data.add(Model2Row(pm));
        }
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return columnas.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] row = (Object[]) data.get(rowIndex);
        return row[columnIndex];
    }

    public void addRow(ProvidersModel pm) {
        data.add(Model2Row(pm));
        fireTableDataChanged();
    }

    public void removeRow(int fila) {
        data.remove(fila);
        fireTableDataChanged();
    }

    private Object[] Model2Row(ProvidersModel pm) {
        Object[] row = new Object[6];
        row[0] = pm.getProvider_id();
        row[1] = pm.getProvider_name();
        row[2] = pm.getProvider_rewrite();
        row[3] = pm.getProvider_email();
        row[4] = pm.getProvider_phone();
        row[5] = pm.getProvider_address();
        return row;
    }
}
