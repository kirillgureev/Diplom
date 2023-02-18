package ru.netology.data;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class CreditModel {
    String id;
    String bank_id;
    String created;
    String status;
}