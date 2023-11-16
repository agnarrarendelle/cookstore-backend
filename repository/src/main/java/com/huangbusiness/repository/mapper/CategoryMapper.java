package com.huangbusiness.repository.mapper;

import com.huangbusiness.common.vo.CategoryVo;
import com.huangbusiness.repository.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryVo toVo(Category entity);

    List<CategoryVo> toVo(List<Category> entities);

}