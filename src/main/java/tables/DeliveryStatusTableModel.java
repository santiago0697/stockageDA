package tables;

import model.DeliveryStatusModel;
import model.LocationsModel;
import view.DeliveryStatusView;
import view.LocationsView;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class DeliveryStatusTableModel extends AbstractTableModel {

    ArrayList data = new ArrayList();

    String[] columnas = {
            "delivery_status_id",
            "delivery_rewrite",
            "delivery_description"
    };

    public DeliveryStatusTableModel() {
        Object[] row = new Object[3];
        row[0] = "TEST";
        row[1] = "TEST";
        row[2] = "TEST";
        data.add(row);
    }

    private DeliveryStatusView view;

    public DeliveryStatusTableModel(List<DeliveryStatusModel> deliveryStatus, DeliveryStatusView view) {
        this.view = view;
        for (DeliveryStatusModel dsm : deliveryStatus) {
            data.add(Model2Row(dsm));
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

    public void addRow(DeliveryStatusModel dsm) {
        data.add(Model2Row(dsm));
        fireTableDataChanged();
    }

    public void removeRow(int fila) {
        data.remove(fila);
        fireTableDataChanged();
    }

    private Object[] Model2Row(DeliveryStatusModel dsm) {
        Object[] row = new Object[3];
        row[0] = dsm.getDelivery_status_id();
        row[1] = dsm.getDelivery_rewrite();
        row[2] = dsm.getDelivery_description();
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
        boolean validated = true;
        for (int j = 1; j < getColumnCount(); j++) {
            if (row[j] == null) {
                validated = false;
                break;
            }
        }
        if (validated) {
            DeliveryStatusModel dsm = new DeliveryStatusModel();

            dsm.setDelivery_rewrite(row[1].toString());
            dsm.setDelivery_description(row[2].toString());

            if (row[0] == null) {
                DeliveryStatusModel.createDeliveryStatus(dsm);
            } else {
                DeliveryStatusModel.updateDeliveryStatus(dsm, Integer.parseInt(row[0].toString()));
            }
            view.updateContentTable();
        }
    }
}
