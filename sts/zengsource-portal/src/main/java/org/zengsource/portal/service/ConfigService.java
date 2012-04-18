/**
 * 
 */
package org.zengsource.portal.service;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import org.zengsource.portal.model.Config;

/**
 * @author snzeng
 * @since 6.0
 */
public interface ConfigService extends Serializable {

	// Initialization //////////////////////////////////////////////////////

	public void initConfigs(String xmlPath) ;
	
	public Properties getDefaults(); 

	// Management //////////////////////////////////////////////////////////
	
	public void save(Config config) ;

	public Config getById(int id) ;

	public List<?> getConfigsByGroup(String group) ;

	public int searchCount(String query) ;

	public List<?> searchByGroup(String query, String group, int start, int limit) ;

	// Application /////////////////////////////////////////////////////////

	public Config getByKey(String key) ;

	public String getString(String key) ;

	public boolean getBoolean(String key) ;
	
	// Convenience ////////////////////////////////////////////////////////
	
	public String getSiteHome() ;
	
	public String getSiteUpload() ;
	
	public String getSiteTmp() ;
	
	public String getExtHome() ;
	
}
