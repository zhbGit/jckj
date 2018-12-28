package com.wtgkpt.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wtgkpt.entity.User;
import com.wtgkpt.mapper.UserMapper;
import com.wtgkpt.service.UserService;
import com.wtgkpt.utils.AwayTools;
import com.wtgkpt.utils.ExcelUtil;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	protected Mapper<User> getMapper() {
		return this.userMapper;
	}

	@Override
	public User findUserByUserName(String userName) {
		return this.userMapper.getUserByUserName(userName);
	}

	@Override
	public void deleteBatchByIds(String[] idsStr) {
		this.userMapper.deleteBatchByIds(idsStr);
	}

	@Override
	public PageInfo<User> getListByPage(int currentNum, int pageSize,String name) {
		
		Example cond = new Example(User.class);
        if (!StringUtils.isEmpty(name)) {
        	// 模糊查询
            cond.createCriteria().andLike("userName", "%"+name+"%");
        }
		
        PageHelper.startPage(currentNum, pageSize);
        cond.setOrderByClause(" create_time desc");
        List<User> list = this.userMapper.selectByExample(cond);
        
		return new PageInfo<User>(list);
	}

	@Override
	public void saveUserRole(int userId, String roleIdsStr) {
		// 解绑
		this.userMapper.deleteUserRoleByUserId(userId);
		
		if (StringUtils.isNotEmpty(roleIdsStr)) {
			// 绑定
			String[] roleIds = roleIdsStr.split(",");
			List<Map<String,Integer>> params = new ArrayList<>(roleIds.length);
	        Map<String,Integer> param = null;
	        for (String roleId : roleIds) {
	            param = new HashMap<>(2);
	            param.put("userId",userId);
	            param.put("roleId",Integer.parseInt(roleId));
	            params.add(param);
	        }
	        
	        this.userMapper.saveUserRole(params);
		}
	}
	
	@Override
	public void saveUser(User user) {
		if (user != null) {
			try {
				this.userMapper.saveUser(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 导入excel数据
	 * excel格式为：用户名、密码、邮箱、手机号码、状态（1-启用，0-禁用）
	 */
	@Override
	public void importExcelInfo(InputStream in, MultipartFile file, String salaryDate,Integer adminId) throws Exception{
	    List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
	    List<User> userList = new ArrayList<User>();
	    //遍历listob数据，把数据放到List中
	    for (int i = 0; i < listob.size(); i++) {
	        List<Object> ob = listob.get(i);
	        User user = new User();
	        //通过遍历实现把每一列封装成一个model中，再把所有的model用List集合装载
	        //S_T_USER.nextval, #{userName}, #{password}, #{email}, #{phone}, #{salt}, #{status}, #{createTime}, #{updateTime}
	        String strUserName = AwayTools.checkNullString((String) ob.get(0)).trim();
	        String strPassword = AwayTools.checkNullString((String) ob.get(1)).trim();
	        String strEmail = AwayTools.checkNullString((String) ob.get(2)).trim();
	        String strPhone = AwayTools.checkNullString((String) ob.get(3)).trim();
	        String strStatus = AwayTools.checkNullString((String) ob.get(4)).trim();
	        
	        AwayTools.writeLog(AwayTools.SYSOUTFILE, "用户名: " + strUserName + "，密码：" + strPassword + "，邮箱：" + strEmail + "，手机号码：" + strPhone);
	        
	        if (StringUtils.isEmpty(strUserName)) {
	        	continue;
	        }
	        int status = 0;
	        if (StringUtils.isNotEmpty(strStatus)) {
	        	try {
		        	status = Integer.valueOf(strStatus);
				} catch (NumberFormatException e) {
					status = 0;
				}
			}
	        user.setUserName(strUserName);
	        if (StringUtils.isNotEmpty(strPassword)) {
	        	user.setPassword(DigestUtils.md5Hex(strPassword));
			} else {
				user.setPassword(DigestUtils.md5Hex("123456"));
			}
			user.setEmail(strEmail);
			user.setPhone(strPhone);
			user.setStatus(status);
			user.setCreateTime(new Date());
			user.setUpdateTime(user.getCreateTime());
	        userList.add(user);
	    }
	    //批量插入
	    this.userMapper.insertInfoBatch(userList);
	}
}
