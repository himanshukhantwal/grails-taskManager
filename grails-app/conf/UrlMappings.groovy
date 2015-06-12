class UrlMappings {

	static mappings = {
        "/todos/$id?"(
        	controller:'task',
       		action:[GET:"show",POST:"save",OPTIONS:"option",DELETE:"delete",PUT:"update"]
        )

        "500"(view:'/error')
	}
}
