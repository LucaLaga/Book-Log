package com.example.book_log.dto.response;

import com.example.book_log.model.Log;
import com.example.book_log.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private List<String> bookOwnedName;
    private List<LogResponse> logResponses;
}
