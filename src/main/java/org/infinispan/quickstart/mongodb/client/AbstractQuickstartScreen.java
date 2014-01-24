package org.infinispan.quickstart.mongodb.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author navssurtani
 */
public abstract class AbstractQuickstartScreen extends Widget {

   protected CrudServiceAsync crudService;

   public abstract void build();

   public AbstractQuickstartScreen() {
      this.crudService = CrudService.Util.getInstance();
   }

   protected void displayPopupBox(String header, String message, final Button focusButton) {
      final DialogBox popupBox = new DialogBox();
      popupBox.setText(header);
      final HTML errorLabel = new HTML();
      errorLabel.setHTML(message);
      VerticalPanel verticalPanel = new VerticalPanel();
      verticalPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
      final Button closeButton = new Button("Close");
      closeButton.setEnabled(true);
      closeButton.getElement().setId("close");
      closeButton.addClickHandler(new ClickHandler() {
         @Override
         public void onClick(ClickEvent event) {
            popupBox.hide();
            if (focusButton != null) {
               focusButton.setFocus(true);
               focusButton.setEnabled(true);
            }
         }
      });
      verticalPanel.add(errorLabel);
      verticalPanel.add(closeButton);
      popupBox.setWidget(verticalPanel);
      popupBox.center();
   }


   protected Button buildButton(String buttonName) {
      Button b = new Button(buttonName);
      b.setEnabled(true);
      b.getElement().setId(buttonName);
      return b;
   }

   protected void addToPanel(String panelName, Widget w) {
      RootPanel panel = RootPanel.get(panelName);
      panel.add(w);
   }

   protected void removeFromPanel(String panelName, Widget w) {
      RootPanel panel = RootPanel.get(panelName);
      panel.remove(w);
   }

}
