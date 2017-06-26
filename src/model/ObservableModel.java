package model;

import panels.ControllerPanel;
import panels.GraphPanel;
import panels.TextMessagePanel;
public interface ObservableModel {
        void registerTextPanel(TextMessagePanel textMessagePanel);
        void registerGraphPanel(GraphPanel graphPanel);
        void registerControllerPanel(ControllerPanel controllerPanel);
        void notifyTextMessagePanel();
        void notifyGraphPanel();
        void notifyControllerPanel();
}
