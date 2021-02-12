package ru.netology.testMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor

public class LoginToAccountInfo {
        private String login;
        private String password;
        private String status;

        public String getName() {
                return login;
        }

        public void setName(String name) {
                this.login = name;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }


        public LoginToAccountInfo(String name, String password, String status) {
                this.login = name;
                this.password = password;
                this.status = status;
        }
}
