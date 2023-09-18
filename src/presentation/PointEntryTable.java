package presentation;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import graphing.data.Dataset;
import graphing.data.Point;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PointEntryTable extends JComponent {
    private JTable table;
    private DefaultTableModel tableModel;

    public PointEntryTable() {
        tableModel = new DefaultTableModel(new String[] { "X", "Y" }, 0);
        table = new JTable(tableModel);

        JButton addButton = new JButton("Add Row");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.addRow(new Object[] { "", "" });
            }
        });

        JButton removeButton = new JButton("Remove Row");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = tableModel.getRowCount();
                if (rowCount > 0) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        tableModel.removeRow(selectedRow); // remove the selected row
                    } else {
                        tableModel.removeRow(rowCount - 1); // Remove the last row
                    }
                }

            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
            }
        });

        JButton loadExampleButton = new JButton("Load Example");
        loadExampleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
                tableModel.addRow(new Object[]{0.389, 87.77});
                tableModel.addRow(new Object[]{0.724, 224.70});
                tableModel.addRow(new Object[]{1.0, 365.25});
                tableModel.addRow(new Object[]{1.524, 686.95});
                tableModel.addRow(new Object[]{5.20, 4332.62});
                tableModel.addRow(new Object[]{9.510, 10759.2});
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(loadExampleButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public Dataset toDataset() throws Exception {
        int rowCount = tableModel.getRowCount();
        List<Point> points = new ArrayList<Point>();

        for (int i = 0; i < rowCount; i++) {
            double x = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
            double y = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
            points.add(new Point(x, y));
        }

        return new Dataset(points);
    }
}
