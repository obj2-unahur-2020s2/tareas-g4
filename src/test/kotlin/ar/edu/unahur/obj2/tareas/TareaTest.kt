package ar.edu.unahur.obj2.tareas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class TareaTest : DescribeSpec({
  describe("Una tarea") {
      val responsable = Responsable(150.33)
      val tarea1 = Tarea(13,responsable,150000.00)
      val empleado1 = Empleado(99.50)
      val empleado2 = Empleado(99.50)
      val empleado3 = Empleado(99.50)
      val tarea2 = Tarea(17,responsable,180000.00)
      tarea1.agregarEmpleadoAsignado(empleado1)
      tarea1.agregarEmpleadoAsignado(empleado2)
      tarea1.agregarEmpleadoAsignado(empleado3)
      tarea2.agregarEmpleadoAsignado(empleado1)
      tarea2.agregarEmpleadoAsignado(empleado2)
      val tareaIntegracion = TareaIntegracion(responsable)
      tareaIntegracion.agregarTarea(tarea1)
      tareaIntegracion.agregarTarea(tarea2)
      it("saber la nomina de la tarea"){
          tarea1.nomina().shouldBe(4)
      }
      it("saber cuantas horas se necesitan para finalizar la tarea"){
          tarea1.horasNecesarias().shouldBe(4)
      }
      it("saber la suma total de todos los sueldos por hora"){
          tarea1.sumaDeTodosLosSueldosPorHora().shouldBe(447)
      }
      it("costo de la tarea") {
          tarea1.costoDeTarea().shouldBe(153742.29)
      }
      it("horas de reuniones para tareaIntegracion"){
          tareaIntegracion.horasDeReuniones().shouldBe(3)
      }
      it("horas necesarias para tareaIntegracion"){
          tareaIntegracion.horasNecesarias().shouldBe(15)
      }
      it("costo para tareaIntegracion"){
          tareaIntegracion.costoDeTarea().shouldBe(349253.43)
      }
      it("nomina para tareaIntegracion"){
          tareaIntegracion.nomina().shouldBe(8)
      }

  }
})
