$(function(){
    $(".main_menu a:nth-child(3)").addClass("active")
    $("#search_pt").click(function(){
        $(".pt_search").css("display","block");
    })
    $("#pt_search_close").click(function(){
        $(".pt_search").css("display","");
    })
    $("#pt_keyword").keyup(function(e){
        if(e.keyCode ==13)$("#pt_search_btn").trigger("click");
    })
    $("#pt_search_btn").click(function(){
        $.ajax({
            url:"ptlist/keyword?keyword="+$("#pt_keyword").val(),
            type:"get",
            success:function(r){
                console.log(r);
                $(".search_result ul").html("")
                for(let i=0; i<r.list.length; i++){
                    let str_status="";
                    if(r.list[i].pi_status ==1) str_status ="운영중"
                    if(r.list[i].pi_status ==2) str_status ="보류"
                    if(r.list[i].pi_status ==3) str_status ="폐지"

                    let tag=
                        '<li>'+
                            '<a href="#" data-dep-seq="'+r.list[i].pi_seq+'">'+r.list[i].pi_name+'</a>'+
                            '<span class="status'+r.list[i].pi_status+'">'+str_status+'</span>'+
                        '</li>';
                    $(".search_result ul").append(tag);   
                }

                $(".search_result ul a").click(function(e){
                    e.preventDefault(); //a태그의 링크 기능 제거
                    let seq = $(this).attr("data-dep-seq");
                    let name = $(this).html();

                    $("#pt_name").attr("data-dep-seq",seq);
                    $("#pt_name").val(name);

                    $(".search_result ul").html("");
                    $("#pt_keyword").val("");
                    $(".pt_search").css("display","")
                })
            }
        })
    })
    $("#add_dep").click(function(){
        let pt_name= $("#pt_name").attr("data-dep-seq");
        let ti_name= $("#ti_name").val();
        let ti_birth= $("#ti_birth").val();
        let ti_gen= $("#ti_gen option:selected").val();
        let ti_phone_num= $("#ti_phone_num").val();
        let ti_address= $("#ti_address").val();
        let ti_start_dt= $("#ti_start_dt").val();
        let ti_status= $("#ti_status option:selected").val();
        
        let data={
            ti_pi_seq:pt_name,
            ti_name:ti_name,
            ti_birth:ti_birth,
            ti_gen:ti_gen,
            ti_phone_num:ti_phone_num,
            ti_address:ti_address,
            ti_start_dt:ti_start_dt,
            ti_status:ti_status,
        }
        $.ajax({
            url:"/trainer/add",
            type:"post",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(e){
                alert(e.message);
                if(e.status)
                    location.reload();
            }
        })
    })
    $("#add_trainer").click(function(){
        $(".popup_wrap").css("display","block");
        $("#add_dep").css("display","inline-block");
        $("#modify_dep").css("display","none");
        $("#cancel_dep").css("display","inline-block");
        $(".popup .top_area h2").html("강사 추가");
        $(".popup .top_area p").html("강사 정보를 입력해주세요");
    })
    $("#cancel_dep").click(function(){
        if(confirm("취소하시겠습니까?\n(입력된 내용은 저장되지 않습니다.)")== false) return;

        $("#pt_name").attr("data-dep-seq","");
        $("#ti_name").val("");
        $("#ti_birth").val("");
        $("#ti_phone_num").val("");
        $("#ti_address").val("");
        $("#ti_start_dt").val("");
        $("#ti_gen").val("1").prop("selected",true);
        $("#ti_status").val("1").prop("selected",true);

        $(".popup_wrap").css("display","")
    })
    $(".delete_btn").click(function(){
        if(!confirm("삭제하시겠습니까?")) return
        let seq= $(this).attr("data-seq");
        $.ajax({
            url:"/trainer/delete?seq="+seq,
            type:"delete",
            success:function(r){
                alert(r.message)
                if(r.status)//삭제 성공시
                location.reload();
            }
        })
    })    

    let modifty_seq=0;
    $(".modify_btn").click(function(){
        let seq = $(this).attr("data-seq");
        modify_seq= seq;
        $.ajax({
            url:"/trainer/get?seq="+seq,
            type:"get",
            success:function(r){
                console.log(r)
                $(".popup_wrap").css("display","block");
                $("#add_dep").css("display","none");
                $("#modify_dep").css("display","inline-block");
                $("#cancel_dep").css("display","inline-block");
                $(".popup .top_area h2").html("강사 수정");
                $(".popup .top_area p").html("수정 할 정보를 입력해주세요");

                $("#pt_name").attr("data-dep-seq",r.ti_pi_seq);
                $("#pt_name").val(r.pt_name);
                $("#ti_name").val(r.ti_name);
                $("#ti_birth").val(r.ti_birth);
                $("#ti_phone_num").val(r.ti_phone_num);
                $("#ti_address").val(r.ti_address);
                $("#ti_start_dt").val(r.ti_start_dt);
                $("#ti_gen").val(r.ti_gen).prop("selected",true);
                $("#ti_status").val(r.ti_status).prop("selected",true);
            }
        })
    })
    $("#modify_dep").click(function(){
        if(confirm("수정하시겠습니까?")==false)return;
        let data= {
            ti_seq:modify_seq,
            ti_pi_seq:$("#pt_name").attr("data-dep-seq"),
            ti_name:$("#ti_name").val(),
            ti_birth:$("#ti_birth").val(),
            ti_gen:$("#ti_gen option:selected").val(),
            ti_phone_num:$("#ti_phone_num").val(),
            ti_address:$("#ti_address").val(),
            ti_start_dt:$("#ti_start_dt").val(),
            ti_status:$("#ti_status option:selected").val()
        }
        console.log(data);
        $.ajax({
            url:"/trainer/modify",
            type:"patch",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r){
                alert(r.message)
                if(r.status)
                location.reload();
            }
        })
    })

    $("#search_btn").click(function(){
        let type =$("#search_type option:selected").val();
        let keyword =$("#keyword").val();

        location.href="/trainer?type="+type+"&keyword="+keyword;
    })
    $("#keyword").keydown(function(e){
        console.log(e.keyCode)
        if(e.keyCode == 13){
            $("#search_btn").trigger("click");
        }
    })
})
