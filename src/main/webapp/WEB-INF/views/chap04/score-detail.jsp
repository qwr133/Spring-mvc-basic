
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>

    .wrap{
        border: 1px, solid, orange;
    }

</style>
</head>
<body>

    <div class="wrap">
    <h1>${s.name}님 성적 정보</h1>

    <ul class = "subject">
        <li># 국어 : ${s.kor} </li>
        <li># 영어 : ${s.eng} </li>
        <li># 수학 : ${s.math} </li>
        <li># 총점 : ${s.total} </li>
        <li># 평균 : ${s.average} </li>
        <li># 학점 : ${s.grade} </li>
    </ul>

    <div class="btn-group">
        <a class="list-btn" href="/score/list">목록</a>
        <!-- 해당되는 stuNum에 대한 수정화면 띄어줘!! get으로 받아야함(조회니까) -->
        <a class="list-btn" href="/score/modify?stuNum=${sName.stuNum}">수정</a>
    </div>

    </div>
</body>
</html>