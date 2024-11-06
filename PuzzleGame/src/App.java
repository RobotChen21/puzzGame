import com.xidian.domin.User;
import com.xidian.ui.LoginJFrame;
import com.xidian.ui.gameJFrame;

import java.io.IOException;


public class App {//throws IOException, ClassNotFoundException
    public static void main(String[] args) {
//        new LoginJFrame();
        new gameJFrame(new User("zhangsan","123456"));
    }
}
