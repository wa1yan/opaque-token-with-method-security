package dev.waiyanhtet.samplecodetest.controller;

import dev.waiyanhtet.samplecodetest.controller.request.PersonRequest;
import dev.waiyanhtet.samplecodetest.controller.response.AllPersonResponse;
import dev.waiyanhtet.samplecodetest.domain.dto.PersonDto;
import dev.waiyanhtet.samplecodetest.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/person")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    @PreAuthorize("hasAuthority('USER_WRITE')")
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonRequest request) {
        var personDto = personService.createPerson(request);
        return ResponseEntity.ok(personDto);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('NO_READ_TEST')")
    public ResponseEntity<PersonDto> getPerson(@PathVariable Long id) {
        var personDto = personService.getOnePerson(id);
        return ResponseEntity.ok(personDto);
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('USER_READ')")
    public ResponseEntity<AllPersonResponse> getAllPerson() {
        var personDtoList = personService.getAllPerson();
        return ResponseEntity.ok(AllPersonResponse.builder()
                        .personResponseList(personDtoList)
                .build());
    }
}
