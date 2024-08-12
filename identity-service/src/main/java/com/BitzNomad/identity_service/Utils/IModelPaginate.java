package com.BitzNomad.identity_service.Utils;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IModelPaginate<T> {
	private PageAble meta;
    private T result;


}