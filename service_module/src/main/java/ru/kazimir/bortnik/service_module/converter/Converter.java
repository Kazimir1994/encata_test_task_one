package ru.kazimir.bortnik.service_module.converter;

public interface Converter<ObjectDTO, Object> {
    ObjectDTO toDTO(Object object);

    Object fromDTO(ObjectDTO objectDTO);
}