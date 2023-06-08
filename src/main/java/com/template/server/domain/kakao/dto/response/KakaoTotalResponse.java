package com.template.server.domain.kakao.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.template.server.domain.kakao.dto.KakaoDocumentDto;
import com.template.server.domain.kakao.dto.KakaoMetaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class KakaoTotalResponse {

    @JsonProperty("meta")
    private KakaoMetaDto metaDto;

    @JsonProperty("documents")
    private List<KakaoDocumentDto> documentDtos;
}
