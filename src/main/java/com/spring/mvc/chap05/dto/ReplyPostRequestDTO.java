package com.spring.mvc.chap05.dto;

import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder

//RequestDTO는 클라이언트가 제대로 값을 보냈는지 검증해야함

public class ReplyPostRequestDTO {
    //필드명은 클라이언트 개발자와 상의해야함
    //entity는 db컬럼명과 일치해야함(MARIADB) ex, entity.Reply 클래스
    // entity는 db와 상호작용, dto는 클라이언트와 상호작용

    //검증이 꼭 필요함 (이것을 어기면 에러 400대로 나오게 해야함 - controller 처리)

    @NotBlank //필수값
    private String text; //댓글 내용
    @NotBlank //필수값
    @Size(min =2 , max=8)
    private String author; //댓글작성자명

    /*
        @NotNull - null을 허용하지 않음
        @NotBlank - null + " "(emptySting)을 허용하지 않음
        그러면 not blank로만 해도되는거아닌가?
     */
    @NotNull
    private Long bno; //원본 글번호

    //dto를 entity로 바꿔서 리턴하는 메서드
    public Reply toEntity(){
        return Reply.builder()
                .replyText(this.text)
                .replyWriter(this.author)
                .boardNo(this.bno)
                .build();
    }


}
