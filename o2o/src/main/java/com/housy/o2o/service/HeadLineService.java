package com.housy.o2o.service;

import java.io.IOException;
import java.util.List;

import com.housy.o2o.entity.HeadLine;

public interface HeadLineService {
	public static final String HLLISTKEY = "headlinelist";
	/**
	 * ���ݲ�ѯ������ȡshopCategory�б�
	 * @param headLineCondition
	 * @return
	 * @throws IOException
	 */
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
}
