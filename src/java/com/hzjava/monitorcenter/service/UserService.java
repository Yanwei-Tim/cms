package com.hzjava.monitorcenter.service;

import java.util.List;
import java.util.Map;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.Role;

public interface UserService {

	Map getUserIndexModel(String userName, String status, int pageIndex);

	Map getRoleIndexModel();

	void newUser(Account account, long[] ids);

	void updateUser(Account account, long[] rIds);

	Map getUserDetailModel(Long id);

	void deleteUser(Long id, Account account);

	void newRole(Role role, long[] permissionIds);

	void updateRole(Role role, long[] permissionIds);

	Map getRoleDetailModel(Long roleId);

	void deleteRole(Long roleId, Account account);

	Map getAddUserModel();

	Map getAddRoleModel();

	Map getUpdateUserModel(Long id);

	Map getUpdateRoleModel(Long id);

	void updatePwd(Long id, String newpwd);

	List listAllUsers();

}
