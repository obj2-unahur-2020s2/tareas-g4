package ar.edu.unahur.obj2.tareas


interface desarrolloDeTareas{
    val responsable: Responsable
    fun horasNecesarias():Int
    fun costoDeTarea():Double
    fun nomina():Int
}

class Tarea(val horasEstimadas:Int,override val responsable: Responsable,val costoInfraestructura:Double):desarrolloDeTareas{
    var empleadosEnLaTarea = mutableListOf<Empleado>()
    override fun nomina() =
            (this.empleadosEnLaTarea + this.responsable).size
    fun agregarEmpleadoAsignado(empleado: Empleado){
        empleadosEnLaTarea.add(empleado)
    }
    override fun horasNecesarias() =
            this.horasEstimadas / this.empleadosEnLaTarea.size
   override fun costoDeTarea() =
           (this.horasNecesarias()  * this.sumaDeTodosLosSueldosPorHora()) +
                   (this.horasEstimadas * this.responsable.sueldoPorHora) + this.costoInfraestructura
   fun sumaDeTodosLosSueldosPorHora() =
           (this.empleadosEnLaTarea + this.responsable).sumBy { it.sueldoPorHora.toInt() }
}
class TareaIntegracion(override val responsable: Responsable):desarrolloDeTareas{
    var subTareas = mutableListOf<Tarea>()
    override fun horasNecesarias()=
            subTareas.sumBy { it.horasNecesarias() } + this.horasDeReuniones()
     fun horasDeReuniones()=
             subTareas.sumBy { it.horasEstimadas } / 8
    fun agregarTarea(tarea: Tarea){
        subTareas.add(tarea)
    }
    override fun costoDeTarea()=
            this.sumaTotalDeSubTareas().toDouble() + this.responsable.bonus(this)
    fun sumaTotalDeSubTareas()=
            subTareas.sumBy { it.costoDeTarea().toInt() }

    override fun nomina()=
            subTareas.sumBy { it.nomina() } + 1
}

interface trabajadores{


    val sueldoPorHora:Double
}
 class Empleado(override val sueldoPorHora: Double):trabajadores{

}
class Responsable(override val sueldoPorHora: Double):trabajadores{
    fun bonus(tareaIntegracion: TareaIntegracion) =
            tareaIntegracion.sumaTotalDeSubTareas() * 0.03
}