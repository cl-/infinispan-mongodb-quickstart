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
public class CreateScreen extends AbstractQuickstartScreen {

   private final String PANEL_NAME = "createPanelContainer";

   private TextBox keyBox = null;
   private TextBox valueBox = null;

   private Grid createGrid = null;


   @Override
   public void build() {
      keyBox = new TextBox();
      keyBox.setTitle("Key");

      valueBox = new TextBox();
      valueBox.setTitle("Value");

      Button submitButton = buildButton("Submit");

      createGrid = new Grid(6, 3);
      createGrid.setWidget(0, 0, new Label("Enter a key/value pair"));
      createGrid.setWidget(2, 0, new Label("Key: "));
      createGrid.setWidget(2, 2, keyBox);
      createGrid.setWidget(4, 0, new Label("Value: "));
      createGrid.setWidget(4, 2, valueBox);
      createGrid.setWidget(5, 1, submitButton);

      addToPanel(PANEL_NAME, createGrid);

      // Add the click handler for the submit button to make the RPC call.
      submitButton.addClickHandler(new CreateHandler());
   }

   private class CreateHandler implements ClickHandler {

      @Override
      public void onClick(ClickEvent event) {
         performRPC(keyBox.getText(), valueBox.getText());
      }

      private void performRPC(String key, String value) {
         CreateScreen.this.crudService.createValue(key, value, getCallBack(key));

      }

      private AsyncCallback<Boolean> getCallBack(final String key) {
         return new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
               displayPopupBox("Error with create operation", caught.getMessage(), null);
            }

            @Override
            public void onSuccess(Boolean result) {
               if (result) {
                  displayPopupBox("Success!", "Successfully created value for key: " + key, null);
               } else {
                  displayPopupBox("Failure!", "Failed to create value for key: " + key, null);
               }
               removeFromPanel(PANEL_NAME, createGrid);
            }
         };
      }
   }
}

