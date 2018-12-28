package com.wtgkpt.service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.wtgkpt.entity.User;

public interface UserService extends BaseService<User>{

	User findUserByUserName(String userName);

	/**
	 * 批量删除
	 * @param idsStr
	 */
	void deleteBatchByIds(String[] idsStr);

	PageInfo<User> getListByPage(int currentNum, int pageSize, String name);

	/**
	 * 绑定用户和角色
	 * @param userId
	 * @param roleIds
	 */
	void saveUserRole(int userId, String roleIds);
	
	void saveUser(User user);

	void importExcelInfo(InputStream in, MultipartFile file, String salaryDate,Integer adminId) throws Exception;
}
