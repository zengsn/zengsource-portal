/**
 * &copy; 2011-2012 ZengSource.com
 * 2012-1-16
 */
package org.zengsource.portal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zsn
 * @since 6.0
 */
public class Page implements Serializable{

	// + STATIC +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	private static final long serialVersionUID = 1L;

	public static final String OPT_INFO = "info";
	public static final String OPT_PORTLET = "portlet";

	// + FIELDS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //
	private int id;
	private String url;
	private String name;
	private String cls;
	private String style;
	private Integer columns = 1;
	private Integer width;// = 1020;
	private Integer height;// = 500;
	private String roles;
	private Module module;
	private Portlet defaultPortlet;
	private Set<PortletInstance> portletInstanceSet;

	private String description;
	private Date createdTime;
	private Date updatedTime;

	// + CSTORS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	// + METHODS +++++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public void copy(Page p) {
		this.name = p.name;
		this.cls = p.cls;
		this.style = p.style;
		this.columns = p.columns;
		this.roles = p.roles;
		this.description = p.description;
		this.portletInstanceSet = p.portletInstanceSet;
	}

	public void addPortlet(PortletInstance instance) {
		if (getPortletInstanceSet() == null) {
			setPortletInstanceSet(new HashSet<PortletInstance>());
		}
		this.portletInstanceSet.add(instance);
		instance.addPage(this);
	}

	public PortletInstance removePortlet(String instanceId) {
		if (instanceId == null) {
			return null;
		}
		for (PortletInstance instance : this.portletInstanceSet) {
			if (instance != null && instanceId.equals(instance.getId())) {
				this.portletInstanceSet.remove(instance);
				instance.removePage(this);
				return instance;
			}
		}
		return null;
	}

	public List<PortletInstance> getSortedPortletInstances() {
		List<PortletInstance> sorted = new ArrayList<PortletInstance>();
		sorted.addAll(this.portletInstanceSet);
		Collections.sort(sorted, new Comparator<PortletInstance>() {
			public int compare(PortletInstance o1, PortletInstance o2) {
				return o1.getIndex() - o2.getIndex();
			}
		});
		return sorted;
	}

	public Integer getBaseCells() {
		int baseCells = 0;
		for (PortletInstance block : this.portletInstanceSet) {
			if (block != null) {
				baseCells += block.getColspan() + block.getRowspan() - 1;
			}
		}
		return baseCells;
	}

	public void movePortlet(int oldIndex, int newIndex) {
		if (newIndex > 0 && newIndex < this.getPortletInstanceSet().size()) {
			PortletInstance oldBlock = getPortletInstance(oldIndex);
			PortletInstance newBlock = getPortletInstance(newIndex);
			if (oldBlock != null && newBlock != null) {
				oldBlock.setIndex(newIndex);
				newBlock.setIndex(oldIndex);
			}
		} else {

		}
	}

	public PortletInstance getPortletInstance(int index) {
		for (PortletInstance ins : this.portletInstanceSet) {
			if (ins.getIndex() == index) {
				return ins;
			}
		}
		return null;
	}

	public String getCurrentPortlets() {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (PortletInstance ins : this.portletInstanceSet) {
			if (ins != null && ins.getPortlet() != null) {
				count++;
				sb.append(ins.getName()).append(", ");
			}
		}
		if (sb.lastIndexOf(",") > -1) {
			sb.deleteCharAt(sb.length() - 1);
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.insert(0, "[" + count + "] ");
		return sb.toString();
	}

	public int getNumOfBlocks() {
		int count = 0;
		for (PortletInstance ins : this.portletInstanceSet) {
			if (ins != null && ins.getPortlet() != null) {
				count++;
			}
		}
		return count;
	}

	public List<String> getJsRequiredModules() {
		if (this.portletInstanceSet == null) {
			return null;
		}
		Set<String> moduleNames = new HashSet<String>();
		for (PortletInstance pi : this.portletInstanceSet) {
			if (pi.getPortlet().getJsRequiredModule() != null) {
				moduleNames.add(pi.getPortlet().getJsRequiredModule());
			}
		}
		return new ArrayList<String>(moduleNames);
	}

	public List<Module> getUsedModules() {
		if (this.portletInstanceSet == null) {
			return null;
		}
		Map<String, Module> moduleMap = new HashMap<String, Module>();
		for (PortletInstance pi : this.portletInstanceSet) {
			moduleMap.put(pi.getPortlet().getModule().getName(), pi.getPortlet().getModule());
		}
		return new ArrayList<Module>(moduleMap.values());
	}

	/** TODO 计算页面的高度。 */
	public int getFittedHeight() {
		int fittedHeight = 200;
		if (this.portletInstanceSet != null) {
			for (PortletInstance pi : this.portletInstanceSet) {
				fittedHeight += pi.getHeight();
			}
		} else {
			fittedHeight = 500;
		}
		return fittedHeight;
	}
	public int getFittedWidth() {
		return 1020;
	}

	@Override
	public String toString() {
		return this.url;
	}

	// + G^SETTERS +++++++++++++++++++++++++++++++++++++++++++++++++++++ //

	public String getUrl() {
		return url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Integer getColumns() {
		return columns;
	}

	public void setColumns(Integer columns) {
		this.columns = columns;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	
	public Integer getWidth() {
		return width;
	}
	
	public void setWidth(Integer width) {
		this.width = width;
	}
	
	public Integer getHeight() {
		return height;
	}
	
	public void setHeight(Integer height) {
		this.height = height;
	}

	public Portlet getDefaultPortlet() {
		return defaultPortlet;
	}

	public void setDefaultPortlet(Portlet defaultBlock) {
		this.defaultPortlet = defaultBlock;
	}

	public Set<PortletInstance> getPortletInstanceSet() {
		return portletInstanceSet;
	}

	public void setPortletInstanceSet(Set<PortletInstance> blockInstances) {
		this.portletInstanceSet = blockInstances;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	// + MAIN^TEST +++++++++++++++++++++++++++++++++++++++++++++++++++++ //
}
