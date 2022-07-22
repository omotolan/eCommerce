package africa.semicolon.ecommerce.dto;

import africa.semicolon.ecommerce.data.model.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

    private String productId;
    @NotNull
    @NotBlank(message = "Please enter product review")
    private String review;

//    public List<Review> unPackDto(String productId){
//
//    }
}
