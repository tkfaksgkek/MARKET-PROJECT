package MARKETDTO;

public class Product {
    private int pNum;
    private String pName;
    private String pNewUsed;
    private int pCategory;
    private int pLike;
    private int pPrice;
    private String pMemo;
    private String pDate;
    private String memId;

    public int getpNum() {
        return pNum;
    }

    public void setpNum(int pNum) {
        this.pNum = pNum;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpNewUsed() {
        return pNewUsed;
    }

    public void setpNewUsed(String pNewUsed) {
        this.pNewUsed = pNewUsed;
    }

    public int getpCategory() {
        return pCategory;
    }

    public void setpCategory(int pCategory) {
        this.pCategory = pCategory;
    }

    public int getpLike() {
        return pLike;
    }

    public void setpLike(int pLike) {
        this.pLike = pLike;
    }

    public int getpPrice() {
        return pPrice;
    }

    public void setpPrice(int pPrice) {
        this.pPrice = pPrice;
    }

    public String getpMemo() {
        return pMemo;
    }

    public void setpMemo(String pMemo) {
        this.pMemo = pMemo;
    }

    public String getpDate() {
        return pDate;
    }

    public void setpDate(String pDate) {
        this.pDate = pDate;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pNum=" + pNum +
                ", pName=" + pName +
                ", pNewUsed=" + pNewUsed +
                ", pCategory=" + pCategory +
                ", pLike=" + pLike +
                ", pPrice=" + pPrice +
                ", pMemo=" + pMemo +
                ", pDate=" + pDate +
                ", memId=" + memId +
                '}';
    }
}
