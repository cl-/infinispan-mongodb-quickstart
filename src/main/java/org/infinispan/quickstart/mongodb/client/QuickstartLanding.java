package org.infinispan.quickstart.mongodb.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * The initial landing page for this GWT application.
 *
 * @author navssurtani
 */
public class QuickstartLanding implements EntryPoint {

   private static QuickstartLanding instance = new QuickstartLanding();

   /** Singleton */
   private QuickstartLanding() {
   }

   /** Static getter */
   public static QuickstartLanding getInstance() {
      return instance;
   }

   @Override
   public void onModuleLoad() {
      setEntryScreen();
   }

   private void setEntryScreen() {
      EntryScreen entry = new EntryScreen();
      entry.build();
      RootPanel.get().add(entry);
   }

   public void setCreateScreen() {
      CreateScreen create = new CreateScreen();
      create.build();
      RootPanel.get().add(create);
   }

   public void setReadScreen() {
      ReadScreen read = new ReadScreen();
      read.build();
      RootPanel.get().add(read);

   }

   public void setUpdateScreen() {
      UpdateScreen update = new UpdateScreen();
      update.build();
      RootPanel.get().add(update);
   }

   public void setDeleteScreen() {
      DeleteScreen delete = new DeleteScreen();
      delete.build();
      RootPanel.get().add(delete);
   }
}
