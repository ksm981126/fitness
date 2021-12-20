<!-- 회원관리페이지 -->
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
    <script src="/assets/js/trainer.js"></script>
    <link rel="stylesheet" href="/assets/css/index.css">
</head>
<body>
    <main>
        <h1><i class="fas fa-user-tie"></i>강사관리</h1>
        <button id="add_member"><i class="fas fa-plus-circle"></i>강사 추가</button>
        <div class="content_area">
            <div class="menu_area">
                <div class="search_box">
                    <input type="text" id="keyword" placeholder="이름 입력"value="${data.keyword}">
                    <button id="search_btn"><i class="fas fa-search"></i></button>
                </div>
                <button id="reset_btn"  onclick="location.href='/trainer'">초기화</button>
            </div>
            <div class="table_area">
                <table>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>이름</th>
                            <th>생년월일</th>
                            <th>성별</th>
                            <th>전화번호</th>
                            <th>주소</th>
                            <th>담당종목</th>
                            <th>입사일</th>
                            <th>조작</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${data.total ==0}">
                            <tr>
                                <td id="nodata" colspan=9"> 데이터가 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${data.list}" var="d">
                            <tr>
                                <td>${d.ti_seq}</td>
                                <td>${d.ti_name}</td>
                                <td>${d.ti_birth}</td>
                                <td>${d.ti_gen == 0?"남":"여"}</td>
                                <td>${d.ti_phone_num}</td>
                                <td>${d.ti_address}</td>
                                <td>${d.ti_subject}</td>
                                <td>${d.ti_start_dt}</td>
                                <td>
                                    <button class="modify_btn" data-seq="${d.mi_seq}"><i class="fas fa-pencil-alt"></i></button>
                                    <button class="delete_btn" data-seq="${d.mi_seq}"><i class="fas fa-trash"></i></button>
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
                        <a href="/trainer?offset=${(i-1)*10}&keyword=${data.keyword}">${i}</a>
                    </c:forEach>
                </div>
                <button id="next"><i class="fas fa-chevron-right"></i></button>
            </div>
        </div>
    </main>
    <div class="popup_wrap">
        <div class="popup" id="member_add">
            <div class="top_area">
                <div class="ico">
                    <i class="fas fa-school"></i>
                </div>
                <h2>강사 추가</h2>
                <p>강사 정보를 입력해주세요.</p>
            </div>
            <div class="content_area">
                <input type="text" id="mi_name" placeholder="강사 명">
                <input type="text" id="mi_birth" placeholder="생년월일(YYYYMMDD)">
                <select id="mi_gen">
                    <option value="" disabled selected hidden>성별</option>
                    <optgroup value="성별" label="성별">
                    <option value="0">남자</option>
                    <option value="1">여자</option>
                </optgroup>
                </select>
                <input type="text" id="mi_phone_num" placeholder="전화번호(01012345678)">
                <input type="text" id="mi_address" placeholder="주소">
                <input type="text" id="ti_subject" placeholder="담당종목">
                <input type="text" id="mi_start_dt" placeholder="시작일(YYYY-MM-DD)">
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