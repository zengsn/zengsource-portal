/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-4
 */
package org.zengsource.portal;

import org.apache.log4j.Logger;
import org.zengsource.portal.service.ModuleService;

/**
 * 启动器：初始化模块。
 * @author zsn
 * @since 6.0
 */
public class Bootstrap {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	Logger logger = Logger.getLogger(getClass());
	
	private ModuleService moduleService;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public Bootstrap() {
	}

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public void startup() {
		logger.info("初始化系统模块***************************************");
		//this.moduleService.init();
		logger.info("初始化系统模块***********************************完成!");
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public ModuleService getModuleService() {
		return moduleService;
	}
	
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
