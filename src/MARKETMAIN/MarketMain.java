package MARKETMAIN;

import MARKETDAO.MarketSQL;
import MARKETDTO.Delivery;
import MARKETDTO.Member;
import MARKETDTO.Orderlist;
import MARKETDTO.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MarketMain {
    public static <let> void main(String[] args) {

        MarketSQL sql = new MarketSQL();
        Member mem = new Member();
        Product prod = new Product();
        Orderlist order = new Orderlist();
        Delivery del = new Delivery();
        Scanner sc = new Scanner(System.in);
        String loginId = null;
        int ordNum = 0;
        String seller = null;
        String buyer = null;
        int delKind = 0;
        int delNum = 0;

        boolean run = true;
        boolean run1 = true;
        boolean run2 = true;
        boolean run3 = true;
        boolean run4 = true;
        boolean run5 = true;
        boolean run6 = true;
        boolean run7 = true;

        int menu = 0;
        int menu1 = 0;
        int menu2 = 0;
        int menu3 = 0;
        int menu4 = 0;
        int menu5 = 0;
        int menu6 = 0;
        int menu7 = 0;
        int menu8 = 0;

        sql.connect();

        while (run) {
            System.out.println("----------------------------------------------------");
            System.out.println("[조삼모삼]마켓에 오신 것을 환영합니다.");
            System.out.println("(랜덤상품예정)");
            System.out.println("1.회원가입    2.로그인    3.아이디/비밀번호 찾기    4.종료 ");
            System.out.println("----------------------------------------------------");
            System.out.print("메뉴 선택 >> ");
            menu = sc.nextInt();
            System.out.println();

            switch (menu) {
                case 1:
                    System.out.println("------회원가입------");
                    System.out.print("회원 이름 >> ");
                    String memName = sc.next();
                    boolean idcheck = true;
                    while (idcheck) {
                        System.out.print("회원 아이디 >> ");
                        String memId = sc.next();
                        if (sql.checkId(memId)) {
                            System.out.print("회원 비밀번호 >> ");
                            String memPw = sc.next();
                            System.out.print("회원 연락처 >> ");
                            String memPhone = sc.next();
                            sc.nextLine();
                            System.out.print("회원 주소 >> ");
                            String memAddr = sc.nextLine();
                            int memNum = sql.memNumber() + 1;
                            System.out.println();

                            mem.setMemName(memName);
                            mem.setMemId(memId);
                            mem.setMemPw(memPw);
                            mem.setMemPhone(memPhone);
                            mem.setMemAddr(memAddr);
                            mem.setMemNum(memNum);

                            sql.memJoin(mem);
                            System.out.println();

                            idcheck = false;
                        }
                    }
                    break;

                case 2:
                    System.out.println("-----로그인------");
                    System.out.print("회원 아이디 >> ");
                    String memId = sc.next();

                    System.out.print("회원 비밀번호 >> ");
                    String memPw = sc.next();

                    if (sql.checkId2(memId, memPw)) {
                        System.out.println("로그인에 성공하셨습니다.");
                        System.out.println();
                        loginId = memId;

                        while (run1) {
                            System.out.println("-------------------------------------------");
                            System.out.println("1.상품등록   2.상품조회   3.내정보    4.로그아웃");
                            System.out.println("-------------------------------------------");
                            System.out.print("메뉴선택 >> ");
                            menu1 = sc.nextInt();
                            System.out.println();

                            switch (menu1) {
                                case 1:
                                    System.out.println("-------------------------------------------------------");
                                    System.out.println("                   상품 카테고리 등록                    ");
                                    System.out.println("1.패션의류/잡화  2.자전거  3.노트북    4.가전제품     5.도서");
                                    System.out.println("-------------------------------------------------------");
                                    System.out.print("상품 카테고리 선택 >> ");
                                    menu2 = sc.nextInt();
                                    int cateNum = menu2 * 10;
                                    System.out.println();

                                    System.out.print("신상 / 중고 >> ");
                                    String pNewUsed = sc.next();
                                    sc.nextLine();
                                    System.out.print("상품이름 >> ");
                                    String pName = sc.nextLine();
                                    System.out.print("상품가격 >> ");
                                    int pPrice = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("상품설명 >> ");
                                    String pMemo = sc.nextLine();

                                    int pLike = 0;
                                    int pNum = sql.prodNumber() + 1;

                                    prod.setMemId(loginId);
                                    prod.setpNum(pNum);
                                    prod.setpCategory(cateNum);
                                    prod.setpNewUsed(pNewUsed);
                                    prod.setpName(pName);
                                    prod.setpPrice(pPrice);
                                    prod.setpMemo(pMemo);

                                    sql.productInsert(prod);
                                    break;

                                case 2:
                                    while (run2) {
                                        System.out.println("--------------------------------------------");
                                        System.out.println("1.최신순    2.인기순     3.가격순     4.신상  ");
                                        System.out.println("5.중고      6.카테고리   7.구매       8.나가기");
                                        System.out.println("--------------------------------------------");
                                        System.out.print("메뉴 선택 >> ");
                                        menu2 = sc.nextInt();
                                        System.out.println();

                                        switch (menu2) {
                                            case 1:
                                                List<Product> plist2 = sql.checkDate();
                                                for (Product pd : plist2) {
                                                    System.out.println("번호: " + pd.getpNum());
                                                    System.out.println("이름 : " + pd.getpName());
                                                    System.out.println("가격 : " + pd.getpPrice());
                                                    System.out.println("등록날짜 : " + pd.getpDate());
                                                    System.out.println("상품설명 : " + pd.getpMemo());
                                                    System.out.println("신상/중고 : " + pd.getpNewUsed());
                                                    System.out.println("만족도 : " + pd.getpLike());
                                                    System.out.println("판매자 ID : " + pd.getMemId());
                                                    System.out.println();
                                                }
                                                break;

                                            case 2:
                                                List<Product> plist3 = sql.checkLike();
                                                for (Product pl : plist3) {
                                                    System.out.println("번호: " + pl.getpNum());
                                                    System.out.println("이름 : " + pl.getpName());
                                                    System.out.println("가격 : " + pl.getpPrice());
                                                    System.out.println("등록날짜 : " + pl.getpDate());
                                                    System.out.println("상품설명 : " + pl.getpMemo());
                                                    System.out.println("신상/중고 : " + pl.getpNewUsed());
                                                    System.out.println("만족도 : " + pl.getpLike());
                                                    System.out.println("판매자 ID : " + pl.getMemId());
                                                    System.out.println();
                                                }
                                                break;

                                            case 3:
                                                List<Product> plist4 = sql.checkPrice();
                                                for (Product pr : plist4) {
                                                    System.out.println("번호: " + pr.getpNum());
                                                    System.out.println("이름 : " + pr.getpName());
                                                    System.out.println("가격 : " + pr.getpPrice());
                                                    System.out.println("등록날짜 : " + pr.getpDate());
                                                    System.out.println("상품설명 : " + pr.getpMemo());
                                                    System.out.println("신상/중고 : " + pr.getpNewUsed());
                                                    System.out.println("만족도 : " + pr.getpLike());
                                                    System.out.println("판매자 ID : " + pr.getMemId());
                                                    System.out.println();
                                                }
                                                break;

                                            case 4:
                                                List<Product> plist5 = sql.checkNew();
                                                for (Product pn : plist5) {
                                                    System.out.println("번호: " + pn.getpNum());
                                                    System.out.println("이름 : " + pn.getpName());
                                                    System.out.println("가격 : " + pn.getpPrice());
                                                    System.out.println("등록날짜 : " + pn.getpDate());
                                                    System.out.println("상품설명 : " + pn.getpMemo());
                                                    System.out.println("신상/중고 : " + pn.getpNewUsed());
                                                    System.out.println("만족도 : " + pn.getpLike());
                                                    System.out.println("판매자 ID : " + pn.getMemId());
                                                    System.out.println();
                                                }
                                                break;

                                            case 5:
                                                List<Product> plist6 = sql.checkUsed();
                                                for (Product pu : plist6) {
                                                    System.out.println("번호: " + pu.getpNum());
                                                    System.out.println("이름 : " + pu.getpName());
                                                    System.out.println("가격 : " + pu.getpPrice());
                                                    System.out.println("등록날짜 : " + pu.getpDate());
                                                    System.out.println("상품설명 : " + pu.getpMemo());
                                                    System.out.println("신상/중고 : " + pu.getpNewUsed());
                                                    System.out.println("만족도 : " + pu.getpLike());
                                                    System.out.println("판매자 ID : " + pu.getMemId());
                                                    System.out.println();
                                                }
                                                break;

                                            case 6:
                                                System.out.println("---------------------------------------------------------");
                                                System.out.println("                       상품 카테고리                       ");
                                                System.out.println("1.패션의류/잡화    2.자전거    3.노트북    4.가전제품    5.도서 ");
                                                System.out.println("---------------------------------------------------------");
                                                System.out.print("조회할 상품 카테고리 선택 >> ");
                                                menu3 = sc.nextInt();
                                                cateNum = menu3 * 10;
                                                System.out.println();

                                                boolean check = sql.checkCategory(cateNum);

                                                if (check) {
                                                    List<Product> plist = sql.selectproduct(cateNum);
                                                    for (Product p : plist) {
                                                        System.out.println("번호: " + p.getpNum());
                                                        System.out.println("이름 : " + p.getpName());
                                                        System.out.println("가격 : " + p.getpPrice());
                                                        System.out.println("등록날짜 : " + p.getpDate());
                                                        System.out.println("상품설명 : " + p.getpMemo());
                                                        System.out.println("신상/중고 : " + p.getpNewUsed());
                                                        System.out.println("만족도 : " + p.getpLike());
                                                        System.out.println("판매자 ID : " + p.getMemId());
                                                        System.out.println();
                                                    }
                                                } else {
                                                    System.out.println("해당되는 상품이 존재하지 않습니다.");
                                                }
                                                break;

                                            case 7:
                                                while (run3) {
                                                    System.out.println("--------------------");
                                                    System.out.println("1.상품선택  2.뒤로가기 ");
                                                    System.out.println("--------------------");
                                                    System.out.print("메뉴 선택 >> ");
                                                    menu4 = sc.nextInt();

                                                    switch (menu4){
                                                        case 1:
                                                            System.out.print("구입할 상품번호를 선택해주세요 >> ");
                                                            pNum = sc.nextInt();

                                                            List<Product> plist7 = sql.searchpNum(pNum);
                                                            for (Product pro : plist7) {
                                                                System.out.println("번호: " + pro.getpNum());
                                                                System.out.println("이름 : " + pro.getpName());
                                                                System.out.println("가격 : " + pro.getpPrice());
                                                                System.out.println("등록날짜 : " + pro.getpDate());
                                                                System.out.println("상품설명 : " + pro.getpMemo());
                                                                System.out.println("신상/중고 : " + pro.getpNewUsed());
                                                                System.out.println("만족도 : " + pro.getpLike());
                                                                System.out.println("판매자 ID : " + pro.getMemId());

                                                                boolean check1 = sql.checkpNum(pNum);

                                                                if (check1) {
                                                                    while (run4){
                                                                        System.out.println("---------------------");
                                                                        System.out.println("1.구매하기   2.이전화면 ");
                                                                        System.out.println("---------------------");
                                                                        System.out.print("메뉴 선택 >> ");
                                                                        menu5 = sc.nextInt();
                                                                        System.out.println();
                                                                        switch (menu5) {
                                                                            case 1:
                                                                                ordNum = sql.orderNum() + 1;
                                                                                seller = pro.getMemId();
                                                                                buyer = loginId;

                                                                                order.setOrdNum(ordNum);
                                                                                order.setpNum(pNum);
                                                                                order.setSeller(seller);
                                                                                order.setBuyer(buyer);

                                                                                if (sql.orderInsert(order)) {
                                                                                    System.out.print("배송정보  1.직거래(대면)   2.택배  >> ");
                                                                                    delKind = sc.nextInt();
                                                                                    int kind = 0;
                                                                                    int delPrice = 0;
                                                                                    if (delKind == 1) {
                                                                                        del.setDelPrice(delPrice);
                                                                                        del.setDelResult("직거래");
                                                                                        kind = 1;
                                                                                    } else {
                                                                                        delPrice = 3000;
                                                                                        del.setDelPrice(delPrice);
                                                                                        del.setDelResult("배송준비중");
                                                                                        kind = 2;
                                                                                    }
                                                                                    buyer = loginId;

                                                                                    delNum = sql.delNum() + 1;
                                                                                    del.setDelNum(delNum);
                                                                                    del.setpNum(pNum);
                                                                                    del.setDelKind(kind);
                                                                                    int totalPrice = delPrice + pro.getpPrice();
                                                                                    del.setBuyer(buyer);

                                                                                    del.setTotalPrice(totalPrice);

                                                                                    sql.delInsert(del);

                                                                                    int  point = (int)(sql.pointcheck(del, loginId)+(totalPrice*0.03));
                                                                                    sql.updatePoint(point);

                                                                                } else {
                                                                                    System.out.println("해당하는 상품이 존재하지 않습니다.");
                                                                                }
                                                                                break;

                                                                            case 2:
                                                                                System.out.println("이전화면으로 돌아갑니다.");
                                                                                run4 = false;
                                                                                break;

                                                                            default:
                                                                                System.out.println("잘못 선택하셨습니다.");
                                                                                break;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            continue;
                                                        case 2:
                                                            run3 = false;
                                                            System.out.println("이전화면으로 돌아갑니다.");
                                                            break;

                                                        default:
                                                            System.out.println("잘못 선택하셨습니다.");
                                                            break;
                                                        }
                                                    }
                                                continue;
                                            case 8:
                                                run2 = false;
                                                System.out.println("이전화면으로 돌아갑니다.");
                                                break;

                                            default:
                                                System.out.println("잘못선택하셨습니다. 다시 선택해주세요");
                                                break;
                                        }
                                    }
                                    break;

                                case 3:
                                    while (run5) {
                                        System.out.println("----------------------------------");
                                        System.out.println("1.개인정보    2.구매이력    3.판매이력");
                                        System.out.println("4.배송현황    5.나가기  ");
                                        System.out.println("----------------------------------");
                                        System.out.print("메뉴 선택 >> ");
                                        menu6 = sc.nextInt();
                                        System.out.println();

                                        switch (menu6) {
                                            case 1:
                                                while (run6) {
                                                    System.out.println("-----------------------------------------");
                                                    System.out.println("1.개인정보 조회   2.개인정보 수정   3.나가기 ");
                                                    System.out.println("-----------------------------------------");
                                                    System.out.print("메뉴 선택 >> ");
                                                    menu7 = sc.nextInt();
                                                    System.out.println();

                                                    switch (menu7) {
                                                        case 1:
                                                            List<Member> mlist = sql.selectMemId(loginId);
                                                            for (Member m : mlist) {
                                                                System.out.println("-----------회원정보----------");
                                                                System.out.println("회원 번호 : " + m.getMemNum());
                                                                System.out.println("회원 이름 : " + m.getMemName());
                                                                System.out.println("회원 아이디 : " + m.getMemId());
                                                                System.out.println("회원 비밀번호 : " + m.getMemPw());
                                                                System.out.println("회원 연락처 : " + m.getMemPhone());
                                                                System.out.println("회원 주소 : " + m.getMemAddr());
                                                                System.out.println("회원 포인트 : " + m.getPoint());
                                                            }
                                                            break;

                                                        case 2:
                                                            System.out.print("회원 아이디 >> ");
                                                            memId = sc.next();

                                                            System.out.print("회원 비밀번호 >> ");
                                                            memPw = sc.next();

                                                            boolean checkID = sql.checkMemId(memId, memPw);

                                                            if(checkID){
                                                                System.out.print("변경할 회원 이름 >> ");
                                                                memName = sc.next();

                                                                System.out.print("변경할 회원 비밀번호 >> ");
                                                                memPw = sc.next();

                                                                System.out.print("변경할 회원 연락처 >> ");
                                                                String memPhone = sc.next();

                                                                sc.nextLine();
                                                                System.out.print("변경할 회원 주소 >> ");
                                                                String memAddr = sc.nextLine();

                                                                mem.setMemId(memId);

                                                                mem.setMemName(memName);
                                                                mem.setMemPw(memPw);
                                                                mem.setMemPhone(memPhone);
                                                                mem.setMemAddr(memAddr);

                                                                sql.memModify(mem);
                                                            } else {
                                                                System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
                                                            }
                                                            break;

                                                        case 3:
                                                            run6 = false;
                                                            System.out.println("이전화면으로 돌아갑니다.");
                                                            break;

                                                        default:
                                                            System.out.println("잘못선택하셨습니다.");
                                                            break;
                                                    }
                                                }
                                                break;

                                            case 2:
                                                buyer = loginId;
                                                List<Orderlist> buyList = sql.findBuyList(buyer);
                                                for (Orderlist buy : buyList){
                                                    System.out.println("-----------구매이력----------");
                                                    System.out.println("주문 번호 : " + buy.getOrdNum());
                                                    System.out.println("상품 번호 : " + buy.getpNum());
                                                    List<Product> buyprod = sql.findBuyProd(buy.getpNum());
                                                    for (Product bp : buyprod){
                                                        System.out.println("상품 이름 : " + bp.getpName());
                                                        System.out.println("상품 가격 : " + bp.getpPrice());
                                                        System.out.println("상품 등록날짜 : " + bp.getpDate());
                                                        System.out.println("상품 설명 : " + bp.getpMemo());
                                                        System.out.println("신상/중고 : " + bp.getpNewUsed());
                                                        System.out.println("만족도 : " + bp.getpLike());
                                                    }
                                                    System.out.println("판매자 ID : " + buy.getSeller());
                                                    System.out.println("구매날짜 : " + buy.getSellDate());
                                                    System.out.println();
                                                }
                                                break;

                                            case 3:
                                                seller = loginId;
                                                List<Orderlist> sellList = sql.findSellList(seller);
                                                for (Orderlist sell : sellList){
                                                    System.out.println("-----------판매이력----------");
                                                    System.out.println("주문 번호 : " + sell.getOrdNum());
                                                    System.out.println("상품 번호 : " + sell.getpNum());
                                                    List<Product> sellprod = sql.findSellProd(sell.getpNum());
                                                    for (Product sp : sellprod){
                                                        System.out.println("상품 이름 : " + sp.getpName());
                                                        System.out.println("상품 가격 : " + sp.getpPrice());
                                                        System.out.println("상품 등록날짜 : " + sp.getpDate());
                                                        System.out.println("상품 설명 : " + sp.getpMemo());
                                                        System.out.println("신상/중고 : " + sp.getpNewUsed());
                                                        System.out.println("만족도 : " + sp.getpLike());
                                                    }
                                                    System.out.println("구매자 ID : " + sell.getBuyer());
                                                    System.out.println("판매날짜 : " + sell.getSellDate());
                                                    System.out.println();
                                                }
                                                break;

                                            case 4:
                                                buyer = loginId;
                                                List<Delivery> delivery = sql.selectdelivery(buyer);
                                                for (Delivery deli : delivery){
                                                    System.out.println("----------배송현황-----------");
                                                    System.out.println("배송 번호 : " + deli.getDelNum());
                                                    System.out.println("물품 번호 : " + deli.getpNum());
                                                    System.out.println("배송시작일 : " + deli.getsDate());
                                                    System.out.println("배송 상황 : " + deli.getDelResult());
                                                    System.out.println("배송완료일 : " + deli.getrDate());
                                                    System.out.println("배송 형태 : " + deli.getDelKind());
                                                    System.out.println("배송비 : " + deli.getDelPrice());
                                                    System.out.println("총가격 : " + deli.getTotalPrice());
                                                    System.out.println();
                                                }
                                                break;

                                            case 5:
                                                run5 = false;
                                                System.out.println("이전화면으로 돌아갑니다.");
                                                break;

                                            default:
                                                System.out.println("잘못선택하셨습니다.");
                                                break;
                                        }
                                    }
                                    break;

                                case 4:
                                    run1 = false;
                                    System.out.println("로그아웃 했습니다.");
                                    break;

                                default:
                                    System.out.println("잘못 선택하셨습니다.");
                                    break;
                            }
                        }
                    }else {
                        System.out.println("로그인에 실패하셨습니다.");
                    }
                    break;

                case 3:
                    while (run7){
                        System.out.println("-------------------------------");
                        System.out.println("1.아아디/비밀번호 찾기   2.뒤로가기");
                        System.out.println("-------------------------------");
                        System.out.print("메뉴 선택 >> ");
                        menu8 = sc.nextInt();

                        switch (menu8){
                            case 1:
                                System.out.print("회원이름을 입력해주세요 >> ");
                                memName = sc.next();
                                System.out.print("회원 전화변호를 입력해주세요 >> ");
                                String memPhone = sc.next();

                                boolean check2 = sql.selectMemName(memName, memPhone);
                                if(check2){
                                    List<Member> mlist1 = sql.fineMemId(memName, memPhone);
                                    for (Member mf : mlist1) {
                                        System.out.println("-----------회원정보----------");
                                        System.out.println("회원 이름 : " + mf.getMemName());
                                        System.out.println("회원 아이디 : " + mf.getMemId());
                                        System.out.println("회원 비밀번호 : " + mf.getMemPw());
                                    }
                                } else {
                                    System.out.println("해당되는 회원정보가 존재하지 않습니다.");
                                }
                                break;

                            case 2:
                                System.out.println("이전화면으로 돌아갑니다.");
                                run7 = false;
                                break;

                            default:
                                System.out.println("잘못 입력하셨습니다.");
                                break;
                        }
                    }
                    break;

                case 4:
                    run = false;
                    System.out.println("프로그램을 종료합니다.");
                    break;

                default:
                    System.out.println("잘못 선택하셨습니다.");
                    break;
            }
        }
    }
}



