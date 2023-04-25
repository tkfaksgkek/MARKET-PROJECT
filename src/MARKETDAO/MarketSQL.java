package MARKETDAO;

import MARKETDTO.Delivery;
import MARKETDTO.Member;
import MARKETDTO.Orderlist;
import MARKETDTO.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarketSQL {

    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public void connect() {
        con = DBC.DBConnect();
    }

    public void conClose() {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkId(String memId) {
        boolean checkId = false;
        String sql = "SELECT * FROM MEMBER WHERE MEMID=?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memId);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("아이디가 중복 됩니다. 다시 입력해주세요. ");
            } else {
                checkId = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return checkId;
    }

    public int memNumber() {
        int memNum = 0;
        String sql = "SELECT MAX(MEMNUM) FROM MEMBER";

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                memNum = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return memNum;
    }


    public void memJoin(Member mem) {
        String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?)";

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, mem.getMemNum());
            pstmt.setString(2, mem.getMemName());
            pstmt.setString(3, mem.getMemId());
            pstmt.setString(4, mem.getMemPw());
            pstmt.setString(5, mem.getMemPhone());
            pstmt.setString(6, mem.getMemAddr());
            pstmt.setInt(7, mem.getPoint());

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("회원가입에 성공하셨습니다.");
                System.out.println(mem.toString());
            } else {
                System.out.println("회원가입에 실패하셨습니다.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkId2(String memId, String memPw) {
        boolean check = false;
        String sql = "SELECT * FROM MEMBER WHERE MEMID=? AND MEMPW=?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memId);
            pstmt.setString(2, memPw);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                check = true;
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public int prodNumber() {
        int pNum = 0;
        String sql = "SELECT MAX(PNUM) FROM PRODUCT";

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                pNum = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pNum;
    }

    public void productInsert(Product prod) {
        String sql = "INSERT INTO PRODUCT VALUES(?,?,?,?,?,?,?,SYSDATE,?)";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, prod.getpNum());
            pstmt.setString(2, prod.getpName());
            pstmt.setString(3, prod.getpNewUsed());
            pstmt.setInt(4, prod.getpCategory());
            pstmt.setInt(5, prod.getpLike());
            pstmt.setInt(6, prod.getpPrice());
            pstmt.setString(7, prod.getpMemo());
            pstmt.setString(8, prod.getMemId());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("상품을 등록하셨습니다.");
                System.out.println(prod.toString());
            } else {
                System.out.println("상품등록에 실패하셨습니다.");
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkCategory(int cateNum) {
        boolean check = false;
        String sql = "SELECT * FROM CATEGORY WHERE CATENUM = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, cateNum);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                check = true;
            } else {
                check = false;
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }


    public List<Product> selectproduct(int cateNum) {
        List<Product> plist = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT WHERE PCATEGORY=?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, cateNum);

            rs = pstmt.executeQuery();
            int cnt = 0;
            while (rs.next()) {
                Product p = new Product();
                p.setpNum(rs.getInt("pNum"));
                p.setpName(rs.getString("pName"));
                p.setpNewUsed(rs.getString("pNewused"));
                p.setpCategory(rs.getInt("pCategory"));
                p.setpLike(rs.getInt("pLike"));
                p.setpPrice(rs.getInt("pPrice"));
                p.setpMemo(rs.getString("pMemo"));
                p.setpDate(rs.getString("pDate"));
                p.setMemId(rs.getString("memId"));

                plist.add(p);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return plist;
    }

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


    public List<Member> selectMemId(String memId) {
        List<Member> mlist = new ArrayList<>();
        String sql = "SELECT * FROM MEMBER WHERE MEMID=?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                int cnt = 0;
                Member m = new Member();
                m.setMemNum(rs.getInt("memNum"));
                m.setMemName(rs.getString("memName"));
                m.setMemId(rs.getString("memId"));
                m.setMemPw(rs.getString("memPw"));
                m.setMemPhone(rs.getString("memPhone"));
                m.setMemAddr(rs.getString("memAddr"));
                m.setPoint(rs.getInt("point"));
                mlist.add(m);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mlist;
    }


    public List<Product> checkDate() {
        List<Product> plist2 = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT ORDER BY PDATE DESC";

        try {
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {

                Product pd = new Product();
                pd.setpNum(rs.getInt("pNum"));
                pd.setpName(rs.getString("pName"));
                pd.setpNewUsed(rs.getString("pNewused"));
                pd.setpCategory(rs.getInt("pCategory"));
                pd.setpLike(rs.getInt("pLike"));
                pd.setpPrice(rs.getInt("pPrice"));
                pd.setpMemo(rs.getString("pMemo"));
                pd.setpDate(rs.getString("pDate"));
                pd.setMemId(rs.getString("memId"));

                plist2.add(pd);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return plist2;
    }


    public List<Product> checkLike() {
        List<Product> plist3 = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT ORDER BY PLIKE DESC";

        try {
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {

                Product pl = new Product();
                pl.setpNum(rs.getInt("pNum"));
                pl.setpName(rs.getString("pName"));
                pl.setpNewUsed(rs.getString("pNewused"));
                pl.setpCategory(rs.getInt("pCategory"));
                pl.setpLike(rs.getInt("pLike"));
                pl.setpPrice(rs.getInt("pPrice"));
                pl.setpMemo(rs.getString("pMemo"));
                pl.setpDate(rs.getString("pDate"));
                pl.setMemId(rs.getString("memId"));

                plist3.add(pl);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return plist3;
    }

    public List<Product> checkPrice() {
        List<Product> plist4 = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT ORDER BY PPRICE DESC";

        try {
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {

                Product pr = new Product();
                pr.setpNum(rs.getInt("pNum"));
                pr.setpName(rs.getString("pName"));
                pr.setpNewUsed(rs.getString("pNewused"));
                pr.setpCategory(rs.getInt("pCategory"));
                pr.setpLike(rs.getInt("pLike"));
                pr.setpPrice(rs.getInt("pPrice"));
                pr.setpMemo(rs.getString("pMemo"));
                pr.setpDate(rs.getString("pDate"));
                pr.setMemId(rs.getString("memId"));

                plist4.add(pr);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return plist4;
    }

    public List<Product> checkNew() {
        List<Product> plist5 = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT WHERE PNEWUSED = '신상'";

        try {
            pstmt = con.prepareStatement(sql);

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

                plist5.add(pn);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return plist5;
    }

    public List<Product> checkUsed() {
        List<Product> plist6 = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT WHERE PNEWUSED = '중고'";

        try {
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {

                Product pu = new Product();
                pu.setpNum(rs.getInt("pNum"));
                pu.setpName(rs.getString("pName"));
                pu.setpNewUsed(rs.getString("pNewused"));
                pu.setpCategory(rs.getInt("pCategory"));
                pu.setpLike(rs.getInt("pLike"));
                pu.setpPrice(rs.getInt("pPrice"));
                pu.setpMemo(rs.getString("pMemo"));
                pu.setpDate(rs.getString("pDate"));
                pu.setMemId(rs.getString("memId"));

                plist6.add(pu);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return plist6;
    }

    public boolean checkMemId(String memId, String memPw) {
        boolean check = false;
        String sql = "SELECT * FROM MEMBER WHERE MEMID = ? AND MEMPW = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memId);
            pstmt.setString(2, memPw);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                check = true;
            } else {
                check = false;
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public void memModify(Member mem) {
        System.out.println(mem);

        String sql = "UPDATE MEMBER SET MEMNAME = ?, MEMPW = ?, " +
                "MEMPHONE = ?, MEMADDR = ? WHERE MEMID = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mem.getMemName());
            pstmt.setString(2, mem.getMemPw());
            pstmt.setString(3, mem.getMemPhone());
            pstmt.setString(4, mem.getMemAddr());
            pstmt.setString(5, mem.getMemId());

            int result = pstmt.executeUpdate();
            if (result > 0){
                System.out.println("회원정보가 수정되었습니다.");
            } else {
                System.out.println("회원정보수정에 실패하셨습니다.");
            }
            pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public List<Product> searchpNum(int pNum) {
        List<Product> searchpNum = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT WHERE PNUM=?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, pNum);

            rs = pstmt.executeQuery();
            while (rs.next()) {

                Product pro = new Product();
                pro.setpNum(rs.getInt("pNum"));
                pro.setpName(rs.getString("pName"));
                pro.setpNewUsed(rs.getString("pNewused"));
                pro.setpCategory(rs.getInt("pCategory"));
                pro.setpLike(rs.getInt("pLike"));
                pro.setpPrice(rs.getInt("pPrice"));
                pro.setpMemo(rs.getString("pMemo"));
                pro.setpDate(rs.getString("pDate"));
                pro.setMemId(rs.getString("memId"));

                searchpNum.add(pro);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return searchpNum;
    }

    public boolean checkpNum(int pNum) {
        boolean check = false;
        String sql = "SELECT * FROM PRODUCT WHERE PNUM = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, pNum);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                check = true;
            } else {
                check = false;
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public int orderNum() {
        int ordNum = 0;
        String sql = "SELECT MAX(ORDNUM) FROM ORDERLIST";

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()){
                ordNum = rs.getInt(1);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordNum;
    }

    public boolean orderInsert(Orderlist order) {
        boolean checkOrder = false;
        String  sql = "INSERT INTO ORDERLIST VALUES(?,?,?,?,SYSDATE)";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,order.getOrdNum());
            pstmt.setInt(2,order.getpNum());
            pstmt.setString(3, order.getSeller());
            pstmt.setString(4,order.getBuyer());

            rs = pstmt.executeQuery();

            if(rs.next()){
                checkOrder = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return checkOrder;
    }

    public int delNum() {
        int delNum = 0;
        String sql = "SELECT MAX(DELNUM) FROM DELIVERY";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
                delNum = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return delNum;
    }

    public void delInsert(Delivery del) {
        String sql = "INSERT INTO DELIVERY VALUES(?,?,SYSDATE,SYSDATE,?,?,?,?,?)";

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, del.getDelNum());
            pstmt.setInt(2, del.getpNum());
            pstmt.setString(3, del.getDelResult());
            pstmt.setInt(4, del.getDelKind());
            pstmt.setInt(5, del.getDelPrice());
            pstmt.setInt(6, del.getTotalPrice());
            pstmt.setString(7, del.getBuyer());

            int result = pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean selectMemName(String memName, String memPhone) {
        boolean check = false;
        String sql = "SELECT * FROM MEMBER WHERE MEMNAME = ? AND MEMPHONE = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memName);
            pstmt.setString(2, memPhone);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                check = true;
            } else {
                check = false;
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public List<Member> fineMemId(String memName, String memPhone) {
        List<Member> mlist1 = new ArrayList<>();
        String sql = "SELECT * FROM MEMBER WHERE MEMNAME=? AND MEMPHONE = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memName);
            pstmt.setString(2, memPhone);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Member mf = new Member();
                mf.setMemNum(rs.getInt("memNum"));
                mf.setMemName(rs.getString("memName"));
                mf.setMemId(rs.getString("memId"));
                mf.setMemPw(rs.getString("memPw"));
                mf.setMemPhone(rs.getString("memPhone"));
                mf.setMemAddr(rs.getString("memAddr"));
                mf.setPoint(rs.getInt("point"));
                mlist1.add(mf);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mlist1;
    }

    public List<Orderlist> findBuyList(String buyer) {
        List<Orderlist> buyList = new ArrayList<>();
        String sql = "SELECT * FROM ORDERLIST WHERE BUYER=?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, buyer);

            rs = pstmt.executeQuery();
            while (rs.next()) {

                Orderlist buy = new Orderlist();

                buy.setOrdNum(rs.getInt("ordNum"));
                buy.setpNum(rs.getInt("pNum"));
                buy.setSeller(rs.getString("seller"));
                buy.setBuyer(rs.getString("buyer"));
                buy.setSellDate(rs.getString("sellDate"));

                buyList.add(buy);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return buyList;
    }

    public List<Orderlist> findSellList(String seller) {
        List<Orderlist> sellList = new ArrayList<>();
        String sql = "SELECT * FROM ORDERLIST WHERE SELLER=?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, seller);

            rs = pstmt.executeQuery();
            while (rs.next()) {

                Orderlist sell = new Orderlist();

                sell.setOrdNum(rs.getInt("ordNum"));
                sell.setpNum(rs.getInt("pNum"));
                sell.setSeller(rs.getString("seller"));
                sell.setBuyer(rs.getString("buyer"));
                sell.setSellDate(rs.getString("sellDate"));

                sellList.add(sell);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sellList;
    }

    public List<Delivery> selectdelivery(String buyer) {
        List<Delivery> selectdelivery = new ArrayList<>();
        String sql = "SELECT * FROM DELIVERY WHERE BUYER=?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, buyer);

            rs = pstmt.executeQuery();
            while (rs.next()) {

                Delivery deli = new Delivery();

                deli.setDelNum(rs.getInt("delNum"));
                deli.setpNum(rs.getInt("pNum"));
                deli.setsDate(rs.getString("sDate"));
                deli.setrDate(rs.getString("rDate"));
                deli.setDelResult(rs.getString("delResult"));
                deli.setDelKind(rs.getInt("delKind"));
                deli.setDelPrice(rs.getInt("delPrice"));
                deli.setTotalPrice(rs.getInt("totalPrice"));
                deli.setBuyer(rs.getString("buyer"));

                selectdelivery.add(deli);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectdelivery;
    }

    public List<Product> findBuyProd(int i) {
        List<Product> findBuyProd = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT WHERE PNUM = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, i);

            rs = pstmt.executeQuery();
            while (rs.next()) {

                Product bp = new Product();
                bp.setpNum(rs.getInt("pNum"));
                bp.setpName(rs.getString("pName"));
                bp.setpNewUsed(rs.getString("pNewused"));
                bp.setpCategory(rs.getInt("pCategory"));
                bp.setpLike(rs.getInt("pLike"));
                bp.setpPrice(rs.getInt("pPrice"));
                bp.setpMemo(rs.getString("pMemo"));
                bp.setpDate(rs.getString("pDate"));
                bp.setMemId(rs.getString("memId"));

                findBuyProd.add(bp);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findBuyProd;
    }

    public List<Product> findSellProd(int i) {
        List<Product> findSellProd = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT WHERE PNUM = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, i);

            rs = pstmt.executeQuery();
            while (rs.next()) {

                Product bp = new Product();
                bp.setpNum(rs.getInt("pNum"));
                bp.setpName(rs.getString("pName"));
                bp.setpNewUsed(rs.getString("pNewused"));
                bp.setpCategory(rs.getInt("pCategory"));
                bp.setpLike(rs.getInt("pLike"));
                bp.setpPrice(rs.getInt("pPrice"));
                bp.setpMemo(rs.getString("pMemo"));
                bp.setpDate(rs.getString("pDate"));
                bp.setMemId(rs.getString("memId"));

                findSellProd.add(bp);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findSellProd;
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

    public void updatePoint(int point) {
        String sql = "UPDATE MEMBER SET POINT = ? ";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,point);

            int result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int pointcheck(Delivery del, String loginId) {
        int point = 0;
        String sql = "SELECT POINT FROM MEMBER, DELIVERY WHERE BUYER = MEMID AND BUYER = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, loginId);

            rs = pstmt.executeQuery();

            while (rs.next()){
                point =rs.getInt(1);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return point;

    }
}




