package model;

import panels.GraphPanel;
import panels.TextMessagePanel;
public interface ObservableModel {
        void registerTextPanel(TextMessagePanel textMessagePanel);
        void registerGraphPanel(GraphPanel graphPanel);
        void notifyTextMessagePanel();
        void notifyGraphPanel();
}
