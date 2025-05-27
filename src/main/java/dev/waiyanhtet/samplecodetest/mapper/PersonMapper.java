package dev.waiyanhtet.samplecodetest.mapper;


import dev.waiyanhtet.samplecodetest.controller.request.PersonRequest;
import dev.waiyanhtet.samplecodetest.domain.dto.PersonDto;
import dev.waiyanhtet.samplecodetest.domain.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonMapper {

    Person asPerson(PersonRequest personRequest);

    PersonDto asPersonDto(Person person);
    List<PersonDto> asPersonDtoList(List<Person> personList);

}