package manager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GalleryResponseDTO {
    //String tittle, String description, MultipartFile file, Integer categoryId
    private Long id;
    private String tittle;
    private String description;
    private Integer categoryId;
    private String categoryName;
    private String imageUrl;
}
