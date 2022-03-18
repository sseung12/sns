package code.sns.domain.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class PostRequestDto {


    @NotBlank
    private String context;

    private Long user_id;

    private MultipartFile file;



}