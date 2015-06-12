hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}

environments{
  development{
    dataSource{
      pooled=true
      driverClassName="com.mysql.jdbc.Driver"
      dialect="org.hibernate.dialect.MySQL5InnoDBDialect"
      dbCreate="update"
      url="jdbc:mysql://localhost/grails_todo_app?useUnicode=yes&characterEncoding=UTF-8"
      username="root"
      password="himanshu123"
    }
    hibernate{
      show_sql=true
    }
  }
}