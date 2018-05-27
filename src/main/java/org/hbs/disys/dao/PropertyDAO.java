package org.hbs.disys.dao;

import java.io.Serializable;
import java.util.List;

import org.hbs.disys.util.model.ConstProperty;

public interface PropertyDAO extends Serializable {
	public List<ConstProperty> getConstPropertyList();
}
