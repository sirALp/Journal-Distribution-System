import java.io.Serializable;
import java.util.Date;

public class DateInfo implements Serializable {
    private int startMonth;
    private int endMonth;
    private int startYear;

    public DateInfo(int startYear,int startMonth){
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.endMonth = (startMonth == 1 ? 12 : startMonth-1);
    }

    public int getStartMonth() {
        return startMonth;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    @Override
    public String toString() {
        return  "" +getStartMonth()+'/'+getStartYear() +
                " > " + getEndMonth()+'/' + (getEndMonth() == 12 ? getStartYear() : getStartYear()+1);
    }
}
