package parking

data class Car(var n: Int = 0, var num: String ="", var color: String = ""){
    override fun toString(): String {
        return "${n+1} $num $color"
    }
}

fun notFull(p: ParkingLot): Int{
    for (i in 0 until p.size) if (p.car[i].num == "") return i
    return -1
}

class ParkingLot(var size: Int = 0) {

    val car = mutableListOf<Car>()

    init{
        for (i in 0 until size)
            car.add(Car(i))
    }

    // show full status
    fun status() {
        if (size == 0) println("Sorry, a parking lot has not been created.")
        else {
            var check = true
            for (i in 0 until size)
                if (car[i].num != "") {
                    println(car[i])
                    check = false
                }
            if (check) println("Parking lot is empty.")
        }
    }

    // parking of a new car
    fun park(_num: String, _color: String){
        if (size == 0) println("Sorry, a parking lot has not been created.")
        else {
            val j = notFull(this)
            if (j != -1) {
                println("$_color car parked in spot ${car[j].n + 1}.")
                car[j].num = _num
                car[j].color = _color
            } else println("Sorry, the parking lot is full.")
        }
    }

    // car leaving parking lot
    fun leave(_n: Int){
        if (size == 0) println("Sorry, a parking lot has not been created.")
        else {
            car[_n- 1].num = ""
            car[_n - 1].color = ""
            println("Spot $_n is free.")
        }
    }
    fun reg_by_color(_color: String){
        if (size == 0) println("Sorry, a parking lot has not been created.")
        else {
            var check = true
            val s = mutableListOf<String>()
            for (i in 0 until size) {
                if (car[i].color.uppercase() == _color.uppercase()) {
                    s.add(car[i].num)
                    check = false
                }
            }
            if (!check) println(s.joinToString())
            else println("No cars with color ${_color.uppercase()} were found.")
        }
    }

    fun spot_by_reg(_reg: String){
        if (size == 0) println("Sorry, a parking lot has not been created.")
        else {
            var check = true
            val s = mutableListOf<Int>()
            for (i in 0 until size) {
                if (car[i].num.uppercase() == _reg.uppercase()) {
                    s.add(car[i].n + 1)
                    check = false
                }
            }
            if (!check) println(s.joinToString())
            else println("No cars with registration number $_reg were found.")
        }
    }

    fun spot_by_color(_color: String){
        if (size == 0) println("Sorry, a parking lot has not been created.")
        else {
            var check = true
            val s = mutableListOf<Int>()
            for (i in 0 until size) {
                if (car[i].color.uppercase() == _color.uppercase()) {
                    s.add(car[i].n + 1)
                    check = false
                }
            }
            if (!check) println(s.joinToString())
            else println("No cars with color ${_color.uppercase()} were found.")
        }
    }
}

fun main() {

    var parking = ParkingLot()

    do {
        // reading user command
        val userinput = readLine()!!.split(" ").toMutableList()

        when (userinput[0]) {
            "create" -> {
                parking = ParkingLot(userinput[1].toInt())
                println("Created a parking lot with ${userinput[1]} spots.")
            }
            "status" -> parking.status()
            "park" -> parking.park(userinput[1],userinput[2])
            "leave" -> parking.leave(userinput[1].toInt())
            "reg_by_color" -> parking.reg_by_color(userinput[1])
            "spot_by_color" -> parking.spot_by_color(userinput[1])
            "spot_by_reg" -> parking.spot_by_reg(userinput[1])
        }
    } while (userinput[0] != "exit")

}
