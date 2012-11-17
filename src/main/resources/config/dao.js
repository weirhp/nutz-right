var ioc = {
  dataSource : {
    type : "com.mchange.v2.c3p0.ComboPooledDataSource",
    fields : {
      driverClass : 'com.mysql.jdbc.Driver',
      jdbcUrl : 'jdbc:mysql://127.0.0.1:3306/nutz_right_demo',
      user : 'root',
      password : 'weirhp'
    }
  },

  dao : {
    type : 'org.nutz.dao.impl.NutDao',
    args : [ {
      refer : 'dataSource'
    } ]
  }

};