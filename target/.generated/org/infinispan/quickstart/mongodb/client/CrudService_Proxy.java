package org.infinispan.quickstart.mongodb.client;

import com.google.gwt.user.client.rpc.impl.RemoteServiceProxy;
import com.google.gwt.user.client.rpc.impl.ClientSerializationStreamWriter;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.impl.RequestCallbackAdapter.ResponseReader;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.RpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.core.client.impl.Impl;
import com.google.gwt.user.client.rpc.impl.RpcStatsContext;

public class CrudService_Proxy extends RemoteServiceProxy implements org.infinispan.quickstart.mongodb.client.CrudServiceAsync {
  private static final String REMOTE_SERVICE_INTERFACE_NAME = "org.infinispan.quickstart.mongodb.client.CrudService";
  private static final String SERIALIZATION_POLICY ="F6B10B7BECD22354F954835FBD7FB0A9";
  private static final org.infinispan.quickstart.mongodb.client.CrudService_TypeSerializer SERIALIZER = new org.infinispan.quickstart.mongodb.client.CrudService_TypeSerializer();
  
  public CrudService_Proxy() {
    super(GWT.getModuleBaseURL(),
      "CrudService", 
      SERIALIZATION_POLICY, 
      SERIALIZER);
  }
  
  public void createValue(java.lang.String k, java.lang.String v, com.google.gwt.user.client.rpc.AsyncCallback async) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("CrudService_Proxy", "createValue");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(k);
      streamWriter.writeString(v);
      helper.finish(async, ResponseReader.BOOLEAN);
    } catch (SerializationException ex) {
      async.onFailure(ex);
    }
  }
  
  public void deleteValue(java.lang.String k, com.google.gwt.user.client.rpc.AsyncCallback async) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("CrudService_Proxy", "deleteValue");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(k);
      helper.finish(async, ResponseReader.BOOLEAN);
    } catch (SerializationException ex) {
      async.onFailure(ex);
    }
  }
  
  public void readValue(java.lang.String k, com.google.gwt.user.client.rpc.AsyncCallback async) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("CrudService_Proxy", "readValue");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(k);
      helper.finish(async, ResponseReader.STRING);
    } catch (SerializationException ex) {
      async.onFailure(ex);
    }
  }
  
  public void updateValue(java.lang.String k, java.lang.String newValue, com.google.gwt.user.client.rpc.AsyncCallback async) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("CrudService_Proxy", "updateValue");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(k);
      streamWriter.writeString(newValue);
      helper.finish(async, ResponseReader.BOOLEAN);
    } catch (SerializationException ex) {
      async.onFailure(ex);
    }
  }
  @Override
  public SerializationStreamWriter createStreamWriter() {
    ClientSerializationStreamWriter toReturn =
      (ClientSerializationStreamWriter) super.createStreamWriter();
    if (getRpcToken() != null) {
      toReturn.addFlags(ClientSerializationStreamWriter.FLAG_RPC_TOKEN_INCLUDED);
    }
    return toReturn;
  }
  @Override
  protected void checkRpcTokenType(RpcToken token) {
    if (!(token instanceof com.google.gwt.user.client.rpc.XsrfToken)) {
      throw new RpcTokenException("Invalid RpcToken type: expected 'com.google.gwt.user.client.rpc.XsrfToken' but got '" + token.getClass() + "'");
    }
  }
}
