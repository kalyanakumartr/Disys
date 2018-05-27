package org.hbs.disys.dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.hbs.disys.model.IUserLog;
import org.hbs.disys.model.IUsers;
import org.hbs.disys.model.UserFile;
import org.hbs.disys.model.UserLog;
import org.hbs.disys.model.UserParam;
import org.hbs.disys.util.CommonHibernateSessionFactorySupport;
import org.hbs.disys.util.CommonValidator;
import org.hbs.disys.util.CustomLogger;
import org.hbs.disys.util.EnumInterface;
import org.hbs.disys.util.IParam.ENamed;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl extends CommonHibernateSessionFactorySupport implements UserDAO
{
	private static final long	serialVersionUID	= -6282872836045369567L;
	private final CustomLogger	logger				= new CustomLogger(this.getClass());

	@SuppressWarnings("unchecked")
	@Override
	public IUsers getUserForLogin(UserParam userParam, EnumInterface enumInterface)
	{

		Session session = getSessionFactory().openSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();

			sbSelectQry.append(" From Users Where 1=1 ");

			for (String condKey : userParam.searchCondtionMap.keySet())
			{
				sbSelectQry.append(userParam.searchCondtionMap.get(condKey));
			}
			sbSelectQry.append(userParam._OrderBy);

			Query<IUsers> query = session.createQuery((sbSelectQry.toString()));

			_SetNamedParameterValueFromSearchValueMap(userParam, query);

			userParam.dataList = query.list();
			return ((IUsers) userParam.dataList.iterator().next());
		}
		catch (Exception excep)
		{
			logger.error(excep);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserParam getUsersList(UserParam userParam)
	{

		Session session = getSessionFactory().openSession();
		try
		{
			StringBuffer sbSelectQry1 = new StringBuffer();

			sbSelectQry1.append(" From Users Where 1=1 ");

			for (String condKey : userParam.searchCondtionMap.keySet())
			{
				sbSelectQry1.append(userParam.searchCondtionMap.get(condKey));
			}
			sbSelectQry1.append(userParam._OrderBy);

			Query<IUsers> query = session.createQuery((sbSelectQry1.toString()));

			_SetNamedParameterValueFromSearchValueMap(userParam, query);

			userParam.dataList = query.list();
		}
		catch (Exception excep)
		{
			logger.error(excep);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return userParam;
	}

	@Override
	public boolean userLogAtLogin(IUsers user, String ipAddr)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSessionFactory().openSession();
			_Txn = session.beginTransaction();

			IUserLog ulog = new UserLog();

			ulog.setUsers(user);
			ulog.setUlUserLoginTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			ulog.setUlIpaddress(ipAddr);
			session.saveOrUpdate("UserLog", ulog);
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			logger.error(excep);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					logger.error(hibExcep);
				}
			}
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean userLogAtLogOut(UserParam userParam)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			userParam.dataList.clear();
			ENamed.EqualTo.param_AND(userParam, "usEmployeeId", userParam.user.getUsEmployeeId());

			session = getSessionFactory().openSession();
			StringBuffer sbSelectQry = new StringBuffer();
			sbSelectQry.append(FROM + UserLog.class.getCanonicalName() + WHERE_1_1);

			for (String condKey : userParam.searchCondtionMap.keySet())
			{
				sbSelectQry.append(userParam.searchCondtionMap.get(condKey));
			}
			sbSelectQry.append(ENamed.OrderBy.param("ulUserLoginTime") + " Desc");
			userParam.dataList.clear();
			sbSelectQry.append(userParam._OrderBy);

			Query<?> query = session.createQuery((sbSelectQry.toString()));

			_SetNamedParameterValueFromSearchValueMap(userParam, query);

			userParam.dataList = query.list();

			List<IUserLog> userLogList = (List<IUserLog>) userParam.dataList;

			// userParam.dataList = query.list();
			// ((UserFile) userParam.dataList.iterator().next());

			// sbSelectQry.append("from org.hbs.disys.model.UserLog where 1=1");

			// sbSelectQry.append(ENamed.EqualTo.param_AND("userParam.user.usEmployeeId"));
			// sbSelectQry.append(ENamed.OrderBy.param("ulUserLoginTime") + " Desc");

			// Query<?> selQry = session.createQuery(sbSelectQry.toString());
			// selQry.setParameter(ENamed.create("users.usEmployeeId"),
			// userParam.user.getUsEmployeeId());
			// List<IUserLog> userLogList = (List<IUserLog>) selQry.list();

			if (CommonValidator.isListFirstNotEmpty(userLogList))
			{
				_Txn = session.beginTransaction();

				StringBuffer sbUpdateQry = new StringBuffer("Update  org.hbs.disys.model.UserLog ");
				sbUpdateQry.append(" Set ulFetchBlock = false Where ulFetchBlock = true ");
				sbUpdateQry.append(ENamed.EqualTo.param_AND("users.usEmployeeId"));

				Query<?> updateQry = session.createQuery(sbUpdateQry.toString());
				updateQry.setParameter(ENamed.create("users.usEmployeeId"), userParam.user.getUsEmployeeId());
				updateQry.executeUpdate();

				IUserLog userLog = userLogList.iterator().next();
				userLog.setUlUserLogoutTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				userLog.setUlFetchBlock(true);
				session.saveOrUpdate("UserLog", userLog);
				_Txn.commit();
			}
			return true;
		}
		catch (Exception excep)
		{
			logger.error(excep);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					logger.error(hibExcep);
				}
			}
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return false;

	}

	@SuppressWarnings("unchecked")
	public IUsers getUserName(UserParam userParam, EnumInterface enumInterface)
	{
		Session session = getSessionFactory().openSession();
		try
		{
			StringBuffer sbSelectQry = new StringBuffer();

			sbSelectQry.append(" From Users Where 1=1 ");

			for (String condKey : userParam.searchCondtionMap.keySet())
			{
				sbSelectQry.append(userParam.searchCondtionMap.get(condKey));
			}
			sbSelectQry.append(userParam._OrderBy);

			Query<IUsers> query = session.createQuery((sbSelectQry.toString()));

			_SetNamedParameterValueFromSearchValueMap(userParam, query);

			userParam.dataList = query.list();
			return ((IUsers) userParam.dataList.iterator().next());
		}
		catch (Exception excep)
		{
			logger.error(excep);
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return null;
	}

	@Override
	public boolean saveUserFile(UserFile userFile)
	{
		Transaction _Txn = null;
		Session session = null;
		try
		{
			session = getSessionFactory().openSession();
			_Txn = session.beginTransaction();

			session.saveOrUpdate("UserFile", userFile);
			_Txn.commit();
			return true;
		}
		catch (Exception excep)
		{
			logger.error(excep);
			if (_Txn != null && _Txn.isActive())
			{
				try
				{
					_Txn.rollback();
				}
				catch (HibernateException hibExcep)
				{
					logger.error(hibExcep);
				}
			}
		}
		finally
		{
			if (session != null)
			{
				session.clear();
				session.close();
			}
		}
		return false;
	}
}
