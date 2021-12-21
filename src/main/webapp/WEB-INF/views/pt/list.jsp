<!-- Pt수업관리 -->
<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <%@include file="/WEB-INF/includes/header.jsp"%>
    <script src="/assets/js/ptlist.js"></script>
    <link rel="stylesheet" href="/assets/css/ptlist.css">
</head>
<body>
    <main>
        <h1><i class="fas fa-chalkboard-teacher"></i>PT수업관리</h1>
        <button id="add_ptlist"><i class="fas fa-plus-circle"></i>수업 추가</button>
        <div class="content_area">
            <div class="menu_area">
                <div class="search_box">
                    <input type="text" id="keyword" placeholder="검색어 입력"value="${data.keyword}">
                    <button id="search_btn"><i class="fas fa-search"></i></button>
                </div>
                <button id="reset_btn" onclick="location.href='/ptlist'">초기화</button>
            </div>
            <div class="table_area">
                <table>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>종목</th>
                            <th>강사ID</th>
                            <th>강사이름</th>
                            <th>상태</th>
                            <th>시간</th>
                            <th>시작일</th>
                            <th>종료일</th>
                            <th>조작</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${data.total ==0}">
                            <tr>
                                <td id="nodata" colspan="9"> 데이터가 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${data.list}" var="d">
                            <tr>
                                <td>${d.pi_seq}</td>
                                <td>${d.pi_name}</td>
                                <td>${d.pi_ti_seq}</td>
                                <td>${d.pi_ti_name}</td>
                                <td class="pt_status">
                                    <c:if test="${d.pi_status == 1}">
                                        <span style="background-color: rgb(17,226,27);">운영</span>
                                    </c:if>
                                    <c:if test="${d.pi_status == 2}">
                                        <span style="background-color: rgb(255,110,26);">보류</span>
                                    </c:if>
                                    <c:if test="${d.pi_status == 3}">
                                        <span style="background-color: rgb(255,23,23);">폐지</span>
                                    </c:if>
                                <td>${d.pi_time}</td>
                                <td>${d.pi_start_dt}</td>
                                <td>${d.pi_end_dt}</td>
                                <td>
                                    <button class="modify_btn" data-seq="${d.pi_seq}"><i class="fas fa-pencil-alt"></i></button>
                                    <button class="delete_btn" data-seq="${d.pi_seq}"><i class="fas fa-trash"></i></button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
            <div class="pager_area">
                <button id="prev"><i class="fas fa-chevron-left"></i></button>
                <div class="pagers">
                    <c:forEach begin="1" end="${data.pageCnt}" var="i">
                        <a href="/ptlist?offset=${(i-1)*10}&keyword=${data.keyword}">${i}</a>
                    </c:forEach>
                </div>
                <button id="next"><i class="fas fa-chevron-right"></i></button>
            </div>
        </div>
    </main>
    <div class="popup_wrap">
        <div class="popup" id="ptlist_add">
            <div class="top_area">
                <div class="ico">
                    <i class="fas fa-chalkboard-teacher"></i>
                </div>
                <h2>수업 추가</h2>
                <p>수업 정보를 입력해주세요.</p>
            </div>
            <div class="content_area">
                <input type="text" id="pi_name" placeholder="수업 명">
                <input type="text" id="pi_ti_seq" placeholder="강사ID(YYYYMMDD00)">
                <input type="text" id="pi_ti_name" placeholder="강사 명">
                <input type="text" id="pi_time" placeholder="PT시간(X요일 hh:mm~hh:mm)">
                <input type="text" id="pi_start_dt" placeholder="시작일(YYYY-MM-DD)">
                <input type="text" id="pi_end_dt" placeholder="종료일(YYYY-MM-DD)">
                <select id="pi_status">
                    <option value="1">운영</option>
                    <option value="2">보류</option>
                    <option value="3">폐지</option>
                </select>
            </div>
            <div class="btn_area">
                <button id="add_dep">등록하기</button>
                <button id="modify_dep">수정하기</button>
                <button id="cancel_dep">취소하기</button>
            </div>
        </div>
    </div>
</body>
</html>