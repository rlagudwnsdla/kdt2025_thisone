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


public class ViewDialog2 extends JDialog {
    private int selectedNum;
    private BoardApp boardApp;
    private JPanel pCenter, pTitle, pContent, pWriter, pSouth;
    private JTextField txtTitle, txtWriter;
    private JTextArea txtContent;
    private JButton btnUp, btnDel, btnCan;


    public ViewDialog2(BoardApp boardApp,int num) {
        super(boardApp);
        this.boardApp = boardApp;
        this.setTitle("게시물 작성");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setModal(true);
        this.setSize(400, 270);
        this.selectedNum = num;

        this.setLocation(
                boardApp.getLocationOnScreen().x + (boardApp.getWidth() - this.getWidth()) / 2,
                boardApp.getLocationOnScreen().y + (boardApp.getHeight() - this.getHeight()) / 2
        );

        this.getContentPane().add(getPCenter(), BorderLayout.CENTER);
        this.getContentPane().add(getPSouth(), BorderLayout.SOUTH);

    }

    public static Connection makeConnection() {
        String url = "jdbc:mysql://localhost:3306/db_table";
        String userName = "root";
        String userPass = "khj01040322@";
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("데이터베이스 연결중 ...");
            con = DriverManager.getConnection(url, userName, userPass);
            System.out.println("데이터베이스 연결 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버를 찾지 못했습니다...");
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 실패");
            System.out.println(e.getMessage());
        }
        return con;

    }

    public JPanel getPCenter() {
        if (pCenter == null) {
            pCenter = new JPanel();
            pCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
            pCenter.add(getPTitle());
            pCenter.add(getPWriter());
            pCenter.add(getPContent());
        }
        return pCenter;
    }

    public JPanel getPTitle() {
        if (pTitle == null) {
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
            String result = "";
            try {
                Connection con = makeConnection();
                String sql = "SELECT title FROM tb_board WHERE num = ?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, selectedNum);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    txtTitle.setText(rs.getString("title"));
                }
                rs.close();
                pstmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pTitle;
    }

    public JPanel getPWriter() {
        if (pWriter == null) {
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

            String result = "";
            try {
                Connection con = makeConnection();
                String sql = "SELECT name FROM tb_board WHERE num = ?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, selectedNum);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    txtWriter.setText(rs.getString("name"));
                }
                rs.close();
                pstmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pWriter;
    }

    public JPanel getPContent() {
        if (pContent == null) {
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

            String result = "";
            try {
                Connection con = makeConnection();
                String sql = "SELECT Content FROM tb_board WHERE num = ?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, selectedNum);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    txtContent.setText(rs.getString("Content"));
                }
                rs.close();
                pstmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return pContent;
    }

    public JButton getBtnUp() {
        if (btnUp == null) {
            btnUp = new JButton("수정");
            btnUp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Connection con = makeConnection();
                        String sql = "UPDATE tb_board SET title = ?, name = ?, Content = ? WHERE num = ?";
                        PreparedStatement pstmt = con.prepareStatement(sql);

                        pstmt.setString(1, txtTitle.getText());
                        pstmt.setString(2, txtWriter.getText());
                        pstmt.setString(3, txtContent.getText());
                        pstmt.setInt(4, selectedNum);

                        int result = pstmt.executeUpdate();
                        if (result > 0) {
                            boardApp.loadDataFromDatabase();
                        }

                        pstmt.close();
                        con.close();
                    } catch (SQLException q) {
                        q.printStackTrace();
                    }
                }
            });
        }
        return btnUp;
    }

    public JButton getBtnDel() {
        if (btnDel == null) {
            btnDel = new JButton("삭제");
            btnDel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Connection con = makeConnection();
                        String sql = "DELETE FROM tb_board WHERE num = ?";
                        PreparedStatement pstmt = con.prepareStatement(sql);
                        pstmt.setInt(1, selectedNum);
                        int result = pstmt.executeUpdate();

                        if (result > 0) {
                            boardApp.loadDataFromDatabase();
                        }

                        pstmt.close();
                        con.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        return btnDel;
    }

    public JButton getBtnCan() {
        if(btnCan == null) {
            btnCan = new JButton("닫기");
            btnCan.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }
        return btnCan;
    }

    public JPanel getPSouth() {
        if (pSouth == null) {
            pSouth = new JPanel();
            pSouth.setBackground(Color.WHITE);


            pSouth.add(getBtnUp());
            pSouth.add(getBtnDel());
            pSouth.add(getBtnCan());
        }
        return pSouth;
    }
}
