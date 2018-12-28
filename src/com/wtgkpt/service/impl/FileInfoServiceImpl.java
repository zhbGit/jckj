package com.wtgkpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wtgkpt.entity.FileInfo;
import com.wtgkpt.entity.User;
import com.wtgkpt.mapper.FileInfoMapper;
import com.wtgkpt.service.FileInfoService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

@Service
public class FileInfoServiceImpl extends BaseServiceImpl<FileInfo> implements FileInfoService {

	@Autowired
	private FileInfoMapper fileinfoMapper;
	
	@Override
	protected Mapper<FileInfo> getMapper() {
		return this.fileinfoMapper;
	}
	
	@Override
	public void saveFileInfo(List<FileInfo> fileinfos) {
		if (fileinfos != null) {
	        this.fileinfoMapper.saveFileInfo(fileinfos);
		}
	}
	
	@Override
	public PageInfo<FileInfo> getListByPage(int currentNum, int pageSize, int userId) {
		Example cond = new Example(FileInfo.class);
        cond.createCriteria().andEqualTo("userId", userId);
        PageHelper.startPage(currentNum, pageSize);
        cond.setOrderByClause(" create_time desc");
        List<FileInfo> list = this.fileinfoMapper.selectByExample(cond);
		return new PageInfo<FileInfo>(list);
	}
}
