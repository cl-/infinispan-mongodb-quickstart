package org.infinispan.quickstart.mongodb.client;

import com.google.gwt.user.client.rpc.AsyncCallback;


/**
 * @author navssurtani
 */

public interface CrudServiceAsync {
   void createValue(String k, String v, AsyncCallback<Boolean> async);
   void readValue(String k, AsyncCallback<String> async);
   void updateValue(String k, String newValue, AsyncCallback<Boolean> async);
   void deleteValue(String k, AsyncCallback<Boolean> async);
}
