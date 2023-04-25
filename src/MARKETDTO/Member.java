package MARKETDTO;

public class Member {
    private int memNum;
    private String memName;
    private String memId;
    private String memPw;
    private String memPhone;
    private String memAddr;
    private int point;

    public int getMemNum() {
        return memNum;
    }

    public void setMemNum(int memNum) {
        this.memNum = memNum;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getMemPw() {
        return memPw;
    }

    public void setMemPw(String memPw) {
        this.memPw = memPw;
    }

    public String getMemPhone() {
        return memPhone;
    }

    public void setMemPhone(String memPhone) {
        this.memPhone = memPhone;
    }

    public String getMemAddr() {
        return memAddr;
    }

    public void setMemAddr(String memAddr) {
        this.memAddr = memAddr;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "member{" +
                "memNum=" + memNum +
                ", memName='" + memName + '\'' +
                ", memId='" + memId + '\'' +
                ", memPw='" + memPw + '\'' +
                ", memPhone='" + memPhone + '\'' +
                ", memAddr='" + memAddr + '\'' +
                ", point='" + point + '\'' +
                '}';
    }
}
