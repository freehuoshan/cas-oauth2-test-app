##this project is to test the CAS Oauth2 function(UI and password)

##The application is a springboot application, first configured application.yml and config /etc/hosts `127.0.0.1  hello.net` and mapping server_name hello.net to poart 8082 and then start directly, visit http://hello.net




##configuration#

    logging:
     # log dir
     file: /home/huoshan/log/demo.log
     level.root: DEBUG
    
    spring.freemarker:
     cache: false
     charset: UTF-8
     enabled: true
     suffix: .ftl
    
    server:
     port: 8082
     
     
    oauth2:
      client_id: clientid
      client_secret: clientSecret
      #cas oauth2 endpoint base url
      cas_server_url_prefix: https://cas.example.org:8443/cas/oauth2.0
      #oauth2 callback url
      call_back_url: http://hello.net/callback
      
      
      
## Oauth2CasPasswordTestDemo is to test Oauth2 password model

    String baseUrl = "https://cas.example.org:8443/cas/oauth2.0/";
	String clientId = "clientid";
	String clientSecret = "clientSecret";
	String username = "1416236046@qq.com";
	String password = "123456";
	
config these variable if you want test Oauth2 password model

 