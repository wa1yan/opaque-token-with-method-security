package dev.waiyanhtet.samplecodetest.service.impl;

import dev.waiyanhtet.samplecodetest.controller.request.PersonRequest;
import dev.waiyanhtet.samplecodetest.domain.dto.PersonDto;
import dev.waiyanhtet.samplecodetest.exception.BusinessLogicException;
import dev.waiyanhtet.samplecodetest.mapper.PersonMapper;
import dev.waiyanhtet.samplecodetest.repository.PersonRepository;
import dev.waiyanhtet.samplecodetest.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    @Override
    public PersonDto createPerson(PersonRequest request) {
        var person = personMapper.asPerson(request);
        person.setCreated_on(LocalDateTime.now());
        person.setCreated_by(getLoggedInUsername());
        var personEntity = personRepository.save(person);
       return personMapper.asPersonDto(personEntity);
    }

    @Override
    public PersonDto getOnePerson(Long id) {
        var personEntity = personRepository.findById(id)
                .orElseThrow(() -> new BusinessLogicException(HttpStatus.NOT_FOUND.value(), "Person not found"));
        return personMapper.asPersonDto(personEntity);
    }

    @Override
    public List<PersonDto> getAllPerson() {
        var personEntityList = personRepository.findAll();
        return personMapper.asPersonDtoList(personEntityList);
    }
}
