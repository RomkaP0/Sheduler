package com.romka_po.scheduler.model

sealed class Routes(val route:String){
    object ShowEvents:Routes("showEvents")
    object AddEvent:Routes("addEvent")


}
