package components

import models.{Employee, Project, ProjectTable}
import providers.DBProvider
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait ProjectComponent extends ProjectTable {
  this: DBProvider =>

  import driver.api._

  def create = db.run(projectTableQuery.schema.create)

  def insert(project: Project) = db.run(projectTableQuery += project)

  def deleteById(id: Int) = {
    val query = projectTableQuery.filter(x => x.emp_id === id)
    db.run(query.delete)
  }

  def updateName(id: Int, name: String): Future[Int] = {
    val query = employeeTableQuery.filter(x => x.id === id)
      .map(_.name).update(name)
    db.run(query)
  }

  def insertOrUpdate(project: Project) = {
    val query = projectTableQuery.insertOrUpdate(project)
    db.run(query)
  }

  def getAll(): Future[List[Project]] = {
    db.run {
      projectTableQuery.to[List].result
    }
  }

  def deleteAll = {
    db.run(projectTableQuery.delete)
  }

  def projectsById(id: Int): Future[List[Project]] = {
    val query = projectTableQuery.filter(x => x.emp_id === id).to[List].result
    db.run(query)
  }

}
object ProjectComponent extends ProjectComponent