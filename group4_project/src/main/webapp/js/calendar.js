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
        document.querySelectorAll(".date")
            .forEach(elem => {
                elem.onclick = function () {
                    document.querySelector(".modal.schedule")
                        .classList.add("show");

            const day = Calendar.day = this.innerText;
            const $mTitle = document.querySelector(".modal.schedule .modal-title");
            $mTitle.innerHTML = `Schedules<br>in ${Calendar.year}. ${Calendar.month} .${day}`;   
            
            Calendar.refreshScheduleList();
            }
        });
    },

    refreshScheduleList() {
        const $mScheduleList = document.querySelector(".modal.schedule .schedule-list");
        const schedules = ScheduleManager.schedules.filter(v => v.date == `${Calendar.year}-${Calendar.month}-${Calendar.day}`);

        if(schedules.length)
            $mScheduleList.innerHTML = schedules.map(sc =>`
                    <div class="schedule flex aic jcsb">
                        <h3>${sc.title}</h3>
                        <button onclick="ScheduleManager.remove('${sc.id}')">x</button>
                    </div>
            `).join("\n\n");
        else
        $mScheduleList.innerHTML = `
        <div class="flex aic jcc" style="width: 100%; height: 100%;">
            There is no schedules.. 
        </div>
        `;
    },
    /*Calendar 날짜 출력*/
    showDates(y, m) { 
        const before = document.querySelectorAll(".date");
        before.forEach(v => v.remove());  
        
        const today = new Date();
        const todayDate = (Calendar.year == today.getFullYear()) &&
                          (Calendar.month == today.getMonth() + 1)
                          ? today.getDate()
                          : null;

        for (
            let i = -Calendar.getFirstDay(y,m) + 1; 
            i <= Calendar.getLastDay(y,m); 
            i++
        ) {
            Calendar.$calendar.innerHTML += `
            <div class="date ${i < 1 ? "hidden-date" : ""}">
                <p>${i}</p>
            <div>
            `;
        }
        Calendar.evtHandle();
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
        const index = ScheduleManager.schedules.findIndex(v =>v.id==id);

        ScheduleManager.schedules.splice(index, 1);

        this.saveSchedule();
        Calendar.refreshScheduleList();
    },
}