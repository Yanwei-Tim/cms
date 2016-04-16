package com.hzjava.monitorcenter.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.collin.commons.domain.PageResult;
import cn.collin.commons.utils.DateUtils;

import com.hzjava.monitorcenter.dao.AccountDao;
import com.hzjava.monitorcenter.dao.PermissionDao;
import com.hzjava.monitorcenter.dao.RoleDao;
import com.hzjava.monitorcenter.dao.UserOperLogDao;
import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.Permission;
import com.hzjava.monitorcenter.domain.Role;
import com.hzjava.monitorcenter.domain.UserOperLog;

public class UserServiceImpl implements UserService {
	private AccountDao accountDao;
	private RoleDao roleDao;
	private PermissionDao permissionDao;
	private UserOperLogDao userOperLogDao;

	public void setUserOperLogDao(UserOperLogDao userOperLogDao) {
		this.userOperLogDao = userOperLogDao;
	}

	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

	public Map getUserIndexModel(String userName, String status, int pageIndex) {
		Map model = new HashMap();

		PageResult ps = accountDao.listByPage(userName, status, pageIndex);
		model.put("ps", ps);

		return model;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public Map getRoleIndexModel() {
		Map model = new HashMap();

		List list = roleDao.findAll();
		model.put("roles", list);

		return model;
	}

	public Map getUserDetailModel(Long id) {
		Map model = new HashMap();
		Account account = (Account) accountDao.getById(id);
		model.put("account", account);

		return model;
	}

	public void newUser(Account account, long[] rIds) {
		Set roles = new HashSet();
		for (int i = 0; i < rIds.length; i++) {
			Role role = (Role) roleDao.getById(rIds[i]);
			roles.add(role);
		}
		account.setRoles(roles);
		accountDao.create(account);
	}

	public void updateUser(Account account, long[] rIds) {
		Account old = (Account) accountDao.retrieveById(account.getId());
		old.setDepart(account.getDepart());
		old.setDescription(account.getDescription());
		old.setEmail(account.getEmail());
		old.setEndHour(account.getEndHour());
		old.setEndIp(account.getEndIp());
		old.setModifiedTime(account.getModifiedTime());
		old.setName(account.getName());
		old.setPassword(account.getPassword());
		old.setPhone(account.getPhone());
		old.setSex(account.getSex());
		old.setStartHour(account.getStartHour());
		old.setStartIp(account.getStartIp());
		old.setStatus(account.getStatus());
		old.setTitle(account.getTitle());
		old.setUserName(account.getUserName());

		Set<Role> roles = new HashSet<Role>();
		for (int i = 0; i < rIds.length; i++) {
			Role role = (Role) roleDao.getById(rIds[i]);
			roles.add(role);
		}
		old.setRoles(roles);

		accountDao.update(old);
	}

	public void deleteUser(Long id, Account account) {
		Account item = (Account) accountDao.getById(id);
		UserOperLog entry = new UserOperLog();
		entry.setAuditInfo("用户名 " + item.getUserName() + " 删除成功");
		entry.setAuditModule("用户管理");
		entry.setLevel("INFO");
		entry.setUserName(account.getUserName());
		entry.setLogTime(DateUtils.getNow());
		userOperLogDao.create(entry);

		accountDao.delete(id);

	}

	public void deleteRole(Long roleId, Account account) {
		Role item = (Role) roleDao.getById(roleId);
		UserOperLog entry = new UserOperLog();
		entry.setAuditInfo("角色名 " + item.getName() + " 删除成功");
		entry.setAuditModule("角色管理");
		entry.setLevel("INFO");
		entry.setUserName(account.getUserName());
		entry.setLogTime(DateUtils.getNow());
		userOperLogDao.create(entry);

		roleDao.delete(roleId);

	}

	public Map getRoleDetailModel(Long roleId) {
		Map model = new HashMap();
		Role role = (Role) roleDao.getById(roleId);
		model.put("role", role);
		return model;
	}

	public void newRole(Role role, long[] permissionIds) {
		Set permissions = new HashSet();
		for (int i = 0; i < permissionIds.length; i++) {
			Permission permission = (Permission) permissionDao
					.retrieveById(permissionIds[i]);
			permissions.add(permission);
		}

		role.setCreatedTime(new Timestamp(System.currentTimeMillis()));

		role.setPermissions(permissions);
		Date now = DateUtils.getNow();
		role.setCreatedTime(now);
		role.setModifiedTime(now);
		roleDao.create(role);
	}

	public void updateRole(Role role, long[] permissionIds) {
		Role older = (Role) roleDao.getById(role.getId());
		older.setDescription(role.getDescription());
		older.setName(role.getName());
		Set permissions = new HashSet();
		for (int i = 0; i < permissionIds.length; i++) {
			Permission permission = (Permission) permissionDao
					.retrieveById(permissionIds[i]);
			permissions.add(permission);
		}
		older.setPermissions(permissions);
		older.setModifiedTime(DateUtils.getNow());

		roleDao.update(older);
	}

	public Map getAddUserModel() {
		Map model = new HashMap();
		List roles = roleDao.findAll();
		model.put("roles", roles);

		return model;
	}

	public Map getAddRoleModel() {
		Map model = new HashMap();
		List permissions = permissionDao.findAll();
		model.put("permissions", permissions);

		return model;
	}

	public Map getUpdateUserModel(Long id) {
		Map model = new HashMap();
		List roles = roleDao.findAll();
		model.put("roles", roles);

		Account account = (Account) accountDao.getById(id);
		model.put("account", account);

		return model;
	}

	public Map getUpdateRoleModel(Long id) {
		Map model = new HashMap();

		List permissions = permissionDao.findAll();
		model.put("permissions", permissions);

		Role role = (Role) roleDao.getById(id);
		model.put("role", role);

		return model;
	}

	public void updatePwd(Long id, String newpwd) {
		Account account = (Account) accountDao.retrieveById(id);
		account.setPassword(newpwd);
		accountDao.update(account);
	}

	public List listAllUsers() {
		List list = accountDao.findAll();
		Account acc = new Account();
		acc.setUserName("");
		acc.setName("All");
		list.add(0, acc);
		return list;
	}

}
