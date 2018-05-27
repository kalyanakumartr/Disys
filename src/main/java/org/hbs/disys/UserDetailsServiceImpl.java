package org.hbs.disys;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hbs.disys.bo.UserBo;
import org.hbs.disys.model.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService, Serializable {
	@Autowired
	protected UserBo userBo;
	private static final long serialVersionUID = 7087428004256710898L;

	@Override
	public UserDetails loadUserByUsername(String userId)
			throws UsernameNotFoundException {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		UserParam userParam = new UserParam();
		userParam.userId = userId;
		try {
			userBo.authenticateUser(userParam);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new org.springframework.security.core.userdetails.User(userId,
				userParam.user.getUsUserPwd(), grantedAuthorities);

	}

}
