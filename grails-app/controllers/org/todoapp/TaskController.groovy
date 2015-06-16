package org.todoapp

import grails.converters.JSON
class TaskController {
	def simpleDateFormatUTC=new java.text.SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

	def show={
		if(params.id && Task.exists(params.id)){
			render Task.findById(params.id) as JSON
		}else{
			render Task.list() as JSON
		}
		}

	def save={
		println "inside [save] rest method"
		def jsonTaskObj=request.JSON['todo']
		def task=new Task()
		println jsonTaskObj.dueDate
		task.dueDate=simpleDateFormatUTC.parse(jsonTaskObj.dueDate)
		task.description=jsonTaskObj.description
		task.title=jsonTaskObj.title
		task.isCompleted=jsonTaskObj.isCompleted
		if(task.save(flush: true)){
			render task as JSON
		}else{
			render status:500
		}
	}

	def delete={
		println "inside [delete] rest method"
		def todo
		if(params.id){
			todo=Task.get(params.id)
			if(todo){
				todo.delete(flush: true)
			}
		}
		render todo as JSON
	}

	def option={
		println "inside [option] rest method"
		render status:200
	}

	def update={
		println "inside [update] rest method"
		def todo
		def jsonTaskObj=request.JSON['todo'];
		if(params.id){
			todo=Task.get(params.id)
			if(todo){
				println jsonTaskObj.dueDate
				todo.title=jsonTaskObj.title
				todo.description=jsonTaskObj.description
				todo.isCompleted=jsonTaskObj.isCompleted
				todo.dueDate=simpleDateFormatUTC.parse(jsonTaskObj.dueDate)
				
				if(todo.save(flush: true))
				render todo as JSON
			}
		}
	}

	def beforeInterceptor = {
		response.setHeader('Access-Control-Allow-Origin', '*')
        response.setHeader('Access-Control-Allow-Methods', 'POST, PUT, GET, OPTIONS, PATCH , DELETE')
        response.setHeader('Access-Control-Allow-Headers', 'Origin, X-Additional-Headers-Example,Content-type,Accept')
        response.setHeader('Access-Control-Allow-Credentials', 'true')
        response.setHeader('Access-Control-Max-Age', '1728000')
	}
}
