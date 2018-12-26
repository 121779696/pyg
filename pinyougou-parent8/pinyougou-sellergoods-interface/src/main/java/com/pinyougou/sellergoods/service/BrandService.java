package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.PageResult;
import com.pinyougou.pojo.TbBrand;

import java.util.List;

public interface BrandService {

    public List<TbBrand> findAll();

    //返回分页列表
    public PageResult findPage(int pageNum, int pageSize);

    //增加品牌
    public void add(TbBrand brand);
    //根据ID获取实体
    public TbBrand findOne(Long brandId);
    // 修改
    public void update(TbBrand brand);
    // 批量删除
    public void delete(Long[] brandId);
    //条件查询加分页
    public PageResult findPage(TbBrand brand, int pageNum,int pageSize);

}
