package com.wtgkpt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.wtgkpt.annotation.RequirePermission;
import com.wtgkpt.entity.FileInfo;
import com.wtgkpt.entity.User;
import com.wtgkpt.service.FileInfoService;
import com.wtgkpt.utils.AwayTools;
import com.wtgkpt.utils.FileDownload;
import com.wtgkpt.utils.FileUtil;
import com.wtgkpt.utils.UploadUtil;
import com.wtgkpt.vo.Result;

@Controller
@RequestMapping("fileinfo")
public class FileInfoController {
	
	@Autowired
	private FileInfoService fileinfoService;

	@RequirePermission("fileinfo:index")
	@RequestMapping("index")
	public String listUI(HttpServletRequest request) {
		return "fileinfo/listUI";
	}

	@RequestMapping("photo")
	public String photo(HttpServletRequest request) {
		return "fileinfo/photo";
	}
	
	@RequirePermission("fileinfo:index")
	@RequestMapping("list")
	@ResponseBody
	public Result list(int offset, int limit, String search, HttpSession session, HttpServletResponse response) {
		Object obj = session.getAttribute("loginUser");
		if (obj == null) {
			try {
				response.sendRedirect("redirect:/index.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		User user = (User) obj;
		PageInfo<FileInfo> pageInfo = this.fileinfoService.getListByPage(offset / limit + 1, limit, user.getId());
		return Result.succeed(pageInfo);
	}

	/**
	 * 文件/图片上传（支持多上传）
	 * @param upload_logo
	 * @param session
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "upload_img", method = RequestMethod.POST)
	public Map<String, Object> uploadImage(@RequestParam MultipartFile[] upload_logo, HttpSession session, HttpServletResponse response) throws IOException {
		AwayTools.writeLog(AwayTools.SYSOUTFILE, "上传图片");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			Object obj = session.getAttribute("loginUser");
			if (obj == null) {
				dataMap.put("url", "index.jsp");
				dataMap.put("code", "500");
				return dataMap;
			}
			
			User user = (User) obj;
			List<FileInfo> fileinfoList = new ArrayList<FileInfo>();
			String dir = UploadUtil.getFolder();
			if (upload_logo == null || upload_logo.length <= 0) {
				AwayTools.writeLog(AwayTools.SYSOUTFILE, "文件为空，请选择要上传的文件！");
				dataMap.put("code", "500");
				return dataMap;
			}
			for (MultipartFile myfile : upload_logo) {
				if (myfile.isEmpty()) {
					AwayTools.writeLog(AwayTools.SYSOUTFILE, "文件未上传");
				} else {
					AwayTools.writeLog(AwayTools.SYSOUTFILE, "文件长度: " + myfile.getSize());
					AwayTools.writeLog(AwayTools.SYSOUTFILE, "文件类型: " + myfile.getContentType());
					AwayTools.writeLog(AwayTools.SYSOUTFILE, "文件名称: " + myfile.getName());
					AwayTools.writeLog(AwayTools.SYSOUTFILE, "文件原名: " + myfile.getOriginalFilename());
					AwayTools.writeLog(AwayTools.SYSOUTFILE, "========================================");
					
					// 上传文件 返回路径
					String path = UploadUtil.writeFile(myfile.getOriginalFilename(), dir, myfile.getInputStream());
					AwayTools.writeLog(AwayTools.SYSOUTFILE, "文件路径:" + path);
					
					FileInfo fileinfo = new FileInfo();
					fileinfo.setFilePath(path);
					if (FileUtil.checkValidFileEndName(myfile, AwayTools.IMAGE_TYPE)) {
						fileinfo.setFileType(0);	//图片上传
					} else {
						fileinfo.setFileType(1);	//文件上传
					}
					fileinfo.setFileName(myfile.getOriginalFilename());
					fileinfo.setCreateTime(new Date());
					fileinfo.setUserId(user.getId());
					fileinfoList.add(fileinfo);
				}
			}
			
			if (fileinfoList != null && fileinfoList.size() > 0) {
				this.fileinfoService.saveFileInfo(fileinfoList);
				dataMap.put("code", "200");
				dataMap.put("url", "index");
				return dataMap;
			} else {
				dataMap.put("code", "500");
				return dataMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			dataMap.put("code", "500");
			return dataMap;
		}
	}
	
	/**
	 * 下载文件
	 */
	@RequestMapping("/downloadFile")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, HttpSession session, Integer id) throws Exception {
		Object obj = session.getAttribute("loginUser");
		if (obj == null) {
			response.sendRedirect("index.jsp");
		}
		
		User user = (User) obj;
		if (id != null) {
			FileInfo fileinfo = this.fileinfoService.getById(id);
			if (fileinfo != null) {
				if (fileinfo.getUserId().intValue() != user.getId().intValue()) {
					throw new Exception("没有权限");
				}
				String filePath = AwayTools.getUserUploadPath() + fileinfo.getFilePath();
				String file = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
				FileDownload.fileDownload(response, filePath, file);
			}
		}
	}
}
