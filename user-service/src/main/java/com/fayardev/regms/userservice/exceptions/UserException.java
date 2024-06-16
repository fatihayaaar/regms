package com.fayardev.regms.userservice.exceptions;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class UserException extends RuntimeException {

    private final String message;
}
