<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.util.BSPageBar" %>
<%
//noticeList_jsp.java -> noticeList_jsp.class
	//서블릿(FrontMVC)을 경유[NoticeController-> NoticeLogic]하고 
	//select한 결과를 돌려(List<Map>) 받아서 request객체에 담아 두었다
	//                                         req.setAttribute("xList", nList);
	int size = 0;//전체 레코드 수
	List<Map<String,Object>> nList = (List)request.getAttribute("nList");
	if(nList !=null){
		size = nList.size();
	}
	//out.print(size);
	//테스트 링크
	//http://localhost:8000/notice/noticeList.gd - FrontMVC경유하는 경우
	//http://localhost:8000/notice/noticeList.jsp - FrontMVC경유하지 않는다.
  //한 페이지에 몇 개씩 뿌릴거야?
  //1(26, 25, 24, 23, 22) 2(21, 20, 19, 18, 17) 3(16, 15, 14, 13, 12) > 4(11, 10, 9, 8, 7) 5(6, 5, 4, 3, 2) 6(1)
  int numPerPage = 3;		
  int nowPage = 0;
  //Servlet이 아님으로 request 다 적어야 함
  if(request.getParameter("nowPage") != null){
    nowPage = Integer.parseInt(request.getParameter("nowPage"));
  }
%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>공지사항{webapp}</title>
    <%@include file="/common/bootstrap_common.jsp" %>
	<link rel="stylesheet" href="/css/notice.css">
    <script type="text/javascript">
      // 화면을 리액트로 사용해보는 것만으로 자바스크립트 복습 및 최신 문법을 공부할 수 있음
    	function searchEnter(){
    		console.log('searchEnter')
        console.log(window.event.keyCode); //13
        if(window.event.keyCode == 13){
          noticeSearch(); //재사용성						
        }///end of searchEnter
    	}

		
		function noticeSearch(){
			console.log('noticeSearch');
			const gubun = document.querySelector("#gubun").value;
			const keyword = document.querySelector("#keyword").value;
			console.log(`${gubun} , ${keyword}`);
			location.href="/notice/noticeList?gubun="+gubun+"&keyword="+keyword;
      document.querySelector("#gubun").value = '분류선택';
      document.querySelector("#keyword").value = '';
		}
    const noticeDetail = (n_no) => {
      location.href = "/notice/noticeDetail?n_no="+n_no;
    }    
    const noticeInsert = () => {
      // input type의 button은 submit이 아니다. 전송 -> action의 url로 이동
      // 웹 서비스에서의 URL요청은 NoticeController메소드 호출을 의미하는 것
      // @GetMapping, PostMapping Restful API : 컨트롤 계층에만 국한됨
      document.querySelector("#f_notice").submit();
    }
    </script>
  </head>
  <body>
    <!-- 공지사항 목록 처리할 코드 작성해 보기 -->
	<!-- header start -->
	<%@include file="/include/gym_header.jsp" %>
	<!-- header end -->
	<div class="container">
		<div class="page-header">
			<h2>공지사항 <small>공지목록</small></h2>
			<hr />
		</div>
		<!-- 검색기 시작 -->
		<div class="row">
			<div class="col-3">
		    	<select id="gubun" class="form-select" aria-label="분류선택">
		      		<option value="none">분류선택</option>
		      		<option value="n_title">제목</option>
		      		<option value="n_writer">작성자</option>
		      		<option value="n_content">내용</option>
		    	</select>			
		  	</div>
			<div class="col-6">
		 		<input type="text" id="keyword" class="form-control" placeholder="검색어를 입력하세요" 
		           aria-label="검색어를 입력하세요" aria-describedby="btn_search" onkeyup="searchEnter()"/>
			</div>
			<div class="col-3">
		 		<button id="btn_search" class="btn btn-danger" onClick="noticeSearch()">검색</button>
		 	</div>
		</div>		
		<!-- 검색기 끝 -->
		
		<!-- 회원목록 시작 -->
		<div class='notice-list'>
			<table class="table table-hover">
		    	<thead>
		      		<tr>
		        		<th width="10%">#</th>
		        		<th width="50%">제목</th>
		        		<th width="20%">작성자</th>
		      		</tr>
		    	</thead>
		    	<tbody>	      	
