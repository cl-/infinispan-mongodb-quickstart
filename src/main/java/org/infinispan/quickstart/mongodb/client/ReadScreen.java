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
public class ReadScreen extends AbstractQuickstartScreen {

   private final String PANEL_NAME = "readPanelContainer";

   private Grid readGrid;
   private TextBox keyBox;

   @Override
   public void build() {
      keyBox = new TextBox();
      keyBox.setTitle("Key");

      Button submitButton = buildButton("Submit");

      readGrid = new Grid(4, 3);

      readGrid.setWidget(0, 0, new Label("Enter the key to read"));
      readGrid.setWidget(2, 0, new Label("Key: "));
      readGrid.setWidget(2, 2, keyBox);
      readGrid.setWidget(3, 1, submitButton);

      submitButton.addClickHandler(new ReadHandler());

      addToPanel(PANEL_NAME, readGrid);
   }

   private class ReadHandler implements ClickHandler {
      @Override
      public void onClick(ClickEvent event) {
         performRPC(keyBox.getText());
      }

      private void performRPC(String key) {
         ReadScreen.this.crudService.readValue(key, getCallBack());
      }

      private AsyncCallback<String> getCallBack() {
         return new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
               displayPopupBox("Error with read operation.", caught.getMessage(), null);
            }

            @Override
            public void onSuccess(String result) {
               if (result != null) {
                  String message = "The value for key: " + keyBox.getText() + " is: " + result;
                  displayPopupBox("Read successfully", message, null);
               } else {
                  displayPopupBox("Null!", "The value for this key is null. It may not be in the cache.", null);
               }
               removeFromPanel(PANEL_NAME, readGrid);
            }
         };
      }
   }
}
