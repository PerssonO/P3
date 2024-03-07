package Model;

 import java.io.Serializable;
 import java.util.Date;

public class ModelLog implements Serializable {
    private Date date;
    private String str;

    public ModelLog(String str){
        this.date = new Date();
        this.str = str;
    }

    @Override
    public String toString() {
        return date + " " + str;
    }
}
