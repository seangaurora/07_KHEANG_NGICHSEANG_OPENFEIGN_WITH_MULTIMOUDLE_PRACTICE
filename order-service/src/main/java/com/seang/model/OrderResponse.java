package com.seang.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private Customer customerResponse;
    private List<Product> productResponses;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime orderDate;
}
