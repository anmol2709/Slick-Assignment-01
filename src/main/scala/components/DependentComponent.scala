package components

import components.ProjectComponent._
import models._
import providers.DBProvider
import scala.concurrent.Future

trait DependentComponent extends DependentTable{
  this: DBProvider =>
  import driver.api._

  def create = db.run(dependentTableQuery.schema.create)

  def insert(dependent: Dependent) = db.run(dependentTableQuery += dependent)

  def delete(id: Int) = {
    val query = dependentTableQuery.filter(x => x.emp_id === id)
    db.run(query.delete)
  }

  def updateName(id: Int, name: String): Future[Int] = {
    val query = dependentTableQuery.filter(x => x.emp_id === id)
      .map(_.name).update(name)
    db.run(query)
  }

  def deleteAll = {
    db.run(dependentTableQuery.delete)
  }

  def getAll(): Future[List[Dependent]] ={
    db.run { dependentTableQuery.to[List].result }
  }

  def insertOrUpdate(dependent: Dependent) = {
    val query = dependentTableQuery.insertOrUpdate(dependent)
    db.run(query)
  }


  //Real Life Operation for Dependents of a Particular Employee
   def dependentsById(id:Int):Future[List[Dependent]] ={
     val query=dependentTableQuery.filter(x=>x.emp_id===id).to[List].result
   db.run(query)
  }


}

object DependentComponent extends DependentComponent