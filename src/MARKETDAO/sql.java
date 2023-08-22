package MARKETDAO;

import MARKETDTO.Orderlist;
import MARKETDTO.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class sql {

    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public boolean checkName(String pName) {
        boolean checkN = false;
        String sql = "SELECT * FROM PRODUCT WHERE PNAME LIKE ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + pName + "%");

            rs = pstmt.executeQuery();

            if (rs.next()) {
                checkN = true;
            } else {
                checkN = false;
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return checkN;
    }

    public List<Product> selectName(String pName) {
        List<Product> plist1 = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT WHERE PNAME LIKE ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + pName + "%");

            rs = pstmt.executeQuery();
            while (rs.next()) {

                Product pn = new Product();
                pn.setpNum(rs.getInt("pNum"));
                pn.setpName(rs.getString("pName"));
                pn.setpNewUsed(rs.getString("pNewused"));
                pn.setpCategory(rs.getInt("pCategory"));
                pn.setpLike(rs.getInt("pLike"));
                pn.setpPrice(rs.getInt("pPrice"));
                pn.setpMemo(rs.getString("pMemo"));
                pn.setpDate(rs.getString("pDate"));
                pn.setMemId(rs.getString("memId"));

                plist1.add(pn);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return plist1;
    }

    public boolean checkMember() {
        boolean check = false;
        String sql = "SELECT * FROM MEMBER WHERE MEMID";

        try {
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                check = true;
            }

            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public List<Orderlist> odrList() {
        List<Orderlist> odrList = new ArrayList<>();
        String sql = "SELECT * FROM ORDERLIST";

        try {
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Orderlist ordl = new Orderlist();
                ordl.setOrdNum(rs.getInt("ordNum"));
                ordl.setpNum(rs.getInt("pNum"));
                ordl.setSeller(rs.getString("seller"));
                ordl.setBuyer(rs.getString("buyer"));
                ordl.setSellDate(rs.getString("sellDate"));

                odrList.add(ordl);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return odrList;
    }

    public List<Product> selectOrdlist(String loginId) {
        List<Product> selectOrdlist = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT WHERE MEMID";

        try {
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {

                Product po = new Product();
                po.setpNum(rs.getInt("pNum"));
                po.setpName(rs.getString("pName"));
                po.setpNewUsed(rs.getString("pNewused"));
                po.setpCategory(rs.getInt("pCategory"));
                po.setpLike(rs.getInt("pLike"));
                po.setpPrice(rs.getInt("pPrice"));
                po.setpMemo(rs.getString("pMemo"));
                po.setpDate(rs.getString("pDate"));
                po.setMemId(rs.getString("memId"));

                selectOrdlist.add(po);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectOrdlist;
    }

    public void pointSearch(String loginId) {
        String sql = "SELECT POINT FROM MEMBER WHERE MEMID=? ";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, loginId);

            rs = pstmt.executeQuery();

            while (rs.next()){
                System.out.println("포인트 금액 : "+rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
