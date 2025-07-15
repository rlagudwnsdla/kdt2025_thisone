package cc33.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;


public class ViewDialog extends JDialog {
    private BoardApp boardApp;
    private JPanel pCenter, pTitle, pContent, pWriter, pSouth;
    private JTextField txtTitle, txtWriter;
    private JTextArea txtContent;
    private JButton btnOk, btnCancel;
    int z=1;
    int x=0;




    public ViewDialog(BoardApp boardApp) {
        super(boardApp);
        this.boardApp = boardApp;
        this.setTitle("게시물 작성");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setModal(true);
        this.setSize(400, 270);

        this.setLocation(
                boardApp.getLocationOnScreen().x + (boardApp.getWidth()-this.getWidth())/2,
                boardApp.getLocationOnScreen().y + (boardApp.getHeight()-this.getHeight())/2
        );

        this.getContentPane().add(getPCenter(), BorderLayout.CENTER);
        this.getContentPane().add(getPSouth(), BorderLayout.SOUTH);

    }

    public static Connection makeConnection() {
        String url = "jdbc:mysql://localhost:3306/db_table";
        String userName="root";
        String userPass = "khj01040322@";
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("데이터베이스 연결중 ...");
            con = DriverManager.getConnection(url, userName, userPass);
            System.out.println("데이터베이스 연결 성공");
        }catch(ClassNotFoundException e) {
            System.out.println("JDBC 드라이버를 찾지 못했습니다...");
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 실패");
            System.out.println(e.getMessage());
        }
        return con;

    }

    public JPanel getPCenter() {
        if(pCenter==null) {
            pCenter = new JPanel();
            pCenter.setBorder(new EmptyBorder(10,10,10,10));
            pCenter.add(getPTitle());
            pCenter.add(getPWriter());
            pCenter.add(getPContent());
        }
        return pCenter;
    }

    public JPanel getPTitle() {
        if(pTitle==null) {
            pTitle = new JPanel();
            pTitle.setLayout(new BorderLayout());
            JLabel label = new JLabel("제목");
            label.setAlignmentX(JLabel.CENTER);
            label.setPreferredSize(new Dimension(70, 30));
            label.setHorizontalAlignment(JLabel.CENTER);
            pTitle.add(label, BorderLayout.WEST);
            txtTitle = new JTextField();
            txtTitle.setPreferredSize(new Dimension(300, 30));
            pTitle.add(txtTitle, BorderLayout.CENTER);
        }
        return pTitle;
    }

    public JPanel getPWriter() {
        if(pWriter==null) {
            pWriter = new JPanel();
            pWriter.setLayout(new BorderLayout());
            JLabel label = new JLabel("글쓴이");
            label.setAlignmentX(JLabel.CENTER);
            label.setPreferredSize(new Dimension(70, 30));
            label.setHorizontalAlignment(JLabel.CENTER);
            pWriter.add(label, BorderLayout.WEST);
            txtWriter = new JTextField();
            txtWriter.setPreferredSize(new Dimension(300, 30));
            pWriter.add(txtWriter, BorderLayout.CENTER);
        }
        return pWriter;
    }

    public JPanel getPContent() {
        if(pContent == null) {
            pContent = new JPanel();
            pContent.setLayout(new BorderLayout());
            JLabel label = new JLabel("내용");
            label.setAlignmentX(JLabel.CENTER);
            label.setPreferredSize(new Dimension(70, 30));
            label.setHorizontalAlignment(JLabel.CENTER);
            pContent.add(label, BorderLayout.WEST);
            txtContent = new JTextArea();
            txtContent.setBorder(new EtchedBorder());
            txtContent.setPreferredSize(new Dimension(300, 100));
            pContent.add(txtContent, BorderLayout.CENTER);
        }
        return pContent;
    }

    public JPanel getPSouth() {
        if(pSouth == null) {
            pSouth = new JPanel();
            pSouth.setBackground(Color.WHITE);
            pSouth.add(getBtnOk());
            pSouth.add(getBtnCancel());
        }
        return pSouth;
    }

    public JButton getBtnOk() {
        if(btnOk == null) {
            btnOk = new JButton();
            btnOk.setText("저장");
            btnOk.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Connection con = makeConnection();
                        Statement stmt = con.createStatement();
                        StringBuilder sql = new StringBuilder();

                        sql.append("INSERT INTO tb_board (num, title, name, Content, date, Views) VALUES (?, ?, ?, ?, ?, ?)"
);
                        PreparedStatement pstmt = con.prepareStatement(sql.toString());

                        pstmt.setInt(1,++z);
                        pstmt.setString(2,txtTitle.getText());
                        pstmt.setString(3,txtWriter.getText());
                        pstmt.setString(4,txtContent.getText());
                        pstmt.setDate(5,new java.sql.Date(System.currentTimeMillis()));
                        pstmt.setInt(6,x);
                        pstmt.executeUpdate();

                        Object[] rowdata = {++z, txtTitle.getText(), txtWriter.getText(), new java.sql.Date(System.currentTimeMillis()), x};

                        DefaultTableModel tableModel = (DefaultTableModel)boardApp.getJTable().getModel();
                        tableModel.addRow(rowdata);



                    } catch (SQLException q) {
                        System.out.println(q.getMessage());

                    }
                    makeConnection();
                }
            });
        }
        return btnOk;
    }

    public JButton getBtnCancel() {
        if(btnCancel == null) {
            btnCancel = new JButton();
            btnCancel.setText("취소");
            btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }
        return btnCancel;
    }
}
