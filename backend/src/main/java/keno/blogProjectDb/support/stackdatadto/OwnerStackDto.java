package keno.blogProjectDb.support.stackdatadto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OwnerStackDto {
    private Long account_id;
    private int reputation;
    private Long user_id;
    private String user_type;
    private String profile_image;
    private String display_name;
    private String link;

}

