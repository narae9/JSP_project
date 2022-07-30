const ls = localStorage;


//Calendar main
const Calendar = {

    init() {
        const today = new Date();
        ScheduleManager.loadSchedule();

        Calendar.showDates(today.getFullYear(), today.getMonth() + 1);
        
        Calendar.year = today.getFullYear();
        Calendar.month = today.getMonth() + 1;

    },
    /*스케줄 메시지창*/
    evtHandle() {
		const today = new Date();
        document.querySelectorAll(".date .day")
            .forEach(elem => {
                elem.onclick = function () {
                    document.querySelector(".modal.schedule")
                        .classList.add("show");

            const day = Calendar.day = this.innerText;
            //const day = Calendar.day = i;
            const $mTitle = document.querySelector(".modal.schedule .modal-title");
            $mTitle.innerHTML = `${Calendar.year}. ${Calendar.month} .${day} 공연 목록`;   
            
            Calendar.refreshScheduleList(Calendar.year,Calendar.month,Calendar.day);
            }
        });
    },
	/*스케줄 메시지 창에 스케줄 입력*/
    refreshScheduleList(y,m,day) {
        const $mScheduleList = document.querySelector(".modal.schedule .schedule-list");
        const schedules = ScheduleManager.schedules.filter(v => v.date == `${Calendar.year}-${Calendar.month}-${Calendar.day}`);
        if(day.length==1) day = "0"+day;
		console.log(y + "~~~" + m + "~~~~" + day);
		$.ajax({
			url:'listCalendar.do',
			type:'post',
			data:{y:y,m:m},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
			let output = "";
			let cnt = 0;		
			$(param.list).each(function(index,item){			
				if(day == item.sh_date.substr(8,2)){
	            output += `<div class="schedule flex aic jcsb">`;
	            output += `<p>` + item.sh_title + ` / ` + item.sh_place +  ` / ` + item.sh_time + `</p>`;
	            output += `<button class="btn btn-danger" onclick="location.href='/group4_project/show/showDetailForm.do?sh_key=`+item.sh_key+`'">예매하기</button>`;
	            output += `</div>`
				cnt++;
		        }
        	});
			console.log(y + "~~~" + m + "~~~~" + day + "cnt:" + cnt);
        	if(cnt==0){
		        output += 
		        `<div class="flex aic jcc" style="width: 100%; height: 100%;">
		            <p style="width:100%;">해당일에는 공연이 없습니다.<br>
		            감사합니다.</p> 
		        </div>`;
		    }	
        	
        		
        	$mScheduleList.innerHTML += output;	
        	
			},
			error:function(){
				alert('네트워크 오류');
			}		
		});		
    },
    /*AJAX 통신*/
    selsectList(){	
		$.ajax({
			url:'listCalendar.do',
			type:'post',
			dataType:'json',
			async:false,
			cache:false,
			timeout:30000,
			success:function(param){
				return param.list;
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	},
    
    /*Calendar 날짜 출력*/
    showDates(y, m) {
		$.ajax({
			url:'listCalendar.do',
			type:'post',
			data:{y:y,m:m},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
		        const before = document.querySelectorAll(".date");
		        before.forEach(v => v.remove());
		          
		        /* //오늘 날짜 구하기
		        const today = new Date();
		        const todayDate = (Calendar.year == today.getFullYear()) &&
		                          (Calendar.month == today.getMonth() + 1)
		                          ? today.getDate()
		                          : null;
				*/
				let output = "";
		        for (
		            let i = -Calendar.getFirstDay(y,m) + 1; 
		            i <= Calendar.getLastDay(y,m); 
		            i++
		        ) {
		            output += 
		            `<div class="date ${i < 1 ? "hidden-date" : ""}">
		                <p class="day" id="day${i}">${i}</p>`;
		           let cnt = 0; //캘린터 공연 타이틀 갯수 카운트
		           $(param.list).each(function(index,item){
						if(i == item.sh_date.substr(8,2)){
							if(cnt<3){
								output += `<p class="day_item">` + item.sh_title + `</p>`;
							}
							if(cnt==3){
								output += `<input type="button" class="day_item_add" value="더보기" onclick="$('#day${i}').trigger('click');"></button>`;
							}
							cnt++;
						}
					});
		            output +=  `</div>`;
		        }
		        
		        Calendar.$calendar.innerHTML += output;
		               
		        Calendar.evtHandle();        
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		
    },

    getFirstDay(y,m) {
        const date = new Date(`${y}-${m}-1`);
        return date.getDay();
    },
    getLastDay(y,m) {
        const date = new Date(y, m, 0);
        return date.getDate();      
    },

    addMonth(m) {
        const date = new Date(
            Calendar.year, Calendar.month + m -1, 1
        );
        Calendar.year = date.getFullYear();
        Calendar.month = date.getMonth() + 1;

        Calendar.showDates(Calendar.year, Calendar.month);
        Calendar.$date.innerHTML = `${Calendar.year}. ${Calendar.month}.`
    },

};

const ScheduleManager = {

    schedules: [],

    loadSchedule() {
        if(!ls['schedules']) return;

        ScheduleManager.schedules = JSON.parse(ls['schedules']);
    },

    saveSchedule() {
        ls['schedules'] = JSON.stringify(ScheduleManager.schedules)
    },

    addSchedule() {
        const title = prompt("Whar is title of schedle?");

        if(!title.match(/^[a-zA-z0-9ㄱ-힣]*$/g))
        return alert("Incorrect title.");

        const id = new Date().getTime();
        this.schedules.push({
            id,
            date: `${Calendar.year}-${Calendar.month}-${Calendar.day}`,
            title
        });

        this.saveSchedule();
        Calendar.refreshScheduleList();
    },

    remove(id){
		const $mScheduleList = document.querySelector(".modal.schedule .schedule-list");
        const index = ScheduleManager.schedules.findIndex(v =>v.id==id);

        ScheduleManager.schedules.splice(index, 1);

        this.saveSchedule();
        //Calendar.refreshScheduleList();
        
        $mScheduleList.innerHTML =``;
    },
}