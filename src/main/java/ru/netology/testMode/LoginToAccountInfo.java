package ru.netology.testMode;

import lombok.*;

@Data
@AllArgsConstructor

public class LoginToAccountInfo {
        @Getter private String login;
        @Getter private String password;
        @Getter private String status;

}
