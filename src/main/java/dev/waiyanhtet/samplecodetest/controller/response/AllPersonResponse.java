package dev.waiyanhtet.samplecodetest.controller.response;

import dev.waiyanhtet.samplecodetest.domain.dto.PersonDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllPersonResponse {
    List<PersonDto> personResponseList;
}
