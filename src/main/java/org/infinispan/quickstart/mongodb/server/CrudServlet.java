package org.infinispan.quickstart.mongodb.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.quickstart.mongodb.client.CrudService;
import org.jboss.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

/**
 * @author navssurtani
 */
public class CrudServlet extends RemoteServiceServlet implements CrudService {

   private static final Logger logger = Logger.getLogger(CrudServlet.class);

   private Cache<String, String> cache;

   // Over-riding the init().
   @Override
   public void init() throws ServletException {
      if (logger.isInfoEnabled()) logger.info("Instantiating CrudServlet");
      buildAndConfigureCache();
   }

   @Override
   public boolean createValue(String key, String value) {
      logger.info("Creating entry into Cache with <K,V>: " + key + ":" + value);
      cache.put(key, value);
      return cache.get(key).equals(value);
   }

   @Override
   public String readValue(String key) {
      logger.info("Reading value in Cache with <K>: " + key);
      return cache.get(key);
   }

   @Override
   public boolean updateValue(String key, String newValue) {
      logger.info("Updating entry into Cache to <K,V>: " + key + ":" + newValue);
      cache.replace(key, newValue);
      return cache.get(key).equals(newValue);
   }

   @Override
   public boolean deleteValue(String key) {
      logger.info("Removing entry from Cache of <K>: " + key);
      cache.remove(key);
      return !cache.containsKey(key);
   }

   private void buildAndConfigureCache() {
      if (logger.isInfoEnabled()) logger.info("About to build and configure Infinispan");

      // Globan ISPN configuration
      GlobalConfigurationBuilder gcb = new GlobalConfigurationBuilder();
      gcb.globalJmxStatistics()
            .enable()
            .allowDuplicateDomains(true);
      GlobalConfiguration global = gcb.build();

      if (!global.globalJmxStatistics().allowDuplicateDomains()) logger.error("Global JMX statistics need to be set");

      Configuration cacheConfiguration = buildCacheConfiguration();
      DefaultCacheManager cacheManager = new DefaultCacheManager(global, cacheConfiguration);
      cache = cacheManager.getCache();

      if (cache == null) {
         logger.warn("Cache is null. There's a problem here.");
      } else {
         logger.info("Cache instantiated.");
         logger.info("Cache configuration: " + cacheConfiguration.toString());
      }

      logger.info("Cleared build and configure.");

   }

   private Configuration buildCacheConfiguration() {
      logger.info("Building configuration for ISPN-Store");

      String location = null;
      try {
         location = findStoreLocation();
      }
      catch (NamingException e) {
         logger.error("Couldn't read file name!", e);
      }
      logger.info("Store location: " + location);

      // First we put together the configuration for the cache.
      ConfigurationBuilder builder = new ConfigurationBuilder();
      builder
            .eviction()
               .maxEntries(10)
            .jmxStatistics()
               .enable()
            .persistence()
               .addSingleFileStore()
                  .preload(true)
                  .location(location);

      // Finally we just call build() on the builder and return it.
      return builder.build();
   }

   private String findStoreLocation() throws NamingException {
      Context ctx = (Context) new InitialContext().lookup("java:comp/env");
      return (String) ctx.lookup("infinispan.cachestore.location");
   }
}

