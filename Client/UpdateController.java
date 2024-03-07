package Client;

import Model.Update;
import View.MainFrame;

public class UpdateController {
    private OutputHandler outputHandler;
    private MainFrame mainFrame;

    public void updateGUI(Update update) {
        mainFrame.transferUpdate(update);
    }
}
