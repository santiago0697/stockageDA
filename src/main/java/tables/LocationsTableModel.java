package tables;

import model.LocationsModel;
import view.LocationsView;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class LocationsTableModel extends AbstractTableModel {

    ArrayList data = new ArrayList();

    String[] columnas = {
            "location_id",
            "location_name",
            "location_rewrite"
    };

    public LocationsTableModel() {
        Object[] row = new Object[3];
        row[0] = "TEST";
        row[1] = "TEST";
        row[2] = "TEST";
        data.add(row);
    }

    private LocationsView view;

    public LocationsTableModel(List<LocationsModel> locations, LocationsView view) {
        this.view = view;
        for (LocationsModel lm : locations) {
            data.add(Model2Row(lm));
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

    public void addRow(LocationsModel lm) {
        data.add(Model2Row(lm));
        fireTableDataChanged();
    }

    public void removeRow(int fila) {
        data.remove(fila);
        fireTableDataChanged();
    }

    private Object[] Model2Row(LocationsModel lm) {
        Object[] row = new Object[3];
        row[0] = lm.getLocation_id();
        row[1] = lm.getLocation_name();
        row[2] = lm.getLocation_rewrite();
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
            LocationsModel lm = new LocationsModel();

            lm.setLocation_name(row[1].toString());
            lm.setLocation_rewrite(row[2].toString());

            if (row[0] == null) {
                LocationsModel.createLocation(lm);
            } else {
                LocationsModel.updateLocation(lm, Integer.parseInt(row[0].toString()));
            }
            view.updateContentTable();

        }
    }
}
