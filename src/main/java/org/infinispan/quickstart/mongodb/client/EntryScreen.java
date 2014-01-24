package org.infinispan.quickstart.mongodb.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author navssurtani
 */
public class EntryScreen extends AbstractQuickstartScreen {

   private RootPanel entryPanel = RootPanel.get("entryPanelContainer");

   private Grid g;

   public EntryScreen() {
      super();
   }

   @Override
   public void build() {
      g = new Grid(7, 3);

      g.setWidget(2, 0, new Label("What would you like to do?"));
      g.setWidget(4, 0, createButton());
      g.setWidget(4, 2, readButton());
      g.setWidget(6, 0, updateButton());
      g.setWidget(6, 2, deleteButton());

      entryPanel.add(g);

   }

   private Button deleteButton() {
      Button delete = buildButton("Delete");
      delete.addClickHandler(new ClickHandler() {
         @Override
         public void onClick(ClickEvent event) {
            // Navigate to the DeleteScreen and clear up this panel.
            QuickstartLanding.getInstance().setDeleteScreen();
            entryPanel.remove(g);
         }
      });
      return delete;
   }

   private Button updateButton() {
      Button update = buildButton("Update");
      update.addClickHandler(new ClickHandler() {
         @Override
         public void onClick(ClickEvent event) {
            QuickstartLanding.getInstance().setUpdateScreen();
            entryPanel.remove(g);
         }
      });
      return update;
   }

   private Button readButton() {
      Button read = buildButton("Read");
      read.addClickHandler(new ClickHandler() {
         @Override
         public void onClick(ClickEvent event) {
            QuickstartLanding.getInstance().setReadScreen();
            entryPanel.remove(g);
         }
      });
      return read;
   }

   private Button createButton() {
      Button create = buildButton("Create");
      create.addClickHandler(new ClickHandler() {
         @Override
         public void onClick(ClickEvent event) {
            QuickstartLanding.getInstance().setCreateScreen();
            entryPanel.remove(g);
         }
      });
      return create;
   }
}
