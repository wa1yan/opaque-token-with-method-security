package dev.waiyanhtet.samplecodetest.controller.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {

    private String name;
    private String phone;
    private String address;
}
