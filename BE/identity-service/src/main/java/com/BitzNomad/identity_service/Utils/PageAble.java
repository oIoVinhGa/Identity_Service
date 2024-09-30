package com.BitzNomad.identity_service.Utils;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageAble {
            int current;
            int pageSize;
            int totalpages;
            int total;
}
