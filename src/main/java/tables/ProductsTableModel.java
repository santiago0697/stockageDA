package tables;

import model.ProductsModel;
import view.ProductsView;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProductsTableModel extends AbstractTableModel {

    ArrayList data = new ArrayList();

    String[] columnas = {
            "products_id",
            "products_name",
            "products_description",
            "products_status",
            "products_qty",
            "provider_id",
            "location_id"
    };

    public ProductsTableModel() {
        Object[] row = new Object[6];
        row[0] = "TEST";
        row[1] = "TEST";
        row[2] = "TEST";
        row[3] = "TEST";
        row[4] = "TEST";
        row[5] = "TEST";
        data.add(row);
    }

    private ProductsView view;

    public ProductsTableModel(List<ProductsModel> products, ProductsView view) {
        this.view = view;
        for (ProductsModel pm : products) {
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

    public void addRow(ProductsModel pm) {
        data.add(Model2Row(pm));
        fireTableDataChanged();
    }

    public void removeRow(int fila) {
        data.remove(fila);
        fireTableDataChanged();
    }

    private Object[] Model2Row(ProductsModel pm) {
        Object[] row = new Object[6];
        row[0] = pm.getProducts_id();
        row[1] = pm.getProducts_name();
        row[2] = pm.getProducts_description();
        row[3] = pm.getProducts_status();
        row[4] = pm.getProducts_qty();
        row[5] = pm.getProvider().getProvider_id();
        row[6] = pm.getLocation().getLocation_id();
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
            ProductsModel pm = new ProductsModel();

            pm.setProducts_name(row[1].toString());
            pm.setProducts_description(row[2].toString());
            pm.setProducts_status(Integer.parseInt(row[3].toString()));
            pm.setProducts_qty(Integer.parseInt(row[4].toString()));

            if (row[0] == null) {
                ProductsModel.createProduct(pm, Integer.parseInt(row[5].toString()), Integer.parseInt(row[6].toString()));
            } else {
                ProductsModel.updateProduct(pm, Integer.parseInt(row[0].toString()));
            }
            //view.updateContentTable();
        }
    }
}
