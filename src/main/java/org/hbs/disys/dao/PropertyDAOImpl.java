package org.hbs.disys.dao;

import java.util.List;

import org.hbs.disys.util.CommonHibernateSessionFactorySupport;
import org.hbs.disys.util.CustomLogger;
import org.hbs.disys.util.IConstProperty;
import org.hbs.disys.util.model.ConstProperty;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class PropertyDAOImpl extends CommonHibernateSessionFactorySupport
		implements PropertyDAO, IConstProperty {
	private static final long serialVersionUID = 2841118708051715155L;

	private final CustomLogger logger = new CustomLogger(this.getClass());

	public PropertyDAOImpl() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<ConstProperty> getConstPropertyList() {
		Session session = getSessionFactory().openSession();
		try {
			return session.createQuery(
					FROM + ConstProperty.class.getCanonicalName() + WHERE_1_1
							+ " AND constActive = TRUE ORDER BY constId")
					.list();
		} catch (Exception excep) {
			logger.error(excep);
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}
}
