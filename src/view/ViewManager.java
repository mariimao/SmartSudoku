package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View manager which implements PropertyChangeListener
 */

public class ViewManager implements PropertyChangeListener {

    private final CardLayout cardLayout;

    private final JPanel views;

    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for View Manager
     *
     * @param views            views, is JPanel object
     * @param cardLayout,      card layout, is CardLayout object
     * @param viewManagerModel the view manager model, is ViewManagerModel object
     */

    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    /**
     * Records and notifies of any property change
     *
     * @param evt the propertychange event that is fired by the viewmodel
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            String viewModelName = (String) evt.getNewValue();
            cardLayout.show(views, viewModelName);
        }
    }
}
