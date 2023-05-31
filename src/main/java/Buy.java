public class Buy {

    private String title;
    private String dateY;
    private String dateM;
    private String dateD;
    private int sum;

    public Buy(String title, String dateY, String dateM, String dateD, int sum) {
        this.title = title;
        this.dateY = dateY;
        this.dateM = dateM;
        this.dateD = dateD;
        this.sum = sum;
    }

    public String getTitle() {
        return title;
    }

    public String getDateY() {
        return dateY;
    }

    public String getDateM() {
        return dateM;
    }

    public String getDateD() {
        return dateD;
    }

    public int getSum() {
        return sum;
    }
}
