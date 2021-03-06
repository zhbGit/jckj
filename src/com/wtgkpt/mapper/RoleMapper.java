package com.wtgkpt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wtgkpt.entity.Role;

import tk.mybatis.mapper.common.Mapper;

public interface RoleMapper extends Mapper<Role>{

	void deleteRolePermissionByRoleId(int roleId);

	void saveRolePermission(@Param("list")List<Map<String, Integer>> params);

	List<Role> getRoleListByUserId(int userId);

	void deleteBatchByIds(@Param("ids")String[] idsStr);

	void saveRole(Role role);
}
