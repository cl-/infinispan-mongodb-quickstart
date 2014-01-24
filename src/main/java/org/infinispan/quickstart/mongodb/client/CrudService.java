package org.infinispan.quickstart.mongodb.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author navssurtani
 */
@RemoteServiceRelativePath("CrudService")
public interface CrudService extends RemoteService {
   public boolean createValue(String k, String v);
   public String readValue(String k);
   public boolean updateValue(String k, String newValue);
   public boolean deleteValue(String k);

   static class Util {
      private static CrudServiceAsync instance;

      public static CrudServiceAsync getInstance(){
         if (instance == null) {
            instance = (CrudServiceAsync) GWT.create(CrudService.class);
         }
         return instance;
      }
   }
}
