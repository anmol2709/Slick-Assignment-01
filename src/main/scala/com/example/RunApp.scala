package com.example

import components.{DependentComponent, ProjectComponent, EmployeeComponent}
import models.{Project, Dependent, Employee}

object RunApp extends App {


  //EMPLOYEE TABLE ENTRIES

    EmployeeComponent.create
    EmployeeComponent.insert(Employee(1,"anmol",2D))
    EmployeeComponent.insert(Employee(6,"abcd",3D))
    EmployeeComponent.insert(Employee(9,"abc",2D))
    EmployeeComponent.insertOrUpdate(Employee(6, "anmol", 5D))
    EmployeeComponent.updateName(9,"anmol")
//  EmployeeComponent.delete(9)


  //PROJECT TABLE ENTRIES

    ProjectComponent.create
    ProjectComponent.insert(Project(1,"p3"))
    ProjectComponent.insert(Project(1,"p2"))
    ProjectComponent.insert(Project(9,"p1"))
    //ProjectComponent.deleteAll

  //DEPENDENT TABLE ENTRIES

      DependentComponent.create
      DependentComponent.insert(Dependent(1,"abc","brother"))
      DependentComponent.insert(Dependent(1,"abcd","sister"))


  val listOfEmployees=EmployeeComponent.getAll()
  val listOfProjects=ProjectComponent.getAll()
  val listOfDependents=DependentComponent.getAll()
  val dependentsOnEmployee=DependentComponent.dependentsById(1)
  val projectsOfEmployee=ProjectComponent.projectsById(1)

  Thread.sleep(5000)


    println(listOfEmployees)
    println(listOfProjects)
    println(listOfDependents)
    println(dependentsOnEmployee)
    println(projectsOfEmployee)

  Thread.sleep(5000)
}
