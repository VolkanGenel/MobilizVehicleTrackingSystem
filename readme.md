PROJE İÇİN DOCKER'DA bu iki kod run edilmeli.
## RabbitMQ Docker
    docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=root rabbitmq:3-management
## Zipkin Docker
    docker run -d -p 9411:9411 openzipkin/zipkin

2) postgres pgAdmin indirilmiş olmalı, sonrasında MobilizDistrictAndAuthorization, MobilizVehicle ve MobilizUser Databaseleri oluşturulmalı.
3) Modüllerde öncelikle ConfigServer, sonra ApiGateway sonrasında DistrictAndAuthorization,Vehicle ve User Mikroservisleri ayağa kaldırılmalı.
4) Proje çalıştırılıp tablolar oluşturulunca PostgreSQL de, MobilizUser Database'inde bir COMPANYADMIN rolünde, şifresi oluşturularak(Aa123456**)
ve email bilgisi girilerek User tanımlanmalı; çünkü register işlemi yetkisi COMPANYADMIN rolune sahip olan kişide.
5)  UserMicroService için methodlar buradan görüntülenebilir http://localhost:8090/swagger-ui/index.html#/
    VehicleMicroService için methodlar buradan görüntülenebilir http://localhost:8091/swagger-ui/index.html#/
    DistrictAndAuthorizationMicroService için methodlar buradan görüntülenebilir http://localhost:8090/swagger-ui/index.html#/
6) UserMicroService için oluşturduğumuz User'ın şifresi ve emaili ile Login methodu çalıştırılarak token alınıp bu tokenla PostMan üzerinden istek atılarak başlanabilir.
7) DistrictAndAuthorizationMicroService'te;
            Zone Entity'si Bölge anlamına Gelir (AVRUPA,ASYA vs.)
            Sector Entity'si Sektör anlamına Gelir (KURYE, NAKLİYAT vs.)
            Şehir bilgiler Enum olarak tutulmuştur.
            Authorization Entity'si Yetki bölgelerini belirtir.
8) Userlar oluşturulmalı (emailler farklı olmalı, şifre için regex var (Aa123456** uygun) )
9) Araçlar oluşturulmalı
10) Zonelar oluşturulmalı
11) Sectorler oluşturulmalı
12) Son olarak Araç yetkilendirmeleri yapılabilir. (AuthorizationController)
13) Yetkilendirme işlemleri bölgeye göre yapılmıştır. Örneğin bir kişinin yetki alanında sadece ISTANBUL belirtilip, zone ve sektör belirtilmediyse o kişi İstanbul
sorumlusu olup, İstanbul'daki her aracı sektör ve zone farketmezsizin görüntüleyebilir. (vehicle/findall methodunda otomatik filtrelenir.)
Eğer şehir İstanbul bölge Avrupa seçildiyse, bu kişi İstanbul'da Avrupa Bölgesi'nde her sektördeki araçları görebilir. Sadece COMPANYADMIN kişisi bütün araçları görüntüler.