<%
	//스크립틀릿 - 지변이다, 메소드 선언불가, 생성자 선언불가, 실행문
	//n건을 조회하는 경우이지만 resultType에는 map이나 vo패턴을 주는게 맞다
	//주의사항 - 자동으로 키값을 생성함 - 디폴트가 대문자이다
	//myBatis연동시 resultType=map{한행}으로 줌 -> selectList("noticeList", pMap)
	for(int i=nowPage*numPerPage;i<(nowPage*numPerPage)+numPerPage;i++){
    if(i == size) break;
		Map<String,Object> rmap = nList.get(i);
%>					
					<tr>
						<td><%=rmap.get("N_NO") %></td>
						<td>
              <a href="javascript:noticeDetail('<%=rmap.get("N_NO")%>')"><%=rmap.get("N_TITLE") %></a>
            </td>
						<td><%=rmap.get("N_WRITER") %></td>
					</tr>					
<%
	}
%>
		    	</tbody>
			</table> 
    
<!-- [[ 페이징 처리  구간  ]] -->
			<div style="display:flex; justify-content:center;">
				<ul class="pagination">

<% 
  String pagePath = "noticeList";
  BSPageBar bspb = new BSPageBar(numPerPage, size, nowPage, pagePath);
  out.print(bspb.getPageBar());
%>

        </ul>
			</div>
<!-- [[ 페이징 처리  구간  ]] -->		
	  
		  	<div class='notice-footer'>
		    	<button class="btn btn-warning" onclick="noticeSearch()">
		      		전체조회
		    	</button>&nbsp; 
			    <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#noticeForm">
			    글쓰기
			    </button>
		    </div>
		</div>		
		<!-- 회원목록   끝  -->		
	</div>
	<!-- body end       -->
	<!-- footer start -->
	<%@include file="/include/gym_footer.jsp" %>
	<!-- footer end    -->	
	<!-- ========================== [[ 공지사항 Modal ]] ========================== -->
	<div class="modal" id="noticeForm">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">공지사항</h4>
	        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
	      </div>
	      <!-- Modal body -->
	      <div class="modal-body">
	      	<form id="f_notice" method="post" action="./noticeInsert"><!-- method가 get이면 @GetMapping 사용해야함 -->
	      	  <input type="hidden" name="method" value="memberInsert">
	          <div class="form-floating mb-3 mt-3">
              <!-- getParameter에 들어오는 값은 name만 가능함. id는 불가함(값을 못 가져옴) -->
              <!-- id와 name이 같아도 됨. id: Front-End, name: Back-End에서 사용 -->
              <!-- 개발 설계 - 화면정의서 업무 분장 : 모달인가 모달이 아닌가 -->
              <!-- 모달에서 사용하는 아이디가 목록에서 동시에 사용하는 것은 불가함 -->
	            <input type="text"  class="form-control" id="n_title" name="n_title" placeholder="Enter 제목" />
	            <label for="n_title">제목</label>
	          </div>	      	
	          <div class="form-floating mb-3 mt-3">
	            <input type="text"  class="form-control" id="n_writer" name="n_writer" placeholder="Enter 작성자" />
	            <label for="n_writer">작성자</label>
	          </div>	      	
	          <div class="form-floating mb-3 mt-3">
	            <textarea rows="5" class="form-control h-25" aria-label="With textarea" id="n_content" name="n_content"></textarea>
	          </div>	      	
	      	</form>
	      </div>	
	      <div class="modal-footer">
	        <input type="button" class="btn btn-warning" data-bs-dismiss="modal" onclick="noticeInsert()"  value="저장">
	        <input type="button" class="btn btn-danger" data-bs-dismiss="modal" value="닫기">
	      </div>
	
	    </div>
	  </div>
	</div>
    <!-- ========================== [[ 공지사항 Modal ]] ========================== -->

  </body>
</html>
<!-- 
	M : margin -  외부여백
	P : padding  - 안쪽 여백
	
	t: top
	b: bottom
	l: left
	r:right
	x: x축 -> left, right
	y: y축 -> top, bottom
	
	
	- 0, 1, 2, 3,4 5, auto
	1: .25 rem(font-size가 16px이면 4px)
	2. : 0.5
	3: 1rem
	4: 1.5rem
	5: 3rem
-->