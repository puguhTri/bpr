package com.mib.configuration.bprconfigurationsvc.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommonMapper {


    private final ModelMapper modelMapper = new ModelMapper();

    /*
     * Create new modelMapper
     * */
    public ModelMapper getMapper() {
        return modelMapper;
    }


    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
    }

    public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> getMapper().map(objectEntity, dtoClass));
    }


}
