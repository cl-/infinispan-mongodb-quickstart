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
public class DeleteScreen extends AbstractQuickstartScreen {

   private final String PANEL_NAME = "deletePanelContainer";

   private Grid deleteGrid;
   private TextBox keyBox;

   @Override
   public void build() {
      keyBox = new TextBox();
      keyBox.setTitle("Key");

      Button submitButton = buildButton("Submit");

      deleteGrid = new Grid(4, 3);

      deleteGrid.setWidget(0, 0, new Label("Enter the key for which you want to delete from the cache"));
      deleteGrid.setWidget(2, 0, new Label("Key: "));
      deleteGrid.setWidget(2, 2, keyBox);
      deleteGrid.setWidget(3, 1, submitButton);

      submitButton.addClickHandler(new DeleteHandler());

      addToPanel(PANEL_NAME, deleteGrid);
   }


   private class DeleteHandler implements ClickHandler {
      @Override
      public void onClick(ClickEvent event) {
         performRPC(keyBox.getText());
      }

      private void performRPC(String k) {
         DeleteScreen.this.crudService.deleteValue(k, getCallBack());
      }

      private AsyncCallback<Boolean> getCallBack() {
         return new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
               displayPopupBox("Error with delete operation.", caught.getMessage(), null);
            }

            @Override
            public void onSuccess(Boolean result) {
               if (result) {
                  String message = "Successfully removed key: " + keyBox.getText();
                  displayPopupBox("Success!", message, null);
               } else {
                  String message = "Failed to remove key: " + keyBox.getText() + ". See server logs.";
                  displayPopupBox("Failure!", message, null);
               }
               removeFromPanel(PANEL_NAME, deleteGrid);
            }
         };

      }
   }
}
