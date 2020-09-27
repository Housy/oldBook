package com.housy.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.housy.o2o.entity.HeadLine;

public interface HeadLineDao {
	/**
	 * ���ݴ���Ĳ�ѯ������ͷ������ѯͷ����
	 * @param headLineCondition
	 * @return
	 */
	List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);
}
