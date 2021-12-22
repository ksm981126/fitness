<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <%@include file="/WEB-INF/includes/header.jsp"%>
    <link rel="stylesheet" href="/assets/css/dashboard.css">
    <script>
        $(function(){
            $(".main_menu a:nth-child(1)").addClass("active")
        })
    </script>
</head>
<body>
    <main>
        <h1>피트니스 클럽 관리 대시보드 (Fitness club management dashboard)</h1>
            <div class="content_area">
                <div class="member_info">
                    <h2><i class="fas fa-male"></i>회원 정보</h2>
                    <p> 총 등록 회원 : <span>${cnt.member[0]}명</span></p>
                    <p> PT 이용 회원 : <span>${cnt.member[1]}명</span></p>
                    <p> 락커룸 이용 회원 : <span>${cnt.member[2]}명</span></p>
                    <p><i class="fas fa-clock"></i> 업데이트 날짜 : <span><fmt:formatDate value="${update.member}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
                </div>
                <div class="trainer_info">
                    <h2><i class="fas fa-user-tie"></i>강사 정보</h2>
                    <p> 총 등록 강사 : <span>${cnt.trainer[0]}명</span></p>
                    <p> 재직 중 강사 : <span>${cnt.trainer[1]}명</span></p>
                    <p> 휴직 중 강사 : <span>${cnt.trainer[2]}명</span></p>
                    <p> 휴가 중 강사 : <span>${cnt.trainer[3]}명</span></p>
                    <p><i class="fas fa-clock"></i> 업데이트 날짜 : <span><fmt:formatDate value="${update.trainer}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
                </div>
                <div class="pt_info">
                    <h2><i class="fas fa-user-graduate"></i>PT수업 정보</h2>
                    <p> 총 등록 수업 : <span>${cnt.ptlist[0]}건</span></p>
                    <p> 운영 중 수업 : <span>${cnt.ptlist[1]}건</span></p>
                    <p> 보류 중 수업 : <span>${cnt.ptlist[2]}건</span></p>
                    <p> 폐지/폐지 예정 수업 : <span>${cnt.ptlist[3]}건</span></p>
                    <p><i class="fas fa-clock"></i> 업데이트 날짜 :  <span><fmt:formatDate value="${update.ptlist}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
                </div>
                <div class="pt_member_info">
                    <h2><i class="fas fa-chalkboard-teacher"></i>PT회원 정보</h2>
                    <p> 총 등록 회원 : <span> ${cnt.ptmember[0]}명</span></p>
                    <!-- <p> 개강 : <span>${cnt.subject[1]}건</span></p>
                    <p> 폐강 : <span>${cnt.subject[2]}건</span></p>
                    <p> 종강 : <span>${cnt.subject[3]}건</span></p> -->
                    <p><i class="fas fa-clock"></i> 업데이트 날짜 : <span>2021-12-09 12:00:00</span></p>
                </div>
            </div>
    </main>
</body>
</html>