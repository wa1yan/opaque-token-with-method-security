package dev.waiyanhtet.samplecodetest.service;

import dev.waiyanhtet.samplecodetest.controller.request.PersonRequest;
import dev.waiyanhtet.samplecodetest.domain.dto.PersonDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public interface PersonService {

    PersonDto createPerson(PersonRequest personRequest);
    PersonDto getOnePerson(Long id);
    List<PersonDto> getAllPerson();

    default String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return null;
    }
}
