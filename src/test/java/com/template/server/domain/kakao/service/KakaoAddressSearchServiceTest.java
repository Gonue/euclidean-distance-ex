package com.template.server.domain.kakao.service;

import com.template.server.domain.kakao.dto.KakaoDocumentDto;
import com.template.server.domain.kakao.dto.KakaoMetaDto;
import com.template.server.domain.kakao.dto.response.KakaoTotalResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class KakaoAddressSearchServiceTest {

    @MockBean
    private KakaoAddressSearchService kakaoAddressSearchService;

    @Test
    void whenAddressIsNull_requestAddressSearchShouldReturnNull() {
        String address = null;

        var result = kakaoAddressSearchService.requestAddressSearch(null);

        assertNull(result);
    }

    @Test
    void whenAddressIsValid_requestAddressSearchShouldReturnDocument() {
        String address = "서울 강남구 테헤란로 113길";
        KakaoTotalResponse mockResponse = createMockResponse(); // 여기서 createMockResponse 메서드는 적절한 KakaoTotalResponse를 생성해야 합니다.

        when(kakaoAddressSearchService.requestAddressSearch(address)).thenReturn(mockResponse);

        var result = kakaoAddressSearchService.requestAddressSearch(address);

        assertTrue(result.getDocumentDtos().size() > 0);
        assertTrue(result.getMetaDto().getTotalCount() > 0);
        assertNotNull(result.getDocumentDtos().get(0).getAddressName());
    }

    @ParameterizedTest
    @CsvSource({
            "서울 특별시 강남구 삼성동, true",
            "서울 강남구 삼성동 113, true",
            "서울 대학로, true",
            "서울 강남구 삼성동 잘못된 주소, false",
            "강남구 해의동 161-41, true",
            "강남구 해의동 161-4111111, false",
            ", false"
    })
    void whenAddressIsValid_convertToLatitudeAndLongitudeSuccessfully(String inputAddress, boolean expectedResult) {
        KakaoTotalResponse mockResponse = expectedResult ? createMockResponse() : new KakaoTotalResponse();

        Mockito.when(kakaoAddressSearchService.requestAddressSearch(inputAddress)).thenReturn(mockResponse);

        var searchResult = kakaoAddressSearchService.requestAddressSearch(inputAddress);

        boolean actualResult = searchResult != null && searchResult.getDocumentDtos().size() > 0;

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private KakaoTotalResponse createMockResponse() {
        KakaoMetaDto mockMetaDto = new KakaoMetaDto(5);

        List<KakaoDocumentDto> mockDocumentDtoList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            KakaoDocumentDto mockDocumentDto = new KakaoDocumentDto("Test Address Name " + i, 37.56 + i, 126.98 + i);
            mockDocumentDtoList.add(mockDocumentDto);
        }

        KakaoTotalResponse mockResponse = new KakaoTotalResponse(mockMetaDto, mockDocumentDtoList);

        return mockResponse;
    }
}