/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-2-4
 */
package org.zengsource.portal.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.zengsource.portal.dao.MenuDao;
import org.zengsource.portal.model.Menu;
import org.zengsource.portal.model.Module;

/**
 * @author zsn
 * @since 6.0
 */
public class MenuServiceImpl implements MenuService {

	// + STATIC ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	private MenuDao menuDao;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public void save(Menu menu) {
		if (menu == null) {
			return;
		}
		this.menuDao.save(menu);
	};
	
	public List<?> search(Module module, int start, int limit) {
		return this.menuDao.queryByModule(module, start, limit);
	}

	public void reload(Module module) {
		List<?> oldMenus = this.search(module, 0, 0);
		Set<Menu> newMenus = module.getMenuSet();
		Set<Menu> menuSet = new HashSet<Menu>();
		Set<Menu> ignoredSet = new HashSet<Menu>();
		for (Object obj : oldMenus) { // TODO 算法优化
			Menu oldMenu = (Menu) obj;
			for (Menu newMenu : newMenus) {
				if (oldMenu.getName().equals(newMenu.getName())) {
					oldMenu.copy(newMenu);
					this.save(oldMenu); // 更新
					// newPortlets.remove(newPortlet);
					ignoredSet.add(newMenu);
					menuSet.add(oldMenu);
				}
			}
		}
		// 增加新菜单
		for (Menu newMenu : newMenus) {
			boolean exists = false;
			for (Menu ignore : ignoredSet) {
				if (newMenu.equals(ignore)) {
					exists = true;
					break;
				}
			}
			if (!exists) { // 新增
				newMenu.setModule(module);
				this.save(newMenu); // 保存
				menuSet.add(newMenu);
			}
		}
		// 取回
		module.setMenuSet(menuSet);
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	
	public MenuDao getMenuDao() {
		return menuDao;
	}
	
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
