import java.util.Objects;

public class Exchange {
    private int id;
    private int r030;
    private String txt;
    private double rate;
    private String cc;
    private String exchangedate;


    public Exchange() {
    }

    public Exchange(int id, int r030, String txt, double rate, String cc, String exchangedate) {
        this.id = id;
        this.r030 = r030;
        this.txt = txt;
        this.rate = rate;
        this.cc = cc;
        this.exchangedate = exchangedate;
    }


    public Exchange(int r030, String txt, double rate, String cc, String exchangedate) {
        this.r030 = r030;
        this.txt = txt;
        this.rate = rate;
        this.cc = cc;
        this.exchangedate = exchangedate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getR030() {
        return r030;
    }

    public void setR030(int r030) {
        this.r030 = r030;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getExchangedate() {
        return exchangedate;
    }

    public void setExchangedate(String exchangedate) {
        this.exchangedate = exchangedate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exchange exchange = (Exchange) o;
        return id == exchange.id && r030 == exchange.r030 && Double.compare(exchange.rate, rate) == 0 && Objects.equals(txt, exchange.txt) && Objects.equals(cc, exchange.cc) && Objects.equals(exchangedate, exchange.exchangedate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, r030, txt, rate, cc, exchangedate);
    }

    @Override
    public String toString() {
        return "Exchange{" +
                ", r030=" + r030 +
                ", rate=" + rate +
                ", cc='" + cc + '\'' +
                ", exchangedate='" + exchangedate + '\'' +
                '}';
    }
}
