package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.container.page.PageHandler;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.entity.PageResult;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @description:
 * @createTime: 2018-12-26 13:07
 */

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper brandMapper;

    @Override
    public List<TbBrand> findAll() {
        return brandMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(null);

        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(TbBrand brand) {
        brandMapper.insert(brand);
    }

    //根据ID获取实体
    @Override
    public TbBrand findOne(Long brandId) {
        return brandMapper.selectByPrimaryKey(brandId);
    }

    //修改
    @Override
    public void update(TbBrand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }
    //批量删除
    @Override
    public void delete(Long[] brandId) {
        for (Long id : brandId) {
            brandMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        TbBrandExample example  = new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();
        if (brand.getName()!=null &&brand.getName().length()>0){
            criteria.andNameLike("%" + brand.getName()+"%");
        }
        if (brand.getFirstChar() != null && brand.getFirstChar().length() > 0) {
            criteria.andFirstCharEqualTo(brand.getFirstChar());
        }
        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());

    }

}
