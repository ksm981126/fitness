<!-- 강사관리페이지 -->
<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <%@include file="/WEB-INF/includes/header.jsp"%>
    <script src="/assets/js/trainer.js"></script>
    <link rel="stylesheet" href="/assets/css/ptlist.css">
    <link rel="stylesheet" href="/assets/css/trainer.css">
</head>
<body>
    <main>
        <h1><i class="fas fa-user-tie"></i>강사관리</h1>
        <button id="add_trainer"><i class="fas fa-plus-circle"></i>강사 추가</button>
        <div class="content_area">
            <div class="menu_area">
                <div class="search_box">
                    <select id="search_type"> 
                        <option value="subj">종목</option>
                        <option value="name"
                            <c:if test="${data.type=='name'}">selected</c:if>
                        >이름</option>
                    </select>
                    <input type="text" id="keyword" placeholder="검색어 입력"value="${data.keyword}">
                    <button id="search_btn"><i class="fas fa-search"></i></button>
                </div>
                <button id="reset_btn" onclick="location.href='/trainer'">초기화</button>
            </div>
            <div class="table_area">
                <table>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>담당종목</th>
                            <th>이름</th>
                            <th>상태</th>
                            <th>성별</th>
                            <th>생년월일</th>
                            <th>전화번호</th>
                            <th>주소</th>
                            <th>입사일</th>
                            <th>등록일</th>
                            <th>수정일</th>
                            <th>조작</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${data.total ==0}">
                            <tr>
                                <td id="nodata" colspan="12"> 데이터가 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${data.list}" var="d">
                            <tr>
                                <td>${d.ti_seq}</td>
                                <td>${d.pt_name}</td>
                                <td>${d.ti_name}</td>
                                <td class="trainer_status">
                                    <c:if test="${d.ti_status == 1}">
                                        <span style="background-color: rgb(17,226,27);">정상</span>
                                    </c:if>
                                    <c:if test="${d.ti_status == 2}">
                                        <span style="background-color: rgb(255,110,26);">휴직</span>
                                    </c:if>
                                    <c:if test="${d.ti_status == 3}">
                                        <span style="background-color: rgb(251,186,64);">휴가</span>
                                    </c:if>
                                    <c:if test="${d.ti_status == 4}">
                                        <span style="background-color: rgb(255,23,23);">퇴사</span>
                                    </c:if>
                                </td>
                                <td>${d.ti_gen == 0?"남":"여"}</td>
                                <td>${d.ti_birth}</td>
                                <td>${d.ti_phone_num}</td>
                                <td>${d.ti_address}</td>
                                <td>${d.ti_start_dt}</td>
                                <td><fmt:formatDate value="${d.ti_reg_dt}" pattern="yyyy년 MM월 dd일 (EE) HH:mm:ss"/></td>
                                <td><fmt:formatDate value="${d.ti_mod_dt}" pattern="yyyy년 MM월 dd일 (EE) HH:mm:ss"/></td>
                                <td>
                                    <button class="modify_btn" data-seq="${d.ti_seq}"><i class="fas fa-pencil-alt"></i></button>
                                    <button class="delete_btn" data-seq="${d.ti_seq}"><i class="fas fa-trash"></i></button>
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
                        <a href="/trainer?offset=${(i-1)*10}&type=${type}&keyword=${keyword}">${i}</a>
                    </c:forEach>
                </div>
                <button id="next"><i class="fas fa-chevron-right"></i></button>
            </div>
        </div>
    </main>
    <div class="popup_wrap">
        <div class="popup" id="trainer_add">
            <div class="top_area">
                <div class="ico">
                    <i class="fas fa-school"></i>
                </div>
                <h2>강사 추가</h2>
                <p>강사 정보를 입력해주세요.</p>
            </div>
            <div class="content_area">
                <input type="text" id="pt_name" placeholder="종목 명" disabled>
                <button id="search_pt">종목 검색</button>
                <br>
                <input type="text" id="ti_name" placeholder="강사 명">
                <select id="ti_gen">
                    <option value="" disabled selected hidden>성별</option>
                    <optgroup value="성별" label="성별">
                        <option value="0">남자</option>
                        <option value="1">여자</option>
                    </optgroup>
                </select>
                <input type="text" id="ti_birth" placeholder="생년월일(YYYYMMDD)">
                <input type="text" id="ti_phone_num" placeholder="전화번호(01012345678)">
                <input type="text" id="ti_address" placeholder="주소">
                <input type="text" id="ti_start_dt" placeholder="입사일(YYYY-MM-DD)">
                <select id="ti_status">
                    <option value="" disabled selected hidden>상태</option>
                    <optgroup value="상태" label="상태">
                    <option value="1">정상</option>
                    <option value="2">휴직</option>
                    <option value="3">휴가</option>
                    <option value="4">퇴사</option>
                </optgroup>
                </select>
            </div>
            <div class="btn_area">
                <button id="add_dep">등록하기</button>
                <button id="modify_dep">수정하기</button>
                <button id="cancel_dep">취소하기</button>
            </div>
        </div>
    </div>
    <div class="pt_search">
        <div class="pt_search_box">
            <input type="text" id="pt_keyword" placeholder="예) 필라테스,요가,수영">
            <button id="pt_search_btn"><i class="fas fa-search"></i></button>
        </div>
        <div class="search_result">
            <ul>
            </ul>
        </div>
        <div class="pt_search_buttons">
            <button id="pt_search_close">닫기</button>
        </div>
    </div>
</body>
</html>