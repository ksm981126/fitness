// ptlist.js
$(function(){
    $(".main_menu a:nth-child(4)").addClass("active")
    $("#add_ptlist").click(function(){
        $(".popup_wrap").addClass("open");
        $("#add_dep").css("display","inline-block");
        $("#modify_dep").css("display","none");
        $(".popup .top_area h2").html("수업 추가");
        $(".popup .top_area p").html("수업 정보를 입력해주세요");
    })
    $("#add_dep").click(function(){
        if(confirm("수업을 등록하시겠습니까?")== false) return;
        let pi_name = $("#pi_name").val();
        let pi_ti_seq = $("#pi_ti_seq").val();
        let pi_ti_name = $("#pi_ti_name").val();
        let pi_time = $("#pi_time").val();
        let pi_start_dt = $("#pi_start_dt").val();
        let pi_end_dt = $("#pi_end_dt").val();
        let pi_status = $("#pi_status option:selected").val();

        let data ={
            pi_name:pi_name,
            pi_ti_seq:pi_ti_seq,
            pi_ti_name:pi_ti_name,
            pi_time:pi_time,
            pi_start_dt:pi_start_dt,
            pi_end_dt:pi_end_dt,
            pi_status:pi_status,
        }

        $.ajax({
            type:"post",
            url:"/ptlist/add",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r){
                alert(r.message);
                if(r.status)
                location.reload();
            }
        })

    })
    $("#cancel_dep").click(function(){
        if(confirm("취소하시겠습니까?\n(입력된 정보는 저장되지 않습니다.)") == false)return;

        $("#pi_name").val("");
        $("#pi_ti_seq").val("");
        $("#pi_ti_name").val("");
        $("#pi_time").val("");
        $("#pi_start_dt").val("");
        $("#pi_end_dt").val("");
        $("#pi_status").val("1").prop("selected",true);

        $(".popup_wrap").removeClass("open");
    })

    $(".delete_btn").click(function(){
        if(confirm("수업을 삭제 하시겠습니까?\n(이 동작은 되돌릴 수 없습니다.)") == false)return;
        
        let seq = ($(this).attr("data-seq"));

        $.ajax({
            type:"delete",
            url:"/ptlist/delete?seq="+seq,
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    });
    let modify_data_seq=0;

    $(".modify_btn").click(function(){
        // alert($(this).attr("data-seq"))
        modify_data_seq =$(this).attr("data-seq");
        $(".popup_wrap").addClass("open");
        $("#add_dep").css("display","none");
        $("#modify_dep").css("display","inline-block");
        $(".popup .top_area h2").html("수업 수정");
        $(".popup .top_area p").html("수정할 내용을 입력해주세요");
        $.ajax({
            type:"get",
            url:"/ptlist/get?seq="+$(this).attr("data-seq"),
            success:function(r){
                console.log(r);
                $("#pi_name").val(r.data.pi_name);
                $("#pi_ti_seq").val(r.data.pi_ti_seq);
                $("#pi_ti_name").val(r.data.pi_ti_name);
                $("#pi_time").val(r.data.pi_time);
                $("#pi_start_dt").val(r.data.pi_start_dt);
                $("#pi_end_dt").val(r.data.pi_end_dt);
                $("#pi_status").val(r.data.pi_status).prop("selected",true);
            }
        })
    })
    $("#modify_dep").click(function(){
        // alert(modify_data_seq)
        if(confirm("수정하시겠습니까?")==false)return;

        let pi_name = $("#pi_name").val();
        let pi_ti_seq = $("#pi_ti_seq").val();
        let pi_ti_name = $("#pi_ti_name").val();
        let pi_time = $("#pi_time").val();
        let pi_start_dt = $("#pi_start_dt").val();
        let pi_end_dt = $("#pi_end_dt").val();
        let pi_status = $("#pi_status option:selected").val()

        let data ={
            pi_seq: modify_data_seq,
            pi_name:pi_name,
            pi_ti_seq:pi_ti_seq,
            pi_ti_name:pi_ti_name,
            pi_time:pi_time,
            pi_start_dt:pi_start_dt,
            pi_end_dt:pi_end_dt,
            pi_status:pi_status,
        }
        $.ajax({
            type:"patch",
            url:"/ptlist/update",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })
    $("#search_btn").click(function(){
        location.href="/ptlist?keyword="+$("#keyword").val();
    })
    $("#keyword").keydown(function(e){
        console.log(e.keyCode)
        if(e.keyCode == 13){
            $("#search_btn").trigger("click");
        }
    })
    })