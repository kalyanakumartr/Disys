package org.hbs.disys.bo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hbs.disys.dao.PropertyDAO;
import org.hbs.disys.util.CommonValidator;
import org.hbs.disys.util.model.ConstProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertyBoImpl implements PropertyBo {
	private Map<String, String> constPropHM = new LinkedHashMap<String, String>();

	@Autowired
	private PropertyDAO propertyDAO;

	public Map<String, String> getConstPropHM() {
		return constPropHM;
	}

	public PropertyDAO getPropertyDAO() {
		return propertyDAO;
	}

	private void loadConstPropertyListInHM() {
		List<ConstProperty> propList = propertyDAO.getConstPropertyList();
		if (CommonValidator.isListFirstNotEmpty(propList)) {
			for (ConstProperty constProp : propList) {
				if (constProp != null) {
					constPropHM.put(constProp.getEnumKey(),
							constProp.getConstValue());
				}
			}
		}
	}

	public String obtainProperty(String key) {
		if (key == null) {
			return null;
		} else if (constPropHM.containsKey(key) == false) {
			loadConstPropertyListInHM();
		}
		return constPropHM.get(key);
	}

	public void setConstPropHM(LinkedHashMap<String, String> constPropHM) {
		this.constPropHM = constPropHM;
	}

	public void setPropertyDAO(PropertyDAO propertyDAO) {
		this.propertyDAO = propertyDAO;
	}

}
