package com.naucratis.naucratis.model.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.ModelAttribute;

@Data
public class LibraryRequestDto {
    String name;
    String address;
    String contactPhone;
}
