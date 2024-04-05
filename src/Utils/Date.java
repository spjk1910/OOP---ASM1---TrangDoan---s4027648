package Utils;

public class Date
{
    private int day;
    private int month;
    private  int year;
    public  Date(String date)
    {
        String[] tmp = date.split("/");

        day = Integer.parseInt(tmp[0]);
        month = Integer.parseInt(tmp[1]);
        year = Integer.parseInt(tmp[2]);
    }
    public Date(int day, int month, int year)
    {
        this.day = day;
        this.month = month;
        this.year = year;
    }


}
