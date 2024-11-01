import com.utils.DbUtil;
import com.view.LogOnjf;
import com.view.MainFrm;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        DbUtil db = new DbUtil();
        try {
            db.getConnection();
            System.out.println("Connected to database");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Connection failed");
        }
        EventQueue.invokeLater(() -> {
            try {
                LogOnjf frame = new LogOnjf();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}