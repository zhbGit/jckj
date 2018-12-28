package com.wtgkpt.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wtgkpt.entity.FileInfo;

public interface FileInfoService extends BaseService<FileInfo> {
	
	void saveFileInfo(List<FileInfo> fileinfos);
	PageInfo<FileInfo> getListByPage(int currentNum, int pageSize, int userId);
}
