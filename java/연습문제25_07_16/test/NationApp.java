package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class NationApp extends JFrame {

    private final JButton btnSort ;
    private JTable jTable;
    JComboBox<String> cmbOrder;

    NationApp() {
        setTitle("국가");

        add(new JScrollPane(makeTable()), BorderLayout.CENTER);


        JPanel panel = new JPanel();
        add(panel, BorderLayout.SOUTH);

        String[] cmbString = {"국가별", "인구수","GDP"};
        cmbOrder = new JComboBox<String>(cmbString);


        panel.add(cmbOrder);

        btnSort  = new JButton("정렬");
        panel.add(btnSort);
        btnSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Stream<Nation> ns  = Nation.nations.stream();
                String com = (String) cmbOrder.getSelectedItem();
                switch (com) {
                    case "국가별" :
                        List<Nation> name = Nation.nations.stream().sorted(Comparator.comparing(Nation::getName)).collect(Collectors.toList());
                        setListTable(name);
                        break;

                    case "인구수" :
                        List<Nation> pp = Nation.nations.stream().sorted(Comparator.comparingDouble(Nation::getPopulation)).collect(Collectors.toList());
                        setListTable(pp);
                        break;

                    case "GDP":
                        List<Nation> gg = Nation.nations.stream().sorted(Comparator.comparingInt(Nation::getGdpRank)).collect(Collectors.toList());
                        setListTable(gg);
                        break;

                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);
        setVisible(true);

    }

    void setListTable(List<Nation> list) {
        DefaultTableModel table = (DefaultTableModel) jTable.getModel();
        table.setRowCount(0);

        int i=1;
        for(Nation n : list) {
            Object[] rowdata = {i++,  n.getName(), n.getType(), n.getPopulation(), n.getGdpRank()};
            table.addRow(rowdata);
        }
    }
    /**
     *
     * @return
     */
    JTable makeTable() {
        if ( jTable == null) {
            jTable = new JTable();

            DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
            tableModel.addColumn("번호");
            tableModel.addColumn("국가명");
            tableModel.addColumn("유형");
            tableModel.addColumn("인구수");
            tableModel.addColumn("GDP순위");

            jTable.getColumn("번호").setPreferredWidth(30);
            jTable.getColumn("국가명").setPreferredWidth(100);
            jTable.getColumn("유형").setPreferredWidth(30);
            jTable.getColumn("인구수").setPreferredWidth(30);
            jTable.getColumn("GDP순위").setPreferredWidth(30);

            CenterTableCellRenderer ctcr = new CenterTableCellRenderer();
            jTable.getColumn("번호").setCellRenderer(ctcr);
            jTable.getColumn("국가명").setCellRenderer(ctcr);
            jTable.getColumn("유형").setCellRenderer(ctcr);
            jTable.getColumn("인구수").setCellRenderer(ctcr);
            jTable.getColumn("GDP순위").setCellRenderer(ctcr);
        }
        return jTable;
    }




    public class CenterTableCellRenderer extends JLabel implements TableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            setFont(new Font(null, Font.PLAIN, 12));
            setHorizontalAlignment(JLabel.CENTER);
            setOpaque(true);
            if(isSelected) { setBackground(Color.YELLOW); }
            else { setBackground(Color.WHITE); }
            return this;
        }
    }
    public static void main(String[] args) {
        new NationApp();

    }

}
