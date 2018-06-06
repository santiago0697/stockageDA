package tables;

import model.ProvidersModel;
import view.ProvidersView;

import javax.swing.event.TableModelEvent;
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

    private ProvidersView view;

    public ProvidersTableModel(List<ProvidersModel> providers, ProvidersView view) {
        this.view = view;
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

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex == 0 ? false : true);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Object[] row = (Object[]) data.get(rowIndex);
        row[columnIndex] = aValue;
        if (row[0] == null) {
            boolean validated = true;
            for (int j = 1; j < getColumnCount(); j++) {
                if (row[j] == null) {
                    validated = false;
                    break;
                }
            }
            if (validated) {
                ProvidersModel pm = new ProvidersModel();

                pm.setProvider_name(row[1].toString());
                pm.setProvider_rewrite(row[2].toString());
                pm.setProvider_email(row[3].toString());
                pm.setProvider_phone(row[4].toString());
                pm.setProvider_address(row[5].toString());

                pm.getCustomInfo();
                ProvidersModel.createProvider(pm);
                view.updateContentTable();
            }
        }
    }
}
