package com.wtgkpt.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wtgkpt.entity.Role;

public interface RoleService extends BaseService<Role>{

	PageInfo<Role> getListByPage(int currentNum, int pageSize, String name);

	void saveRolePermission(int roleId, String permissionIds);

	List<Role> getRoleListByUserId(int userId);

	void deleteBatchByIds(String[] idsStr);
	
	void saveRole(Role role);
}
