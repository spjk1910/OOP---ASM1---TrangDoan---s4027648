import Module.*;
import Utils.Date;
import Utils.LoadData;
import Utils.SaveData;
import View.ClaimView;
import Controller.ClaimController;

import java.io.IOException;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        ClaimProcessManagerImplement manager = new ClaimProcessManagerImplement();

        SaveData save = new SaveData();

        LoadData load = new LoadData();

        ClaimController controller = new ClaimController(manager, null,save,load,manager);

        ClaimView view = new ClaimView(controller);

        controller.setView(view);

        controller.application();
    }
}

