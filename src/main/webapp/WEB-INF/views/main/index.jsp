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
    <script src="/assets/js/member.js"></script>
    <link rel="stylesheet" href="/assets/css/index.css">
</head>
<body>
    <main>
        <h1><i class="fas fa-school"></i>회원관리</h1>
        <button id="add_member"><i class="fas fa-plus-circle"></i>회원 추가</button>
        <div class="content_area">
            <div class="menu_area">
                <div class="search_box">
                    <input type="text" id="keyword" placeholder="이름 입력"value="${data.keyword}">
                    <button id="search_btn"><i class="fas fa-search"></i></button>
                </div>
                <button id="reset_btn">초기화</button>
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
                            <th>PT 사용유무</th>
                            <th>사물함 사용유무</th>
                            <th>시작일</th>
                            <th>종료일</th>
                            <th>조작</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${data.total ==0}">
                            <tr>
                                <td id="nodata" colspan=11"> 데이터가 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${data.list}" var="d">
                            <tr>
                                <td>${d.mi_seq}</td>
                                <td>${d.mi_name}</td>
                                <td>${d.mi_birth}</td>
                                <td>${d.mi_gen == 0?"남":"여"}</td>
                                <td>${d.mi_phone_num}</td>
                                <td>${d.mi_address}</td>
                                <td>${d.mi_pt_option == 0?"미사용":"사용"}</td>
                                <td>${d.mi_locker_option == 0?"미사용":"사용"}</td>
                                <td>${d.mi_start_dt}</td>
                                <td>${d.mi_end_dt}</td>
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
                        <a href="/main?offset=${(i-1)*10}&keyword=${data.keyword}">${i}</a>
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
                <h2>회원 추가</h2>
                <p>회원 정보를 입력해주세요.</p>
            </div>
            <div class="content_area">
                <input type="text" id="mi_name" placeholder="회원 명">
                <input type="text" id="mi_birth" placeholder="생년월일">
                <select id="mi_gen">
                    <option value="0">남자</option>
                    <option value="1">여자</option>
                </select>
                <input type="text" id="mi_phone_num" placeholder="전화번호">
                <input type="text" id="mi_address" placeholder="주소">
                <select id="mi_pt_option">
                    <option value="0">미사용</option>
                    <option value="1">사용</option>
                </select>
                <select id="mi_locker_option">
                    <option value="0">미사용</option>
                    <option value="1">사용</option>
                </select>
                <input type="text" id="mi_start_dt" placeholder="시작일">
                <input type="text" id="mi_end_dt" placeholder="종료일">
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