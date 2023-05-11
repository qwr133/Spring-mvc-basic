package com.spring.mvc.chap05.dto.response;


import com.spring.mvc.chap05.dto.page.PageMaker;
import lombok.*;

import java.util.List;
@Setter @Getter @ToString
@NoArgsConstructor @AllArgsConstructor
@Builder
//responseDTO 서버가 클라이언트에게 깔끔하게 보여줄 데이터
public class ReplyListResponseDTO {


    private int count; //총 댓글 수
    private PageMaker pageInfo; // 페이지 정보
    private List<ReplyDetailResponseDTO> replies; //댓글 리스트


}

