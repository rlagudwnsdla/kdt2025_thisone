package cc33.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class BoardApp extends JFrame {
    private JTable jTable;
    private JPanel pSouth;
    private JButton btnInsert;


    BoardApp(){
        setTitle("게시판 리스트");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new JScrollPane(getJTable()), BorderLayout.CENTER);
        getContentPane().add(getPSouth(), BorderLayout.SOUTH);
        loadDataFromDatabase();
        setSize(600, 450);
        setVisible(true);

    }


    JTable getJTable() {
        if(jTable == null) {
            jTable = new JTable();

            DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
            tableModel.addColumn("번호");
            tableModel.addColumn("제목");
            tableModel.addColumn("글쓴이");
            tableModel.addColumn("날짜");
            tableModel.addColumn("조회수");

            jTable.getColumn("번호").setPreferredWidth(20);
            jTable.getColumn("제목").setPreferredWidth(250);
            jTable.getColumn("글쓴이").setPreferredWidth(50);
            jTable.getColumn("날짜").setPreferredWidth(50);
            jTable.getColumn("조회수").setPreferredWidth(20);

            CenterTableCellRenderer ctcr = new CenterTableCellRenderer();
            jTable.getColumn("번호").setCellRenderer(ctcr);
            jTable.getColumn("제목").setCellRenderer(ctcr);
            jTable.getColumn("글쓴이").setCellRenderer(ctcr);
            jTable.getColumn("날짜").setCellRenderer(ctcr);
            jTable.getColumn("조회수").setCellRenderer(ctcr);


            jTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int rowin = jTable.getSelectedRow();
                    if(rowin != -1) {
                        int num = (int) jTable.getValueAt(rowin,0);
                        ViewDialog2 dlog2 = new ViewDialog2(BoardApp.this,num);
                        dlog2.setVisible(true);
                    }
                }
            });
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
    public JPanel getPSouth() {
        if(pSouth == null) {
            pSouth = new JPanel();
            pSouth.add(getBtnInsert());
        }
        return pSouth;
    }


    public JButton getBtnInsert() {
        if(btnInsert == null) {
            btnInsert = new JButton();
            btnInsert.setText("추가");
            btnInsert.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ViewDialog dlog = new ViewDialog(BoardApp.this);
                    dlog.setVisible(true);
                }
            });
        }
        return btnInsert;
    }


    public void loadDataFromDatabase() {
        try {
            Connection con = ViewDialog.makeConnection();
            String sql = "SELECT num, title, name, date, Views FROM tb_board ORDER BY num";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) jTable.getModel();
            model.setRowCount(0); // 기존 행 제거

            while (rs.next()) {
                int num = rs.getInt("num");
                String title = rs.getString("title");
                String name = rs.getString("name");
                Date date = rs.getDate("date");
                int views = rs.getInt("Views");

                model.addRow(new Object[]{num, title, name, date, views});
            }

            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        new BoardApp();
    }

}
