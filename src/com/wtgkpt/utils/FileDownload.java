package com.wtgkpt.utils;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class FileDownload {

	/**
	 * @param response 
	 * @param filePath		//文件完整路径(包括文件名和扩展名)
	 * @param fileName		//下载后看到的文件名
	 * @return  文件名
	 */
	public static void fileDownload(final HttpServletResponse response, String filePath, String fileName) throws Exception{
		try {
			byte[] data = FileUtil.toByteArray2(filePath);
			String agent =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("User-agent");
			// 如果浏览器是IE浏览器，就得进行编码转换
			try {
				if (agent.contains("MSIE")) {
					fileName = URLEncoder.encode(fileName, "UTF-8");
				} else {
					fileName = new String(fileName.getBytes(), "ISO-8859-1");
				}
			} catch (UnsupportedEncodingException e) {//如果出错默认非ie 浏览器
				fileName= new String(fileName.getBytes(), "ISO-8859-1");
			}
			response.reset();  
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
			response.addHeader("Content-Length", "" + data.length);  
			response.setContentType("application/octet-stream;charset=UTF-8");  
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());  
			outputStream.write(data);
			outputStream.flush();  
			outputStream.close();
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
}
