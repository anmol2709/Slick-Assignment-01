package components


import models.{Employee, EmployeeTable}
import providers.{MySqlProvider, DBProvider, PostGresDBProvider}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


trait EmployeeComponent extends EmployeeTable{

  this:DBProvider =>
  import driver.api._

  def create = db.run(employeeTableQuery.schema.create)

  def insert(employee: Employee) = db.run(employeeTableQuery += employee)

  def delete(id:Int) = {
    val query = employeeTableQuery.filter(x=> x.id === id)
    db.run(query.delete)
  }

  def updateName(id:Int, name:String) : Future[Int] = {
    val query = employeeTableQuery.filter(x => x.id === id)
      .map(_.name).update(name)
    db.run(query)
  }


  def updateEmployee(employee: Employee)={
    employeeTableQuery.filter(x=>x.id===employee.id)
      .map(x=>(x.name,x.experience)).update((employee.name,employee.experience))
  }

  def getAll(): Future[List[Employee]] ={
    db.run { employeeTableQuery.to[List].result }
  }

  def deleteAll={
    db.run(employeeTableQuery.delete)

  }

def insertOrUpdate(employee: Employee)={
  val query=employeeTableQuery.insertOrUpdate(employee)
  db.run(query)

}

}

object EmployeeComponent extends EmployeeComponent with PostGresDBProvider    //change here to change the database(mySql or Postgres)