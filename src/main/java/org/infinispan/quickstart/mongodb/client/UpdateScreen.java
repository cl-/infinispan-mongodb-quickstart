package org.infinispan.quickstart.mongodb.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author navssurtani
 */
public class UpdateScreen extends AbstractQuickstartScreen {

   private final String PANEL_NAME = "updatePanelContainer";

   private TextBox keyBox = null;
   private TextBox valueBox = null;

   private Grid updateGrid = null;

   @Override
   public void build() {
      keyBox = new TextBox();
      keyBox.setTitle("Key");

      valueBox = new TextBox();
      valueBox.setTitle("Value");

      Button submitButton = buildButton("Submit");

      updateGrid = new Grid(6, 3);
      updateGrid.setWidget(0, 0, new Label("Enter a new value for a given key."));
      updateGrid.setWidget(2, 0, new Label("Key: "));
      updateGrid.setWidget(2, 2, keyBox);
      updateGrid.setWidget(4, 0, new Label("Value: "));
      updateGrid.setWidget(4, 2, valueBox);
      updateGrid.setWidget(5, 1, submitButton);

      addToPanel(PANEL_NAME, updateGrid);

      submitButton.addClickHandler(new UpdateHandler());
   }

   private class UpdateHandler implements ClickHandler {

      @Override
      public void onClick(ClickEvent event) {
         performRPC(keyBox.getText(), valueBox.getText());
      }

      private void performRPC(String k, String v) {
         crudService.updateValue(k, v, getCallBack());

      }

      private AsyncCallback<Boolean> getCallBack() {
         return new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
               displayPopupBox("Error with create operation", caught.getMessage(), null);
            }

            @Override
            public void onSuccess(Boolean result) {
               if (result) {
                  String message = "Updated value for key: " + keyBox.getText() + " to value: " + valueBox.getText();
                  displayPopupBox("Success!", message, null);
               } else {
                  String message = "Could not update value for key: " + keyBox.getText() + ". Check server logs.";
                  displayPopupBox("Failure!", message, null);
               }
               removeFromPanel(PANEL_NAME, updateGrid);
            }
         };
      }
   }
}
