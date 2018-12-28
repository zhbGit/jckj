package com.wtgkpt.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.wtgkpt.annotation.RequirePermission;
import com.wtgkpt.entity.User;
import com.wtgkpt.service.UserService;
import com.wtgkpt.utils.AwayTools;
import com.wtgkpt.utils.UploadUtil;
import com.wtgkpt.vo.Result;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequirePermission("user:listUI")
	@RequestMapping("listUI")
	public String listUI(HttpServletRequest request) {
		return "user/listUI";
	}
	
	@RequirePermission("user:listUI")
	@RequestMapping("list")
	@ResponseBody
	public Result list(int offset, int limit,String search) {
		
		PageInfo<User> pageInfo = this.userService.getListByPage(offset / limit + 1, limit, search);
		return Result.succeed(pageInfo);
	}
	
	
//===================================保存/修改/删除方法=======================================	
	
	@RequestMapping("saveUI")
	public String saveUI(Integer id,HttpServletRequest request) {
		if (id != null) {
			User user = this.userService.getById(id);
			if (user != null) {
				request.setAttribute("user", user);
			}
		}
		return "user/saveUI";
	}
	
	
	@RequirePermission("user:add;user:update")
	@RequestMapping("save")
	public String add(User user, @RequestParam MultipartFile upload_logo) {
		try {
			String dir = UploadUtil.getFolder();
			if (upload_logo.isEmpty()) {
				AwayTools.writeLog(AwayTools.SYSOUTFILE, "文件未上传");
			} else {
				AwayTools.writeLog(AwayTools.SYSOUTFILE, "文件长度: " + upload_logo.getSize());
				AwayTools.writeLog(AwayTools.SYSOUTFILE, "文件类型: " + upload_logo.getContentType());
				AwayTools.writeLog(AwayTools.SYSOUTFILE, "文件名称: " + upload_logo.getName());
				AwayTools.writeLog(AwayTools.SYSOUTFILE, "文件原名: " + upload_logo.getOriginalFilename());
				AwayTools.writeLog(AwayTools.SYSOUTFILE, "========================================");
				
				// 上传文件 返回路径
				String path = UploadUtil.writeFile(upload_logo.getOriginalFilename(), dir, upload_logo.getInputStream());
				AwayTools.writeLog(AwayTools.SYSOUTFILE, "文件路径:" + path);
				user.setPhoto(path);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		if (user.getId() != null) {
			this.userService.update(user);
		} else {
			user.setCreateTime(new Date());
			user.setUpdateTime(user.getCreateTime());
			user.setPassword(DigestUtils.md5Hex("123456"));
			this.userService.saveUser(user);
		}
		return "redirect:/user/listUI";
	}
	
	@RequirePermission("user:delete")
	@RequestMapping("delete/{ids}")
	@ResponseBody
	public Result delete(@PathVariable("ids") String ids) {
		
		String[] idsStr = ids.split(",");
		if (idsStr.length == 1) {
			this.userService.deleteById(Integer.parseInt(idsStr[0]));
		} else {
			this.userService.deleteBatchByIds(idsStr);
		}
		return Result.succeed();
	}
	
	@RequirePermission("user:setRole")
	@RequestMapping("setRole")
	@ResponseBody
	public Result setRole(int userId,String roleIds) {
		
		this.userService.saveUserRole(userId,roleIds);
		
		return Result.succeed();
	}
	
	/**
	 * 导入Excel
	 * excel格式为：用户名、密码、邮箱、手机号码、状态（1-启用，0-禁用）
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/importExport")
	public String impotr(HttpServletRequest request, @RequestParam MultipartFile upload_logo) throws Exception {
	     int adminId = 1;
	     //获取上传的文件
	     String month = request.getParameter("month");
	     InputStream in = upload_logo.getInputStream();
	     //数据导入
	     userService.importExcelInfo(in,upload_logo,month,adminId);
	     in.close();
	     return "redirect:/user/list?order=asc&offset=0&limit=10";
	}
	
	@RequestMapping(value = "/showfile")
	@ResponseBody
	public void showImage(Integer id, HttpServletRequest request, HttpServletResponse response) {
		if (id != null) {
			User user = this.userService.getById(id);
			if (user != null) {
				request.setAttribute("user", user);
			}
			String filePath = AwayTools.getUserUploadPath();
			AwayTools.getShowImage(filePath, user.getPhoto(), response);
		}
	}
}
