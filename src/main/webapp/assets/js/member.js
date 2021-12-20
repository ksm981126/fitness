$(function(){
    $(".main_menu a:nth-child(1)").addClass("active")
    $("#add_member").click(function(){
        // alert("학과 추가 버튼");
        $(".popup_wrap").addClass("open");
        $("#add_dep").css("display","inline-block");
        $("#modify_dep").css("display","none");
        $(".popup .top_area h2").html("회원 추가");
        $(".popup .top_area p").html("회원 정보를 입력해주세요");
    })
    $("#add_dep").click(function(){
        if(confirm("회원을 등록하시겠습니까?")== false) return;
        let mi_name = $("#mi_name").val();
        let mi_birth = $("#mi_birth").val();
        let mi_gen = $("#mi_gen option:selected").val();
        let mi_phone_num = $("#mi_phone_num").val();
        let mi_address = $("#mi_address").val();
        let mi_pt_option = $("#mi_pt_option option:selected").val();
        let mi_locker_option = $("#mi_locker_option option:selected").val();
        let mi_start_dt = $("#mi_start_dt").val();
        let mi_end_dt = $("#mi_end_dt").val();

        let data ={
            mi_name:mi_name,
            mi_birth:mi_birth,
            mi_gen:mi_gen,
            mi_phone_num:mi_phone_num,
            mi_address:mi_address,
            mi_pt_option:mi_pt_option,
            mi_locker_option:mi_locker_option,
            mi_start_dt:mi_start_dt,
            mi_end_dt:mi_end_dt
        }

        $.ajax({
            type:"post",
            url:"/member/add",
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

        $("#mi_name").val("");
        $("#mi_birth").val("");
        $("#mi_gen").val("1").prop("selected", true);
        $("#mi_phone_num").val("");
        $("#mi_address").val("");
        $("#mi_pt_option").val("1").prop("selected", true);
        $("#mi_locker_option").val("1").prop("selected", true);
        $("#mi_start_dt").val("");
        $("#mi_end_dt").val("");

        $(".popup_wrap").removeClass("open");
    })

    $(".delete_btn").click(function(){
        if(confirm("회원정보를 삭제 하시겠습니까?\n(이 동작은 되돌릴 수 없습니다.)") == false)return;
        
        let seq = ($(this).attr("data-seq"));

        $.ajax({
            type:"delete",
            url:"/member/delete?seq="+seq,
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
        $(".popup .top_area h2").html("회원 수정");
        $(".popup .top_area p").html("수정할 내용을 입력해주세요");
        $.ajax({
            type:"get",
            url:"/member/get?seq="+$(this).attr("data-seq"),
            success:function(r){
                console.log(r);
                $("#mi_name").val(r.data.mi_name);
                $("#mi_birth").val(r.data.mi_birth);
                $("#mi_gen").val(r.data.mi_gen).prop("selected", true);
                $("#mi_phone_num").val(r.data.mi_phone_num);
                $("#mi_address").val(r.data.mi_address);
                $("#mi_pt_option").val(r.data.mi_pt_option).prop("selected", true);
                $("#mi_locker_option").val(r.data.mi_locker_option).prop("selected", true);
                $("#mi_start_dt").val(r.data.mi_start_dt);
                $("#mi_end_dt").val(r.data.mi_end_dt);
            }
        })
    })
    $("#modify_dep").click(function(){
        // alert(modify_data_seq)
        if(confirm("수정하시겠습니까?")==false)return;

        let mi_name = $("#mi_name").val();
        let mi_birth = $("#mi_birth").val();
        let mi_gen = $("#mi_gen option:selected").val();
        let mi_phone_num = $("#mi_phone_num").val();
        let mi_address = $("#mi_address").val();
        let mi_pt_option = $("#mi_pt_option option:selected").val();
        let mi_locker_option = $("#mi_locker_option option:selected").val();
        let mi_start_dt = $("#mi_start_dt").val();
        let mi_end_dt = $("#mi_end_dt").val();

        let data ={
            mi_seq:modify_data_seq,
            mi_name:mi_name,
            mi_birth:mi_birth,
            mi_gen:mi_gen,
            mi_phone_num:mi_phone_num,
            mi_address:mi_address,
            mi_pt_option:mi_pt_option,
            mi_locker_option:mi_locker_option,
            mi_start_dt:mi_start_dt,
            mi_end_dt:mi_end_dt
        }
        $.ajax({
            type:"patch",
            url:"/member/update",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })
    $("#search_btn").click(function(){
        location.href="/main?keyword="+$("#keyword").val();
    })
    $("#keyword").keydown(function(e){
        console.log(e.keyCode)
        if(e.keyCode == 13){
            $("#search_btn").trigger("click");
        }
    })
    })
    