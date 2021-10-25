package com.example.keycloack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Messages {

    private List<String> userTelegramId;

    private String messageText;
}
