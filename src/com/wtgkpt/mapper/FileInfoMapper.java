package com.wtgkpt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wtgkpt.entity.FileInfo;

import tk.mybatis.mapper.common.Mapper;

public interface FileInfoMapper extends Mapper<FileInfo>{

	void saveFileInfo(@Param("list") List<FileInfo> fileinfos);
	
}
